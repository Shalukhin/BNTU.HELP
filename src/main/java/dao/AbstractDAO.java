package dao;



import exception.DAOException;

public interface AbstractDAO<T> {
	
	T create(T entity) throws DAOException;
	T findById(int id) throws DAOException;
	T update(T entity) throws DAOException;
	boolean delete(int id) throws DAOException;
	boolean delete(T entity) throws DAOException;
	

}
