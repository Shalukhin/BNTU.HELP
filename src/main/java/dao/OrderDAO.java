package dao;

import java.util.List;

import entity.Order;
import exception.DAOException;

public interface OrderDAO extends AbstractDAO<Order> {
	
	List<Order> findAllOrderForExecuteByIdUser(int idUser) throws DAOException;
	List<Order> findAllCompleteOrder() throws DAOException;
	List<Order> findAllOrderForExecuteBySubjectNameByIdUser (String nameSubject, int idUser) throws DAOException;
	List<Order> findAllCompleteOrderBySubjectNameByIdUser (String nameSubject, int idUser) throws DAOException;
	List<Order> findAllOrderByIdUser (int idUser) throws DAOException;
	

}
