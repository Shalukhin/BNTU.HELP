package service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import builder.TaskBuilder;
import dao.CourseDAO;
import dao.SubjectDAO;
import dao.TaskDAO;
import entity.Course;
import entity.Subject;
import entity.Task;
import exception.DAOException;
import exception.ServiceException;
import factory.DAOFactory;
import service.TaskService;
import util.validator.TaskValidator;

public class TaskServiceImpl implements TaskService {
	
	private static final Logger LOGGER = LogManager.getLogger(TaskServiceImpl.class.getName());
	
	private TaskDAO taskDAO = DAOFactory.getInstance().getTaskDAO();
	private CourseDAO courseDAO = DAOFactory.getInstance().getCourseDAO();
	private SubjectDAO subjectDAO = DAOFactory.getInstance().getSubjectDAO();
	private TaskBuilder taskBuilder = new TaskBuilder();

	@Override
	public List<Task> takeAllTask() throws ServiceException {
		
		try {
			return taskDAO.findAllTask();
		} catch (DAOException e) {
			LOGGER.error("Error take all task", e);
			throw new ServiceException("Error take all task", e);
		}
	}

	@Override
	public Task takeTaskByName(String nameTask) throws ServiceException {
		
		try {
			return taskDAO.findByNameTask(nameTask);
		} catch (DAOException e) {
			LOGGER.error("Error take task by name <" + nameTask + ">", e);
			throw new ServiceException("Error take task by name <" + nameTask + ">", e);
		}
	}

	@Override
	public Task addNewTask(String nameTaskStr, String priceTaskStr, String courseNumberStr, String subjectNameStr) throws ServiceException {
		
		Task task = buildTask(nameTaskStr, priceTaskStr, courseNumberStr, subjectNameStr);
		
		if (!TaskValidator.validate(task)) {
			LOGGER.error("Error add new task - invalid task");			
			return null;
		}		
		
		try {
			return taskDAO.create(task);
		} catch (DAOException e) {
			LOGGER.error("Error add new task", e);
			throw new ServiceException("Error add new task", e);
		}
	}

	@Override
	public Task editTask(String idStr, String nameTaskStr, String priceTaskStr, String courseNumberStr, String subjectNameStr) throws ServiceException {
		
		Task modifiedTask = buildTask(nameTaskStr, priceTaskStr, courseNumberStr, subjectNameStr);		
		
		if (!TaskValidator.validate(modifiedTask)) {
			LOGGER.error("Error update task - invalid task");			
			return null;
		}
		
		modifiedTask.setId(Integer.valueOf(idStr));
		
		try {
			return taskDAO.update(modifiedTask);
		} catch (DAOException e) {
			LOGGER.error("Error update task", e);
			throw new ServiceException("Error update task", e);
		}		
	}
	
	private Task buildTask(String nameTaskStr, String priceTaskStr, String courseNumberStr, String subjectNameStr) {
		BigDecimal priceTask = null;		
		try {
			priceTask = new BigDecimal(priceTaskStr);
		} catch (NumberFormatException e){
			LOGGER.error("Error build task - invalid price task", e);			
			return null;
		}
		
		Course courseTask = null;
		
		try {
			courseTask = courseDAO.findByNumber(Integer.valueOf(courseNumberStr));
		} catch (Exception e) {
			LOGGER.error("Error build task - invalid course task", e);			
			return null;			
		}
		
		Subject subjectTask = null;
		try {
			subjectTask = subjectDAO.findByName(subjectNameStr);
		} catch (DAOException e) {
			LOGGER.error("Error build task - invalid subject task", e);			
			return null;
		}
		
		Task task = taskBuilder
						.createNewTask()
						.withNameTask(nameTaskStr)
						.withPriceTask(priceTask)
						.withIdCourse(courseTask)
						.withIdSubject(subjectTask)
						.build();
		
		return task;
	}

	@Override
	public boolean deleteTask(String idStr) throws ServiceException {
		try {
			return taskDAO.delete(Integer.valueOf(idStr));
		} catch (Exception e) {
			LOGGER.error("Error delete task", e);
			throw new ServiceException("Error delete task", e);
		} 
	}

}
