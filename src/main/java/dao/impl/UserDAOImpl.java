package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import builder.UserBuilder;
import dao.PersonalDataDAO;
import dao.RoleDAO;
import dao.StatusDAO;
import dao.UserDAO;
import static dao.constant.DBColumnNameConstant.*;
import entity.User;
import exception.DAOException;
import exception.PoolException;
import pool.PoolConnection;

public class UserDAOImpl implements UserDAO {

	private static final Logger LOGGER = LogManager.getLogger(UserDAOImpl.class.getName());

	private UserBuilder userBuilder = new UserBuilder();
	private RoleDAO roleDAO = new RoleDAOImpl();
	private StatusDAO statusDAO = new StatusDAOImpl();
	private PersonalDataDAO personalDataDAO = new PersonalDataDAOImpl();

	private static final String SQL_REQUEST_CREATE_USER = "INSERT INTO `user` (`login`, `password`, `idRole`, `idStatus`, `idPersonalData`) VALUES (?, ?, ?, ?, ?);";
	private static final String SQL_REQUEST_FIND_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM `user` WHERE login = ? AND password = ?;";	
	private static final String SQL_REQUEST_FIND_USER_BY_ID = "SELECT * FROM `user` WHERE id = ?;";
	private static final String SQL_REQUEST_FIND_ALL_USER = "SELECT * FROM `user`;";
	private static final String SQL_REQUEST_CHECK_USER_LOGIN = "SELECT login FROM `user` WHERE login = ?;";	
	private static final String SQL_REQUEST_UPDATE_USER = "UPDATE `user` SET `login` = ?, `password` = ?, `idRole` = ?, `idStatus` = ?, `idPersonalData` = ? WHERE id = ?;";
	private static final String SQL_REQUEST_DELETE_USER = "DELETE FROM `user` WHERE id = ?";

	@Override
	public User create(User entity) throws DAOException {
		if (entity == null) {
			LOGGER.error("Error create user - user null");
			throw new DAOException("Error create user - user null");
		}
		int result;
		try (Connection connection = PoolConnection.INSANCE.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_REQUEST_CREATE_USER, Statement.RETURN_GENERATED_KEYS);) {

			preparedStatement.setString(1, entity.getLogin());
			preparedStatement.setString(2, entity.getPassword());
			preparedStatement.setInt(3, entity.getRole().getId());
			preparedStatement.setInt(4, entity.getStatus().getId());
			
			if (entity.getPersonalData() != null) {
				preparedStatement.setInt(5, entity.getPersonalData().getId());
			} else {
				preparedStatement.setNull(5, Types.NULL);
			}		

			result = preparedStatement.executeUpdate();
			
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();
			entity.setId(resultSet.getInt(1));

		} catch (SQLException e) {
			LOGGER.error("Error create user - SQL error, e");
			throw new DAOException("Error create user - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error create user - pool error, e");
			throw new DAOException("Error create user - pool error", e);
		}
		
		if (result != 1) {
			throw new DAOException("Error create user - user not create");
		}
				
		return entity;
	}

	@Override
	public User findById(int id) throws DAOException {

		try (Connection connection = PoolConnection.INSANCE.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_REQUEST_FIND_USER_BY_ID);) {
			
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			List<User> result = buildUserList(resultSet);
			if (result.size() == 0) {
				LOGGER.error("Error user not found by ID");
				throw new DAOException("Error user not found by ID");
			}
			return result.get(0);

		} catch (SQLException e) {
			LOGGER.error("Error find user by ID - SQL error, e");
			throw new DAOException("Error find user by ID - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error find user by ID - pool error, e");
			throw new DAOException("Error find user by ID - pool error", e);
		}		
	}	
	
	@Override
	public User findByLoginAndPassword(String login, String password) throws DAOException {
		
		try (Connection connection = PoolConnection.INSANCE.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_REQUEST_FIND_USER_BY_LOGIN_AND_PASSWORD);) {
			
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, password);			
			ResultSet resultSet = preparedStatement.executeQuery();

			List<User> result = buildUserList(resultSet);
			if (result.size() == 0) {
				LOGGER.error("Error user not found by login and password");				
				return null;
			}
			return result.get(0);

		} catch (SQLException e) {
			LOGGER.error("Error find user - SQL error, e");
			throw new DAOException("Error find user - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error find user - pool error, e");
			throw new DAOException("Error find user - pool error", e);
		}		
	}
	
	@Override
	public List<User> findAllUser() throws DAOException {
		
		try (Connection connection = PoolConnection.INSANCE.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_REQUEST_FIND_ALL_USER);) {
			
			ResultSet resultSet = preparedStatement.executeQuery();			
			
			return buildUserList(resultSet);

		} catch (SQLException e) {
			LOGGER.error("Error find all user - SQL error, e");
			throw new DAOException("Error find all user - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error find all user - pool error, e");
			throw new DAOException("Error find all user - pool error", e);
		}
	}
	
	@Override
	public boolean checkExistLogin(String login) throws DAOException {
		
		try (Connection connection = PoolConnection.INSANCE.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_REQUEST_CHECK_USER_LOGIN);) {
			
			preparedStatement.setString(1, login);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			return resultSet.next();		

		} catch (SQLException e) {
			LOGGER.error("Error find user - SQL error, e");
			throw new DAOException("Error find user - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error find user - pool error, e");
			throw new DAOException("Error find user - pool error", e);
		}
	}

	private List<User> buildUserList(ResultSet resultSet) throws DAOException {
		List<User> userList = new ArrayList<User>();
		User user;
		String idPersonalData;
		try {
			while (resultSet.next()) {

				idPersonalData = resultSet.getString(USER_ID_PERSONAL_DATA);
				
				user = userBuilder
						.createNewUser()
						.withId(resultSet.getInt(USER_ID))
						.withLogin(resultSet.getString(USER_LOGIN))
						.withPassword(resultSet.getString(USER_PASSWORD))
						.withRole(roleDAO.findById(resultSet.getInt(USER_ID_ROLE)))
						.withStatus(statusDAO.findById(resultSet.getInt(USER_ID_STATUS)))
						.build();
				
				if (idPersonalData != null) {
					user.setPersonalData(personalDataDAO.findById(Integer.valueOf(idPersonalData)));
				}
				
				userList.add(user);
			}
			
		} catch (Exception e) {
			LOGGER.error("Error build user - SQL error", e);
			throw new DAOException("Error build user - SQL error", e);
		}
		return userList;
	}

	@Override
	public User update(User entity) throws DAOException {
		
		if (entity == null) {
			LOGGER.error("Error update user - user null");
			throw new DAOException("Error update user - user null");
		}
		int result;
		try (Connection connection = PoolConnection.INSANCE.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_REQUEST_UPDATE_USER);) {

			preparedStatement.setString(1, entity.getLogin());
			preparedStatement.setString(2, entity.getPassword());
			preparedStatement.setInt(3, entity.getRole().getId());
			preparedStatement.setInt(4, entity.getStatus().getId());
			
			if (entity.getPersonalData() != null) {
				preparedStatement.setInt(5, entity.getPersonalData().getId());
			} else {
				preparedStatement.setNull(5, Types.NULL);
			}
			
			preparedStatement.setInt(6, entity.getId());

			result = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.error("Error update user - SQL error, e");
			throw new DAOException("Error update user - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error update user - pool error, e");
			throw new DAOException("Error update user - pool error", e);
		}
		
		if (result != 1) {
			throw new DAOException("Error update user - user not update");
		}
				
		return entity;
	}

	@Override
	public boolean delete(User entity) throws DAOException {
		if (entity == null) {
			LOGGER.error("Error delete user - user null");
			throw new DAOException("Error delete user - user null");
		}

		return delete(entity.getId());
	}

	@Override
	public boolean delete(int id) throws DAOException {		
		int result;
		try (Connection connection = PoolConnection.INSANCE.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_REQUEST_DELETE_USER);) {		
			
			preparedStatement.setInt(1, id);

			result = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.error("Error delete user - SQL error, e");
			throw new DAOException("Error delete user - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error delete user - pool error, e");
			throw new DAOException("Error delete user - pool error", e);
		}		
				
		return (result == 1);	
	}	

}
