package service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.PersonalDataDAO;
import entity.PersonalData;
import exception.DAOException;
import exception.ServiceException;
import factory.DAOFactory;
import service.PersonalDataService;
import util.validator.PersonalDataValidator;

public class PersonalDataServiceImpl implements PersonalDataService {
	
	private static final Logger LOGGER = LogManager.getLogger(PersonalDataServiceImpl.class.getName());
	
	private PersonalDataDAO personalDataDAO = DAOFactory.getInstance().getPersonalDataDAO();

	@Override
	public PersonalData saveUserPersonalData(PersonalData personalData) throws ServiceException {
		if (!PersonalDataValidator.validate(personalData)) {
			LOGGER.error("Error save personal data - invalid personal data");
			return null;
		}		
		
		try {
			PersonalData existPersonalData = personalDataDAO.findById(personalData.getIdUser());			
			
			if (existPersonalData != null) {
				personalData.setBonusMoney(existPersonalData.getBonusMoney());
				personalData.setIdInvitingUser(existPersonalData.getIdInvitingUser());
				return personalDataDAO.update(personalData);
			}			
			return personalDataDAO.create(personalData);
		} catch (DAOException e) {
			LOGGER.error("Error save personal data", e);
			throw new ServiceException("Error save personal data", e);
		}
	}

	@Override
	public PersonalData takeUserPersonalData(int idUser) throws ServiceException {
		
		try {
			return personalDataDAO.findById(idUser);
		} catch (DAOException e) {
			LOGGER.error("Error take personal data", e);
			throw new ServiceException("Error take personal data", e);
		}
	}

}
