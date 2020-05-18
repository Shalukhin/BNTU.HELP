package service.impl;

import static command.constant.ParameterNameConstant.EMAIL;
import static command.constant.ParameterNameConstant.NAME;
import static command.constant.ParameterNameConstant.PHONE;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import builder.PersonalDataBuilder;
import dao.PersonalDataDAO;
import entity.PersonalData;
import entity.User;
import exception.DAOException;
import exception.ServiceException;
import factory.DAOFactory;
import service.PersonalDataService;
import util.Parser;
import util.validator.PersonalDataValidator;

public class PersonalDataServiceImpl implements PersonalDataService {
	
	private static final Logger LOGGER = LogManager.getLogger(PersonalDataServiceImpl.class.getName());
	
	private PersonalDataDAO personalDataDAO = DAOFactory.getInstance().getPersonalDataDAO();
	private PersonalDataBuilder personalDataBuilder = new PersonalDataBuilder();
		
	
//	@Override
//	public PersonalData takeUserPersonalData(int id) throws ServiceException {
//		
//		try {
//			return personalDataDAO.findById(id);
//		} catch (DAOException e) {
//			LOGGER.error("Error take personal data", e);
//			throw new ServiceException("Error take personal data", e);
//		}
//	}

}
