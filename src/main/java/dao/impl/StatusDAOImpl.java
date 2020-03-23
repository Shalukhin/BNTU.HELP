package dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.StatusDAO;
import entity.Status;
import exception.DAOException;
import exception.PoolException;
import pool.PoolConnection;

public class StatusDAOImpl implements StatusDAO {
	
	private final static Logger LOGGER = LogManager.getLogger(StatusDAOImpl.class.getName());

	@Override
	public boolean create(Status bean) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Status read(int id) throws DAOException {
		Status result = new Status();
		try (Connection connection = PoolConnection.INSANCE.getConnection();
				Statement statement = connection.createStatement()){
			
			String request = String.format("SELECT * FROM `status` WHERE id = %s", String.valueOf(id));
			ResultSet resultSet = statement.executeQuery(request);
			resultSet.next();
			result.setId(resultSet.getInt("id"));
			result.setNameStatus(resultSet.getString("nameStatus"));
			result.setRatioPay(resultSet.getDouble("ratioPay"));
			
		} catch (SQLException e) {
			LOGGER.error("Error read status - SQL error");
			throw new DAOException("Error read status - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error read status - pool error");
			throw new DAOException("Error read status - pool error", e);
		};
		
		return result;
	}

	@Override
	public boolean update(Status bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Status bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
