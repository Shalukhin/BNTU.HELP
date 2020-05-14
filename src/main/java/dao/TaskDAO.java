package dao;

import java.util.List;
import entity.Task;
import exception.DAOException;

public interface TaskDAO extends AbstractDAO<Task> {
	
	List<Task> findAllTask() throws DAOException;
	Task findByNameTask(String nameTask) throws DAOException;

}
