package dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.RoleDAO;
import entity.Role;
import entity.User;
import exception.DAOException;
import exception.PoolException;
import pool.PoolConnection;

public class RoleDAOImpl implements RoleDAO {
	
	private final static Logger LOGGER = LogManager.getLogger(RoleDAOImpl.class.getName());

	@Override
	public boolean create(Role bean) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Role read(int id) throws DAOException {
		Role result = new Role();
		try (Connection connection = PoolConnection.INSANCE.getConnection();
				Statement statement = connection.createStatement()){
			
			String request = String.format("SELECT * FROM `role` WHERE id = %s", String.valueOf(id));
			ResultSet resultSet = statement.executeQuery(request);
			resultSet.next();
			result.setId(resultSet.getInt("id"));
			result.setNameRole(resultSet.getString("nameRole"));			
			
		} catch (SQLException e) {
			LOGGER.error("Error read role - SQL error");
			throw new DAOException("Error read role - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error read role - pool error");
			throw new DAOException("Error read role - pool error", e);
		};
		
		return result;
	}

	@Override
	public boolean update(Role bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Role bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
