package service;

import java.util.List;

import entity.Course;
import exception.ServiceException;

public interface CourseService {
	
	List<Course> takeAllCourse() throws ServiceException;

}
