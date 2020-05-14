package service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import dao.CourseDAO;
import entity.Course;
import exception.DAOException;
import exception.ServiceException;
import factory.DAOFactory;
import service.CourseService;

public class CourseServiceImpl implements CourseService {
	
	private static final Logger LOGGER = LogManager.getLogger(CourseServiceImpl.class.getName());

	private CourseDAO courseDAO = DAOFactory.getInstance().getCourseDAO();

	@Override
	public List<Course> takeAllCourse() throws ServiceException {
		
		try {
			return courseDAO.findAllCourse();
		} catch (DAOException e) {
			LOGGER.error("Error take all course", e);
			throw new ServiceException("Error take all course", e);
		}
	}

}
