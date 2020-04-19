package service;

import entity.PersonalData;
import exception.ServiceException;

public interface PersonalDataService {
	
	PersonalData saveUserPersonalData (PersonalData personalData) throws ServiceException;
	PersonalData takeUserPersonalData (int idUser) throws ServiceException;
	
}
