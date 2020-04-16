package dao;



import exception.DAOException;

public interface BaseDAO<T> {
	
	T create(T bean) throws DAOException;
	T findById(int id) throws DAOException;
	boolean update(T bean) throws DAOException;
	boolean delete(int id) throws DAOException;
	boolean delete(T bean) throws DAOException;
	

}
