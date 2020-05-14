package service;

import java.util.List;

import entity.Task;
import exception.ServiceException;

public interface TaskService {
	
	Task addNewTask(String nameTaskStr, String priceTaskStr, String courseNumberStr, String subjectNameStr) throws ServiceException;
	Task editTask(String idStr, String nameTaskStr, String priceTaskStr, String courseNumberStr, String subjectNameStr) throws ServiceException;
	boolean deleteTask(String idStr) throws ServiceException;
	List<Task> takeAllTask() throws ServiceException;
	Task takeTaskByName(String nameTask) throws ServiceException;

}
