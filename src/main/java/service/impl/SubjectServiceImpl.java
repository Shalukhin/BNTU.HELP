package service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import dao.SubjectDAO;
import entity.Subject;
import exception.DAOException;
import exception.ServiceException;
import factory.DAOFactory;
import service.SubjectService;

public class SubjectServiceImpl implements SubjectService {

	private static final Logger LOGGER = LogManager.getLogger(SubjectServiceImpl.class.getName());

	private SubjectDAO subjectDAO = DAOFactory.getInstance().getSubjectDAO();

	@Override
	public List<Subject> takeAllSubject() throws ServiceException {

		try {
			return subjectDAO.findAllSubject();
		} catch (DAOException e) {
			LOGGER.error("Error take all subject", e);
			throw new ServiceException("Error take all subject", e);
		}
	}

}
