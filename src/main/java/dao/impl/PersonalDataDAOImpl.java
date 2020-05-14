package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

	private static final String SQL_REQUEST_CREATE_PERSONAL_DATA = "INSERT INTO `personaldata` (`idUser`, `name`, `phone`, `email`, `bonusMoney`, `idInvitingUser`) VALUES (?, ?, ?, ?, ?, ?);";
	private static final String SQL_REQUEST_FIND_PERSONAL_DATA_BY_ID = "SELECT * FROM `personaldata` WHERE idUser = ?;";
	private static final String SQL_REQUEST_UPDATE_PERSONAL_DATA = "UPDATE `personaldata` "
			+ "SET `name` = ?, `phone` = ?, `email` = ?, `bonusMoney` = ?, `idInvitingUser` = ? WHERE idUser = ?";
	private static final String SQL_REQUEST_DELETE_PERSONAL_DATA = "DELETE FROM `personaldata` WHERE idUser = ?";
	
	@Override
	public PersonalData create(PersonalData entity) throws DAOException {
		if (entity == null) {
			LOGGER.error("Error create personal data - personal data null");
			throw new DAOException("Error create personal data - personal data null");
		}
		
		if(findById(entity.getIdUser()) != null) {
			LOGGER.error("Error create personal data - personal data exists");
			throw new DAOException("Error create personal data - personal data exists");
		}
		
		int result;
		try (Connection connection = PoolConnection.INSANCE.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_REQUEST_CREATE_PERSONAL_DATA);) {

			preparedStatement.setInt(1, entity.getIdUser());
			preparedStatement.setString(2, entity.getName());
			preparedStatement.setString(3, entity.getPhone());
			preparedStatement.setString(4, entity.getEmail());
			preparedStatement.setBigDecimal(5, entity.getBonusMoney());
			preparedStatement.setInt(6, entity.getIdInvitingUser());			

			result = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.error("Error create personal data - SQL error, e");
			throw new DAOException("Error create personal data - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error create personal data - pool error, e");
			throw new DAOException("Error create personal data - pool error", e);
		}

		if (result != 1) {
			throw new DAOException("Error personal data - personal data not create");
		}
		return findById(entity.getIdUser());
	}
	
	@Override
	public boolean checkExistIdUser(int idUser) throws DAOException {
		
		return findById(idUser) != null ? true : false ;
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
				return null;
			}
			return result.get(0);

		} catch (SQLException e) {
			LOGGER.error("Error find personal data - SQL error, e");
			throw new DAOException("Error find personal data - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error find personal data - pool error, e");
			throw new DAOException("Error find personal data - pool error", e);
		}
	}

	private List<PersonalData> buildPersonalDataList(ResultSet resultSet) throws DAOException {
		List<PersonalData> personalDataList = new ArrayList<>();
		PersonalData personalData;

		try {
			while (resultSet.next()) {
				personalData = PersonalDataBuilder.createNewPersonalData()
						.withIdUser(resultSet.getInt(DBColumnNameConstant.PERSONAL_DATA_ID_USER))
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
			LOGGER.error("Error update user - user null");
			throw new DAOException("Error update user - user null");
		}
		
		int result;
		try (Connection connection = PoolConnection.INSANCE.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(SQL_REQUEST_UPDATE_PERSONAL_DATA);) {			
			
			preparedStatement.setString(1, entity.getName());
			preparedStatement.setString(2, entity.getPhone());
			preparedStatement.setString(3, entity.getEmail());
			preparedStatement.setBigDecimal(4, entity.getBonusMoney());
			preparedStatement.setInt(5, entity.getIdInvitingUser());
			preparedStatement.setInt(6, entity.getIdUser());

			result = preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			LOGGER.error("Error update personal data - SQL error, e");
			throw new DAOException("Error update personal data - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error update personal data - pool error, e");
			throw new DAOException("Error update personal data - pool error", e);
		}
		
		if (result != 1) {
			throw new DAOException("Error personal data - personal data not update");
		}
		
		return findById(entity.getIdUser());
	}
	
	@Override
	public boolean delete(PersonalData entity) throws DAOException {
		if (entity == null) {
			LOGGER.error("Error delete personal data - personal data null");
			throw new DAOException("Error delete personal data - personal data null");
		}

		return delete(entity.getIdUser());
	}

	@Override
	public boolean delete(int id) throws DAOException {
		
		int result;
		try (Connection connection = PoolConnection.INSANCE.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_REQUEST_DELETE_PERSONAL_DATA);) {		
			
			preparedStatement.setInt(1, id);			

			result = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.error("Error delete personal data - SQL error, e");
			throw new DAOException("Error delete personal data - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error delete personal data - pool error, e");
			throw new DAOException("Error delete personal data - pool error", e);
		}		
				
		return (result == 1);
	}

}
