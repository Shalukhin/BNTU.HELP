package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import builder.SubjectBuilder;
import dao.SubjectDAO;
import static dao.constant.DBColumnNameConstant.*;
import entity.Subject;
import exception.DAOException;
import exception.PoolException;
import pool.PoolConnection;

public class SubjectDAOImpl implements SubjectDAO {
	
	private final static Logger LOGGER = LogManager.getLogger(SubjectDAOImpl.class.getName());
	
	private static final String SQL_REQUEST_FIND_SUBJECT_BY_NAME = "SELECT * FROM `subject` WHERE nameSubject = ?;";
	private static final String SQL_REQUEST_FIND_SUBJECT_BY_ID = "SELECT * FROM `subject` WHERE id = ?;";
	private static final String SQL_REQUEST_FIND_ALL_SUBJECT = "SELECT * FROM `subject`;";
	
	private SubjectBuilder subjectBuilder = new SubjectBuilder();
	

	@Override
	public Subject create(Subject entity) throws DAOException {		
		throw new UnsupportedOperationException("Error - Subject create operation not defined");
	}

	@Override
	public Subject findById(int id) throws DAOException {
		
		try (Connection connection = PoolConnection.INSANCE.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_REQUEST_FIND_SUBJECT_BY_ID);) {
			
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			List<Subject> result = buildSubjectList(resultSet);
			if (result.size() == 0) {
				LOGGER.error("Error subject not found by ID");
				throw new DAOException("Error subject not found by ID");
			}
			return result.get(0);

		} catch (SQLException e) {
			LOGGER.error("Error find subject by ID - SQL error", e);
			throw new DAOException("Error find subject by ID - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error find subject by ID - pool error", e);
			throw new DAOException("Error find subject by ID - pool error", e);
		}
	}
	
	@Override
	public Subject findByName(String nameSubject) throws DAOException {
		
		try (Connection connection = PoolConnection.INSANCE.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_REQUEST_FIND_SUBJECT_BY_NAME);) {
			
			preparedStatement.setString(1, nameSubject);
			ResultSet resultSet = preparedStatement.executeQuery();

			List<Subject> result = buildSubjectList(resultSet);
			if (result.size() == 0) {
				LOGGER.error("Error subject not found by name");
				throw new DAOException("Error subject not found by name");
			}
			return result.get(0);

		} catch (SQLException e) {
			LOGGER.error("Error find subject by name - SQL error", e);
			throw new DAOException("Error find subject by name - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error find subject by name - pool error", e);
			throw new DAOException("Error find subject by name - pool error", e);
		}
	}
	
	@Override
	public List<Subject> findAllSubject() throws DAOException {
		
		try (Connection connection = PoolConnection.INSANCE.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_REQUEST_FIND_ALL_SUBJECT);) {
			
			ResultSet resultSet = preparedStatement.executeQuery();			
			
			return buildSubjectList(resultSet);

		} catch (SQLException e) {
			LOGGER.error("Error find all subject - SQL error", e);
			throw new DAOException("Error find all subject - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error find all subject - pool error", e);
			throw new DAOException("Error find all subject - pool error", e);
		}
	}
	
	private List<Subject> buildSubjectList(ResultSet resultSet) throws DAOException {
		
		List<Subject> subjectList = new ArrayList<>();		
		Subject subject;		
		try {
			while (resultSet.next()) {
				subject = subjectBuilder
						.createNewSubject()
						.withId(resultSet.getInt(SUBJECT_ID))
						.withNameSubject(resultSet.getString(SUBJECT_NAME))
						.build();				
				
				subjectList.add(subject);
			}
			
		} catch (SQLException e) {
			LOGGER.error("Error build subject - SQL error", e);
			throw new DAOException("Error build subject - SQL error", e);
		}
		return subjectList;
	}		

	@Override
	public Subject update(Subject entity) throws DAOException {
		throw new UnsupportedOperationException("Error - Subject update operation not defined");
	}

	@Override
	public boolean delete(int id) throws DAOException {
		throw new UnsupportedOperationException("Error - Subject delete operation not defined");
	}

	@Override
	public boolean delete(Subject entity) throws DAOException {
		throw new UnsupportedOperationException("Error - Subject delete operation not defined");
	}	

}
