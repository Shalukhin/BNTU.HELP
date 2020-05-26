package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import builder.PersonalDataBuilder;
import dao.PersonalDataDAO;
import dao.constant.DBColumnNameConstant;
import entity.PersonalData;
import exception.DAOException;
import exception.PoolException;
import pool.PoolConnection;

public class PersonalDataDAOImpl implements PersonalDataDAO {

	private static final Logger LOGGER = LogManager.getLogger(PersonalDataDAOImpl.class.getName());

	private PersonalDataBuilder PersonalDataBuilder = new PersonalDataBuilder();

	private static final String SQL_REQUEST_CREATE_PERSONAL_DATA = "INSERT INTO `personaldata` (`name`, `phone`, `email`, `bonusMoney`, `idInvitingUser`) VALUES (?, ?, ?, ?, ?);";
	private static final String SQL_REQUEST_FIND_PERSONAL_DATA_BY_ID = "SELECT * FROM `personaldata` WHERE id = ?;";
	private static final String SQL_REQUEST_UPDATE_PERSONAL_DATA = "UPDATE `personaldata` "
			+ "SET `name` = ?, `phone` = ?, `email` = ?, `bonusMoney` = ?, `idInvitingUser` = ? WHERE id = ?";
	private static final String SQL_REQUEST_DELETE_PERSONAL_DATA = "DELETE FROM `personaldata` WHERE id = ?";
	
	@Override
	public PersonalData create(PersonalData entity) throws DAOException {
		if (entity == null) {
			LOGGER.error("Error create personal data - personal data null");
			throw new DAOException("Error create personal data - personal data null");
		}		
		
		int result;
		try (Connection connection = PoolConnection.INSANCE.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_REQUEST_CREATE_PERSONAL_DATA, Statement.RETURN_GENERATED_KEYS);) {
			
			if (!StringUtils.isBlank(entity.getName())) {
				preparedStatement.setString(1, entity.getName());
			} else {
				preparedStatement.setNull(1, Types.NULL);
			}
			
			if (!StringUtils.isBlank(entity.getPhone())) {
				preparedStatement.setString(2, entity.getPhone());
			} else {
				preparedStatement.setNull(2, Types.NULL);
			}
			
			if (!StringUtils.isBlank(entity.getEmail())) {
				preparedStatement.setString(3, entity.getEmail());
			} else {
				preparedStatement.setNull(3, Types.NULL);
			}			
			
			preparedStatement.setBigDecimal(4, entity.getBonusMoney());			
			preparedStatement.setInt(5, entity.getIdInvitingUser());			

			result = preparedStatement.executeUpdate();
			
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();
			entity.setId(resultSet.getInt(1));

		} catch (SQLException e) {
			LOGGER.error("Error create personal data - SQL error", e);
			throw new DAOException("Error create personal data - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error create personal data - pool error", e);
			throw new DAOException("Error create personal data - pool error", e);
		}

		if (result != 1) {
			throw new DAOException("Error personal data - personal data not create");
		}
		return entity;
	}

	@Override
	public PersonalData findById(int id) throws DAOException {

		try (Connection connection = PoolConnection.INSANCE.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(SQL_REQUEST_FIND_PERSONAL_DATA_BY_ID);) {

			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			List<PersonalData> result = buildPersonalDataList(resultSet);
			if (result.size() == 0) {
				LOGGER.error("Error personal data not found by ID");
				throw new DAOException("Error personal data not found by ID");
			}
			return result.get(0);

		} catch (SQLException e) {
			LOGGER.error("Error find personal data - SQL error", e);
			throw new DAOException("Error find personal data - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error find personal data - pool error", e);
			throw new DAOException("Error find personal data - pool error", e);
		}
	}

	private List<PersonalData> buildPersonalDataList(ResultSet resultSet) throws DAOException {
		List<PersonalData> personalDataList = new ArrayList<>();
		PersonalData personalData;

		try {
			while (resultSet.next()) {
				personalData = PersonalDataBuilder.createNewPersonalData()
						.withId(resultSet.getInt(DBColumnNameConstant.PERSONAL_DATA_ID))
						.withName(resultSet.getString(DBColumnNameConstant.PERSONAL_DATA_NAME))
						.withPhone(resultSet.getString(DBColumnNameConstant.PERSONAL_DATA_PHONE))
						.withEmail(resultSet.getString(DBColumnNameConstant.PERSONAL_DATA_EMAIL))
						.withBonusMoney(resultSet.getBigDecimal(DBColumnNameConstant.PERSONAL_DATA_BONUS_MONEY))
						.withIdInvitingUser(resultSet.getInt(DBColumnNameConstant.PERSONAL_DATA_ID_INVITING_USER))
						.build();

				personalDataList.add(personalData);
			}

		} catch (SQLException e) {
			LOGGER.error("Error build personal data - SQL error", e);
			throw new DAOException("Error build personal data - SQL error", e);
		}
		return personalDataList;
	}

	@Override
	public PersonalData update(PersonalData entity) throws DAOException {
		if (entity == null) {
			LOGGER.error("Error update personal data - personal data null");
			throw new DAOException("Error update personal data - personal data null");
		}
		
		int result;
		try (Connection connection = PoolConnection.INSANCE.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(SQL_REQUEST_UPDATE_PERSONAL_DATA);) {			
			
			if (!StringUtils.isBlank(entity.getName())) {
				preparedStatement.setString(1, entity.getName());
			} else {
				preparedStatement.setNull(1, Types.NULL);
			}
			
			if (!StringUtils.isBlank(entity.getPhone())) {
				preparedStatement.setString(2, entity.getPhone());
			} else {
				preparedStatement.setNull(2, Types.NULL);
			}
			
			if (!StringUtils.isBlank(entity.getEmail())) {
				preparedStatement.setString(3, entity.getEmail());
			} else {
				preparedStatement.setNull(3, Types.NULL);
			}	
			
			preparedStatement.setBigDecimal(4, entity.getBonusMoney());
			preparedStatement.setInt(5, entity.getIdInvitingUser());
			preparedStatement.setInt(6, entity.getId());

			result = preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			LOGGER.error("Error update personal data - SQL error", e);
			throw new DAOException("Error update personal data - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error update personal data - pool error", e);
			throw new DAOException("Error update personal data - pool error", e);
		}
		
		if (result != 1) {
			throw new DAOException("Error personal data - personal data not update");
		}
		
		return entity;
	}
	
	@Override
	public boolean delete(PersonalData entity) throws DAOException {
		if (entity == null) {
			LOGGER.error("Error delete personal data - personal data null");
			throw new DAOException("Error delete personal data - personal data null");
		}

		return delete(entity.getId());
	}

	@Override
	public boolean delete(int id) throws DAOException {
		
		int result;
		try (Connection connection = PoolConnection.INSANCE.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_REQUEST_DELETE_PERSONAL_DATA);) {		
			
			preparedStatement.setInt(1, id);			

			result = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.error("Error delete personal data - SQL error", e);
			throw new DAOException("Error delete personal data - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error delete personal data - pool error", e);
			throw new DAOException("Error delete personal data - pool error", e);
		}		
				
		return (result == 1);
	}

}
