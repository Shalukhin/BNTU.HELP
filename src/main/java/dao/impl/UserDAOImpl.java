package dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.RoleDAO;
import dao.StatusDAO;
import dao.UserDAO;
import entity.User;
import exception.DAOException;
import exception.PoolException;
import pool.PoolConnection;

public class UserDAOImpl implements UserDAO {

	private final static Logger LOGGER = LogManager.getLogger(UserDAOImpl.class.getName());
	
	RoleDAO roleDAO = new RoleDAOImpl();
	StatusDAO statusDAO = new StatusDAOImpl();

	@Override
	public boolean create(User bean) throws DAOException {
		if (bean == null) {
			LOGGER.error("Error create user - user null");
			throw new DAOException("Error create user - user null");
		}
		int result;
		try (Connection connection = PoolConnection.INSANCE.getConnection();
				Statement statement = connection.createStatement()){
			String request = String.format("INSERT INTO `user` (`login`, `password`, `idRole`, `idStatus`) "
					+ "VALUES ('%s', '%s', '%d', '%d');", 
					bean.getLogin(), bean.getPassword(), bean.getRole().getId(), bean.getStatus().getId());			
			result = statement.executeUpdate(request);
		} catch (SQLException e) {
			LOGGER.error("Error create user - SQL error, e");
			throw new DAOException("Error create user - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error create user - pool error, e");
			throw new DAOException("Error create user - pool error", e);
		}
		
		return (result == 1);
	}

	@Override
	public User read(int id) throws DAOException {
		String parameter = String.format("WHERE id = %s", String.valueOf(id));
		List<User> result = readByParametr(parameter);
		if (result.size() == 0) {
			throw new DAOException("Error user not found");
		}
		return readByParametr(parameter).get(0);
	}
	
	@Override
	public List<User> read(String login, String password) throws DAOException {
		String parameter = String.format("WHERE `login` = '%s' AND `password` = '%s'", login, password);
		return readByParametr(parameter);
	}
	
	private List<User> readByParametr(String parameter) throws DAOException {
		List<User> userList = new ArrayList<User>();
		try (Connection connection = PoolConnection.INSANCE.getConnection();
				Statement statement = connection.createStatement()){
			
			String request = String.format("SELECT * FROM `user` %s", parameter);
			ResultSet resultSet = statement.executeQuery(request);
			User user;
			while (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt("id"));
				user.setLogin(resultSet.getString("login"));
				user.setPassword(resultSet.getString("password"));				
				user.setRole(roleDAO.read(resultSet.getInt("idRole")));
				user.setStatus(statusDAO.read(resultSet.getInt("idStatus")));
				userList.add(user);
			}
			
		} catch (SQLException e) {
			LOGGER.error("Error read user - SQL error", e);
			throw new DAOException("Error read user - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error read user - pool error", e);
			throw new DAOException("Error read user - pool error", e);
		}
		return userList;
	}

	@Override
	public boolean update(User bean) throws DAOException {
		if (bean == null) {
			LOGGER.error("Error update user - user null");
			throw new DAOException("Error update user - user null");
		}
		int result;
		try (Connection connection = PoolConnection.INSANCE.getConnection();
				Statement statement = connection.createStatement()){
			
			String request = String.format("UPDATE `user` SET `login` = '%s', `password` = '%s', `idRole` = '%s', `idStatus` = '%s' "
					+ "WHERE id = %s", bean.getLogin(), bean.getPassword(), String.valueOf(bean.getRole().getId()), 
					String.valueOf(bean.getStatus().getId()), String.valueOf(bean.getId()));
			
			result = statement.executeUpdate(request);
		} catch (SQLException e) {
			LOGGER.error("Error read user - SQL error", e);
			throw new DAOException("Error read user - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error read user - pool error", e);
			throw new DAOException("Error read user - pool error", e);
		}
		return (result == 1);
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
				Statement statement = connection.createStatement()){
			
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
