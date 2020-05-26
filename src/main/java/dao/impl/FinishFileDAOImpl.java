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

import builder.FinishFileBuilder;
import dao.FinishFileDAO;
import static dao.constant.DBColumnNameConstant.*;
import entity.FinishFile;
import exception.DAOException;
import exception.PoolException;
import pool.PoolConnection;

public class FinishFileDAOImpl implements FinishFileDAO {

	private final static Logger LOGGER = LogManager.getLogger(FinishFileDAOImpl.class.getName());

	private static final String SQL_REQUEST_CREATE_FINISH_FILE = "INSERT INTO `finishfile` (`nameFinishFile`, `dataFinishFile`) VALUES (?, ?);";
	private static final String SQL_REQUEST_FIND_FINISH_FILE_BY_ID = "SELECT * FROM `finishfile` WHERE id = ?;";
	
	private FinishFileBuilder finishFileBuilder = new FinishFileBuilder(); 

	@Override
	public FinishFile create(FinishFile entity) throws DAOException {
		
		if (entity == null) {
			LOGGER.error("Error create finish file - finish file null");
			throw new DAOException("Error create finish file - finish file null");
		}
		int result;
		try (Connection connection = PoolConnection.INSANCE.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_REQUEST_CREATE_FINISH_FILE, Statement.RETURN_GENERATED_KEYS);) {

			preparedStatement.setString(1, entity.getNameFinishFile());
			preparedStatement.setBlob(2, entity.getDataFinishFile());
			
			result = preparedStatement.executeUpdate();
			
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();
			entity.setId(resultSet.getInt(1));

		} catch (SQLException e) {
			LOGGER.error("Error create finish file - SQL error", e);
			throw new DAOException("Error create finish file - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error create finish file - pool error", e);
			throw new DAOException("Error create finish file - pool error", e);
		}
		
		if (result != 1) {
			throw new DAOException("Error create finish file - finish file not create");
		}
				
		return entity;
	}

	@Override
	public FinishFile findById(int id) throws DAOException {

		try (Connection connection = PoolConnection.INSANCE.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(SQL_REQUEST_FIND_FINISH_FILE_BY_ID);) {

			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			List<FinishFile> result = buildFinishFileList(resultSet);
			if (result.size() == 0) {
				LOGGER.error("Error finish file not found by ID");
				throw new DAOException("Error finish file not found by ID");
			}
			return result.get(0);

		} catch (SQLException e) {
			LOGGER.error("Error find finish file by ID - SQL error", e);
			throw new DAOException("Error find finish file by ID - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error find finish file by ID - pool error", e);
			throw new DAOException("Error find finish file by ID - pool error", e);
		}
	}

	private List<FinishFile> buildFinishFileList(ResultSet resultSet) throws DAOException {

		List<FinishFile> finishFileList = new ArrayList<>();
		FinishFile finishFile;
		try {
			while (resultSet.next()) {
				finishFile = finishFileBuilder
								.createNewFinishFile()
								.withId(resultSet.getInt(FINISH_FILE_ID))
								.withNameFinishFile(resultSet.getString(FINISH_FILE_NAME_FINISH_FILE))
								.withDataFinishFile(resultSet.getBinaryStream(FINISH_FILE_DATA_FINISH_FILE))
								.build();

				finishFileList.add(finishFile);
			}

		} catch (SQLException e) {
			LOGGER.error("Error build finish file - SQL error", e);
			throw new DAOException("Error build finish file - SQL error", e);
		}
		return finishFileList;
	}

	@Override
	public FinishFile update(FinishFile entity) throws DAOException {
		throw new UnsupportedOperationException("Error - FinishFile update operation not defined");
	}

	@Override
	public boolean delete(int id) throws DAOException {
		throw new UnsupportedOperationException("Error - FinishFile delete operation not defined");
	}

	@Override
	public boolean delete(FinishFile entity) throws DAOException {
		throw new UnsupportedOperationException("Error - FinishFile delete operation not defined");
	}

}
