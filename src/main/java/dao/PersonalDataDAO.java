package dao;

import entity.PersonalData;
import exception.DAOException;

public interface PersonalDataDAO extends BaseDAO<PersonalData> {
	
	boolean checkExistIdUser(int idUser) throws DAOException;

}
