package validator;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import entity.Subject;

public class SubjectValidator {
	private static final Logger LOGGER = LogManager.getLogger(TaskValidator.class.getName());

	public static boolean validate(Subject subject) {
		if (subject == null || StringUtils.isBlank(subject.getNameSubject())) {
			
			LOGGER.error("Error - invalid subject");
			return false;
		}
		
		return true;
	}

}
