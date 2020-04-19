package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import builder.RoleBuilder;
import builder.StatusBuilder;
import builder.UserBuilder;
import dao.UserDAO;
import entity.Role;
import entity.Status;
import entity.User;
import exception.DAOException;
import exception.PoolException;
import pool.PoolConnection;

public class UserDAOImpl implements UserDAO {

	private static final Logger LOGGER = LogManager.getLogger(UserDAOImpl.class.getName());

	private UserBuilder userBuilder = new UserBuilder();
	private RoleBuilder roleBuilder = new RoleBuilder();
	private StatusBuilder statusBuilder = new StatusBuilder();

	private static final String SQL_REQUEST_CREATE_USER = "INSERT INTO `user` (`login`, `password`, `idRole`, `idStatus`) VALUES (?, ?, ?, ?);";
	private static final String SQL_REQUEST_FIND_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM `user` JOIN `role` ON user.idRole = role.id "
			+ "JOIN `status` ON user.idStatus = status.id WHERE login = ? AND password = ?;";	
	private static final String SQL_REQUEST_FIND_USER_BY_ID = "SELECT * FROM `user` JOIN `role` ON user.idRole = role.id "
			+ "JOIN `status` ON user.idStatus = status.id WHERE user.id = ?;";
	private static final String SQL_REQUEST_CHECK_USER_LOGIN = "SELECT login FROM `user` WHERE login = ?;";	

	@Override
	public User create(User entity) throws DAOException {
		if (entity == null) {
			LOGGER.error("Error create user - user null");
			throw new DAOException("Error create user - user null");
		}
		int result;
		try (Connection connection = PoolConnection.INSANCE.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_REQUEST_CREATE_USER);) {

			preparedStatement.setString(1, entity.getLogin());
			preparedStatement.setString(2, entity.getPassword());
			preparedStatement.setInt(3, entity.getRole().getId());
			preparedStatement.setInt(4, entity.getStatus().getId());

			result = preparedStatement.executeUpdate();

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
				
		return findByLoginAndPassword(entity.getLogin(), entity.getPassword());
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
		Role role;
		Status status;
		try {
			while (resultSet.next()) {
				role = roleBuilder
						.createNewRole()
						.withId(resultSet.getInt("idRole"))
						.withNameRole(resultSet.getString("nameRole"))
						.build();

				status = statusBuilder
						.createNewStatus()
						.withId(resultSet.getInt("idStatus"))
						.withNameStatus(resultSet.getString("nameStatus"))
						.withRatioPay(resultSet.getDouble("ratioPay"))
						.build();

				user = userBuilder
						.createNewUser()
						.withId(resultSet.getInt("id"))
						.withLogin(resultSet.getString("login"))
						.withPassword(resultSet.getString("password"))
						.withRole(role)
						.withStatus(status)
						.build();
				
				userList.add(user);
			}
			
		} catch (SQLException e) {
			LOGGER.error("Error build user - SQL error", e);
			throw new DAOException("Error build user - SQL error", e);
		}
		return userList;
	}

	@Override
	public User update(User entity) throws DAOException {
//		if (entity == null) {
//			LOGGER.error("Error update user - user null");
//			throw new DAOException("Error update user - user null");
//		}
//		int result;
//		try (Connection connection = PoolConnection.INSANCE.getConnection();
//				Statement statement = connection.createStatement()) {
//
//			String request = String.format(
//					"UPDATE `user` SET `login` = '%s', `password` = '%s', `idRole` = '%s', `idStatus` = '%s' "
//							+ "WHERE id = %s",
//					entity.getLogin(), entity.getPassword(), String.valueOf(entity.getRole().getId()),
//					String.valueOf(entity.getStatus().getId()), String.valueOf(entity.getId()));
//
//			result = statement.executeUpdate(request);
//		} catch (SQLException e) {
//			LOGGER.error("Error read user - SQL error", e);
//			throw new DAOException("Error read user - SQL error", e);
//		} catch (PoolException e) {
//			LOGGER.error("Error read user - pool error", e);
//			throw new DAOException("Error read user - pool error", e);
//		}
		return null;
	}

	@Override
	public boolean delete(User bean) throws DAOException {
		if (bean == null) {
			LOGGER.error("Error delete user - user null");
			throw new DAOException("Error delete user - user null");
		}

		return delete(bean.getId());
	}

	@Override
	public boolean delete(int id) throws DAOException {
		int result;
		try (Connection connection = PoolConnection.INSANCE.getConnection();
				Statement statement = connection.createStatement()) {

			String request = String.format("DELETE FROM `user` WHERE id = %s", String.valueOf(id));

			result = statement.executeUpdate(request);
		} catch (SQLException e) {
			LOGGER.error("Error delete user - SQL error", e);
			throw new DAOException("Error delete user - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error delete user - pool error", e);
			throw new DAOException("Error delete user - pool error", e);
		}
		return (result == 1);
	}

	

	

}
