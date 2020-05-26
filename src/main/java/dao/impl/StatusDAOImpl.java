package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import builder.StatusBuilder;
import dao.StatusDAO;
import static dao.constant.DBColumnNameConstant.*;
import entity.Status;
import exception.DAOException;
import exception.PoolException;
import pool.PoolConnection;

public class StatusDAOImpl implements StatusDAO {

	private final static Logger LOGGER = LogManager.getLogger(StatusDAOImpl.class.getName());

	private StatusBuilder statusBuilder = new StatusBuilder();

	private static final String SQL_REQUEST_FIND_STATUS_BY_NAME = "SELECT * FROM `status` WHERE nameStatus = ?;";
	private static final String SQL_REQUEST_FIND_STATUS_BY_ID = "SELECT * FROM `status` WHERE id = ?;";
	private static final String SQL_REQUEST_FIND_ALL_STATUS = "SELECT * FROM `status`;";

	@Override
	public Status create(Status bean) throws DAOException {
		throw new UnsupportedOperationException("Error - Status create operation not defined");
	}

	@Override
	public Status findByName(String nameStatus) throws DAOException {

		try (Connection connection = PoolConnection.INSANCE.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_REQUEST_FIND_STATUS_BY_NAME);) {

			preparedStatement.setString(1, nameStatus);
			ResultSet resultSet = preparedStatement.executeQuery();

			List<Status> result = buildStatusList(resultSet);
			if (result.size() == 0) {
				LOGGER.error("Error status not found by name");
				throw new DAOException("Error status not found by name");
			}
			return result.get(0);

		} catch (SQLException e) {
			LOGGER.error("Error find status by name - SQL error", e);
			throw new DAOException("Error find status by name - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error find status by name - pool error", e);
			throw new DAOException("Error find status by name - pool error", e);
		}
	}
	
	@Override
	public Status findById(int id) throws DAOException {
		
		try (Connection connection = PoolConnection.INSANCE.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_REQUEST_FIND_STATUS_BY_ID);) {
			
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			List<Status> result = buildStatusList(resultSet);
			if (result.size() == 0) {
				LOGGER.error("Error status not found by ID");
				throw new DAOException("Error status not found by ID");
			}
			return result.get(0);

		} catch (SQLException e) {
			LOGGER.error("Error find status by ID - SQL error", e);
			throw new DAOException("Error find status by ID - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error find status by ID - pool error", e);
			throw new DAOException("Error find status by ID - pool error", e);
		}
	}
	
	@Override
	public List<Status> findAllStatus() throws DAOException {
		
		try (Connection connection = PoolConnection.INSANCE.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_REQUEST_FIND_ALL_STATUS);) {
			
			ResultSet resultSet = preparedStatement.executeQuery();			
			
			return buildStatusList(resultSet);

		} catch (SQLException e) {
			LOGGER.error("Error find all status - SQL error", e);
			throw new DAOException("Error find all status - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error find all status - pool error", e);
			throw new DAOException("Error find all status - pool error", e);
		}
	}	

	private List<Status> buildStatusList(ResultSet resultSet) throws DAOException {
		List<Status> statusList = new ArrayList<>();
		Status status;
		try {
			while (resultSet.next()) {
				
				status = statusBuilder
						.createNewStatus()
						.withId(resultSet.getInt(STATUS_ID))
						.withNameStatus(resultSet.getString(STATUS_NAME))
						.withRatioPay(resultSet.getDouble(STATUS_RATIO_PAY))
						.build();
				
				statusList.add(status);
			}

		} catch (SQLException e) {
			LOGGER.error("Error build status - SQL error", e);
			throw new DAOException("Error build status - SQL error", e);
		}
		return statusList;
	}	

	@Override
	public Status update(Status bean) throws DAOException {
		throw new UnsupportedOperationException("Error - Status update operation not defined");
	}

	@Override
	public boolean delete(int id) throws DAOException {
		throw new UnsupportedOperationException("Error - Status delete operation not defined");
	}

	@Override
	public boolean delete(Status bean) throws DAOException {
		throw new UnsupportedOperationException("Error - Status delete operation not defined");		
	}	

}
