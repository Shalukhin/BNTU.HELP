package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import builder.TaskBuilder;
import dao.CourseDAO;
import dao.SubjectDAO;
import dao.TaskDAO;
import dao.constant.DBColumnNameConstant;
import entity.Task;
import exception.DAOException;
import exception.PoolException;
import pool.PoolConnection;

public class TaskDAOImpl implements TaskDAO {
	
	private static final Logger LOGGER = LogManager.getLogger(TaskDAOImpl.class.getName());

	private TaskBuilder taskBuilder = new TaskBuilder();
	private CourseDAO courseDAO = new CourseDAOImpl();
	private SubjectDAO subjectDAO = new SubjectDAOImpl();
	
	private static final String SQL_REQUEST_CREATE_TASK = "INSERT INTO `task` (`nameTask`, `priceTask`, `idCourse`, `idSubject`) VALUES (?, ?, ?, ?);";
	private static final String SQL_REQUEST_FIND_ALL_TASK = "SELECT * FROM `task`;";
	private static final String SQL_REQUEST_FIND_TASK_BY_ID = "SELECT * FROM `task` WHERE id = ?;";
	private static final String SQL_REQUEST_FIND_TASK_BY_NAME = "SELECT * FROM `task` WHERE nameTask = ?;";
	private static final String SQL_REQUEST_UPDATE_TASK = "UPDATE `task` SET `nameTask` = ?, `priceTask` = ?, `idCourse` = ?, `idSubject` = ? WHERE id = ?;";
	private static final String SQL_REQUEST_DELETE_TASK = "DELETE FROM `task` WHERE id = ?";
	
	@Override
	public Task create(Task entity) throws DAOException {
		if (entity == null) {
			LOGGER.error("Error create task - task null");
			throw new DAOException("Error create task - task null");
		}
		int result;
		try (Connection connection = PoolConnection.INSANCE.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_REQUEST_CREATE_TASK);) {

			preparedStatement.setString(1, entity.getNameTask());
			preparedStatement.setBigDecimal(2, entity.getPriceTask());
			preparedStatement.setInt(3, entity.getCourse().getId());
			preparedStatement.setInt(4, entity.getSubject().getId());

			result = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.error("Error create task - SQL error, e");
			throw new DAOException("Error create task - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error create task - pool error, e");
			throw new DAOException("Error create task - pool error", e);
		}
		
		if (result != 1) {
			throw new DAOException("Error create task - task not create");
		}
				
		return entity;
	}	

	@Override
	public Task findById(int id) throws DAOException {
		
		try (Connection connection = PoolConnection.INSANCE.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(SQL_REQUEST_FIND_TASK_BY_ID);) {

			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			List<Task> result = buildTaskList(resultSet);
			if (result.size() == 0) {
				LOGGER.error("Error task not found by ID");
				return null;
			}
			return result.get(0);

		} catch (SQLException e) {
			LOGGER.error("Error find task - SQL error, e");
			throw new DAOException("Error find task - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error find task - pool error, e");
			throw new DAOException("Error find task - pool error", e);
		}
	}
	
	@Override
	public Task findByNameTask(String nameTask) throws DAOException {		

		try (Connection connection = PoolConnection.INSANCE.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(SQL_REQUEST_FIND_TASK_BY_NAME);) {

			preparedStatement.setString(1, nameTask);
			ResultSet resultSet = preparedStatement.executeQuery();

			List<Task> result = buildTaskList(resultSet);
			if (result.size() == 0) {
				LOGGER.error("Error task not found by name");
				return null;
			}
			return result.get(0);

		} catch (SQLException e) {
			LOGGER.error("Error find task - SQL error, e");
			throw new DAOException("Error find task - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error find task - pool error, e");
			throw new DAOException("Error find task - pool error", e);
		}
	}
	
	@Override
	public List<Task> findAllTask() throws DAOException {
		
		try (Connection connection = PoolConnection.INSANCE.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_REQUEST_FIND_ALL_TASK);) {
			
			ResultSet resultSet = preparedStatement.executeQuery();			
			
			return buildTaskList(resultSet);

		} catch (SQLException e) {
			LOGGER.error("Error find all task - SQL error, e");
			throw new DAOException("Error find all task - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error find all task - pool error, e");
			throw new DAOException("Error find all task - pool error", e);
		}
	}
	
	private List<Task> buildTaskList(ResultSet resultSet) throws DAOException {
		List<Task> taskList = new ArrayList<>();		
		Task task;		
		try {
			while (resultSet.next()) {
				task = taskBuilder
						.createNewTask()
						.withId(resultSet.getInt(DBColumnNameConstant.TASK_ID))
						.withNameTask(resultSet.getString(DBColumnNameConstant.TASK_NAME_TASK))
						.withPriceTask(resultSet.getBigDecimal(DBColumnNameConstant.TASK_PRICE_TASK))
						.withIdCourse(courseDAO.findById(resultSet.getInt(DBColumnNameConstant.TASK_ID_COURSE)))
						.withIdSubject(subjectDAO.findById(resultSet.getInt(DBColumnNameConstant.TASK_ID_SUBJECT)))
						.build();
				
				taskList.add(task);
			}
			
		} catch (SQLException e) {
			LOGGER.error("Error build task - SQL error", e);
			throw new DAOException("Error build task - SQL error", e);
		}
		return taskList;
	}

	@Override
	public Task update(Task entity) throws DAOException {
		// SQL_REQUEST_UPDATE_TASK = "UPDATE `task` SET `nameTask` = ?, `priceTask` = ?, `idCourse` = ?, `idSubject` = ? WHERE id = ?;";
		if (entity == null) {
			LOGGER.error("Error update task - task null");
			throw new DAOException("Error update task - task null");
		}
		int result;
		try (Connection connection = PoolConnection.INSANCE.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_REQUEST_UPDATE_TASK);) {

			preparedStatement.setString(1, entity.getNameTask());
			preparedStatement.setBigDecimal(2, entity.getPriceTask());
			preparedStatement.setInt(3, entity.getCourse().getId());
			preparedStatement.setInt(4, entity.getSubject().getId());
			preparedStatement.setInt(5, entity.getId());

			result = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.error("Error update task - SQL error, e");
			throw new DAOException("Error update task - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error update task - pool error, e");
			throw new DAOException("Error update task - pool error", e);
		}
		
		if (result != 1) {
			throw new DAOException("Error update task - task not update");
		}
				
		return findById(entity.getId());
	}
	
	@Override
	public boolean delete(Task entity) throws DAOException {
		
		if (entity == null) {
			LOGGER.error("Error delete task - task null");
			throw new DAOException("Error delete task - task null");
		}

		return delete(entity.getId());
	}

	@Override
	public boolean delete(int id) throws DAOException {
		
		int result;
		try (Connection connection = PoolConnection.INSANCE.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_REQUEST_DELETE_TASK);) {		
			
			preparedStatement.setInt(1, id);			

			result = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.error("Error delete task - SQL error, e");
			throw new DAOException("Error delete task - SQL error", e);
		} catch (PoolException e) {
			LOGGER.error("Error delete task - pool error, e");
			throw new DAOException("Error delete task - pool error", e);
		}		
				
		return (result == 1);	
	}	

}
