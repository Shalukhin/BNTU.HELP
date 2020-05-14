package dao;

import entity.PersonalData;
import exception.DAOException;

public interface PersonalDataDAO extends AbstractDAO<PersonalData> {
	
	boolean checkExistIdUser(int idUser) throws DAOException;

}
