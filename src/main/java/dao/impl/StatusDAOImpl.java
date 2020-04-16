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
import builder.StatusBuilder;
import dao.StatusDAO;
import entity.Status;
import exception.DAOException;
import exception.PoolException;
import pool.PoolConnection;

public class StatusDAOImpl implements StatusDAO {

	private final static Logger LOGGER = LogManager.getLogger(StatusDAOImpl.class.getName());

	private StatusBuilder statusBuilder = new StatusBuilder();

	private String sqlRequestFindStatusByName = "SELECT * FROM `status` WHERE nameStatus = ?;";

	@Override
	public Status create(Status bean) throws DAOException {
		
		return null;
	}

	@Override
	public Status findByName(String nameStatus) throws DAOException {

		try (Connection connection = PoolConnection.INSANCE.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlRequestFindStatusByName);) {

			preparedStatement.setString(1, nameStatus);
			ResultSet resultSet = preparedStatement.executeQuery();

			List<Status> result = buildStatusList(resultSet);
			if (result.size() == 0) {
				throw new DAOException("Error StatusDAO - status not found");
			}
			return result.get(0);

		} catch (SQLException e) {
			LOGGER.error("Error find status - SQL error, e");
			throw new DAOException("Error find status - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error find status - pool error, e");
			throw new DAOException("Error find status - pool error", e);
		}
	}

	private List<Status> buildStatusList(ResultSet resultSet) throws DAOException {
		List<Status> statusList = new ArrayList<>();
		Status status;
		try {
			while (resultSet.next()) {
				
				status = statusBuilder
						.createNewStatus()
						.withId(resultSet.getInt("id"))
						.withNameStatus(resultSet.getString("nameStatus"))
						.withRatioPay(resultSet.getDouble("ratioPay"))
						.build();
				
				statusList.add(status);
			}

		} catch (SQLException e) {
			LOGGER.error("Error build user - SQL error", e);
			throw new DAOException("Error build user - SQL error", e);
		}
		return statusList;
	}

	@Override
	public Status findById(int id) throws DAOException {
		Status result = new Status();
		try (Connection connection = PoolConnection.INSANCE.getConnection();
				Statement statement = connection.createStatement()) {

			String request = String.format("SELECT * FROM `status` WHERE id = %s", String.valueOf(id));
			ResultSet resultSet = statement.executeQuery(request);
			resultSet.next();
			result.setId(resultSet.getInt("id"));
			result.setNameStatus(resultSet.getString("nameStatus"));
			result.setRatioPay(resultSet.getDouble("ratioPay"));

		} catch (SQLException e) {
			LOGGER.error("Error read status - SQL error", e);
			throw new DAOException("Error read status - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error read status - pool error", e);
			throw new DAOException("Error read status - pool error", e);
		}
		;

		return result;
	}

	@Override
	public boolean update(Status bean) throws DAOException {
		
		return false;
	}

	@Override
	public boolean delete(int id) throws DAOException {
		
		return false;
	}

	@Override
	public boolean delete(Status bean) throws DAOException {
		
		return false;
	}

}
