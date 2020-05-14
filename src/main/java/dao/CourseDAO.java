package dao;

import java.util.List;

import entity.Course;
import exception.DAOException;

public interface CourseDAO extends AbstractDAO<Course> {
		
	Course findByNumber(int numberCourse) throws DAOException;
	List<Course> findAllCourse() throws DAOException;

}
