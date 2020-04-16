package dao;

import entity.Status;
import exception.DAOException;

public interface StatusDAO extends BaseDAO<Status> {
	
	Status findByName(String nameStatus) throws DAOException;

}
