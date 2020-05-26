package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import builder.CourseBuilder;
import dao.CourseDAO;
import static dao.constant.DBColumnNameConstant.*;
import entity.Course;
import exception.DAOException;
import exception.PoolException;
import pool.PoolConnection;

public class CourseDAOImpl implements CourseDAO {

	private final static Logger LOGGER = LogManager.getLogger(CourseDAOImpl.class.getName());
	
	private static final String SQL_REQUEST_FIND_COURSE_BY_ID = "SELECT * FROM `course` WHERE id = ?;";
	private static final String SQL_REQUEST_FIND_COURSE_BY_NUMBER = "SELECT * FROM `course` WHERE numberCourse = ?;";
	private static final String SQL_REQUEST_FIND_ALL_COURSE = "SELECT * FROM `course`;";

	private CourseBuilder courseBuilder = new CourseBuilder();

	@Override
	public Course create(Course entity) throws DAOException {
		throw new UnsupportedOperationException("Error - Course create operation not defined");
	}

	@Override
	public Course findById(int id) throws DAOException {

		try (Connection connection = PoolConnection.INSANCE.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_REQUEST_FIND_COURSE_BY_ID);) {

			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			List<Course> result = buildCourseList(resultSet);
			if (result.size() == 0) {
				LOGGER.error("Error course not found by ID");
				throw new DAOException("Error course not found by ID");
			}
			return result.get(0);

		} catch (SQLException e) {
			LOGGER.error("Error find course by ID - SQL error", e);
			throw new DAOException("Error find course by ID - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error find course by ID - pool error", e);
			throw new DAOException("Error find course by ID - pool error", e);
		}
	}
	
	@Override
	public Course findByNumber(int numberCourse) throws DAOException {

		try (Connection connection = PoolConnection.INSANCE.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_REQUEST_FIND_COURSE_BY_NUMBER);) {

			preparedStatement.setInt(1, numberCourse);
			ResultSet resultSet = preparedStatement.executeQuery();

			List<Course> result = buildCourseList(resultSet);
			if (result.size() == 0) {
				LOGGER.error("Error course not found by number");
				throw new DAOException("Error course not found by number");
			}
			return result.get(0);

		} catch (SQLException e) {
			LOGGER.error("Error find course by number - SQL error", e);
			throw new DAOException("Error find course by number - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error find course by number - pool error", e);
			throw new DAOException("Error find course by number - pool error", e);
		}
	}
	
	@Override
	public List<Course> findAllCourse() throws DAOException {
		
		try (Connection connection = PoolConnection.INSANCE.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_REQUEST_FIND_ALL_COURSE);) {
			
			ResultSet resultSet = preparedStatement.executeQuery();			
			
			return buildCourseList(resultSet);

		} catch (SQLException e) {
			LOGGER.error("Error find all сourse - SQL error", e);
			throw new DAOException("Error find all сourse - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error find all сourse - pool error", e);
			throw new DAOException("Error find all сourse - pool error", e);
		}
	}

	private List<Course> buildCourseList(ResultSet resultSet) throws DAOException {

		List<Course> courseList = new ArrayList<>();
		Course course;
		try {
			while (resultSet.next()) {
				course = courseBuilder
							.createNewCourse()
							.withId(resultSet.getInt(COURSE_ID))
							.withNumberCourse(resultSet.getInt(COURSE_NUMBER))
							.build();

				courseList.add(course);
			}

		} catch (SQLException e) {
			LOGGER.error("Error build course - SQL error", e);
			throw new DAOException("Error build course - SQL error", e);
		}
		return courseList;
	}

	@Override
	public Course update(Course entity) throws DAOException {		
		throw new UnsupportedOperationException("Error - Course update operation not defined");
	}

	@Override
	public boolean delete(int id) throws DAOException {
		throw new UnsupportedOperationException("Error - Course delete operation not defined");
	}

	@Override
	public boolean delete(Course entity) throws DAOException {
		throw new UnsupportedOperationException("Error - Course delete operation not defined");
	}	
	
}
