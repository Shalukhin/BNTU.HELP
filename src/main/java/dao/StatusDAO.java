package dao;

import java.util.List;

import entity.Status;
import exception.DAOException;

public interface StatusDAO extends AbstractDAO<Status> {
	
	Status findByName(String nameStatus) throws DAOException;
	List<Status> findAllStatus() throws DAOException;

}
