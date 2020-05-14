package dao;

import java.util.List;

import entity.Subject;
import exception.DAOException;

public interface SubjectDAO extends AbstractDAO<Subject> {	
	
	Subject findByName(String nameSubject) throws DAOException;
	List<Subject> findAllSubject() throws DAOException;

}
