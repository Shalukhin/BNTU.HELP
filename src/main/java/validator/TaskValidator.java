package validator;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import entity.Task;

public class TaskValidator {
	
	private static final Logger LOGGER = LogManager.getLogger(TaskValidator.class.getName());
	
	public static boolean validate(Task task) {
		if (task == null || 
				StringUtils.isBlank(task.getNameTask()) ||
				task.getPriceTask().doubleValue() < 0 ||
				task.getCourse() == null ||
				task.getSubject() == null) {
			
			LOGGER.error("Error - invalid task");
			return false;			
		}
		
		return true;			
	}	
}
