package service;

import java.util.List;

import entity.Subject;
import exception.ServiceException;

public interface SubjectService {
	
	List<Subject> takeAllSubject() throws ServiceException;

}
