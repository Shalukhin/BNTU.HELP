package validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import entity.Course;

public class CourseValidator {
	
	private static final Logger LOGGER = LogManager.getLogger(CourseValidator.class.getName());
	
	public static boolean validate(Course course) {
		
		if(course == null || course.getNumberCourse() < 1) {
			
			LOGGER.error("Error - invalid corse");
			return false;
		}
		
		return true;
	}
}
