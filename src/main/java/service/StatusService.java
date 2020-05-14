package service;

import java.util.List;

import entity.Status;
import exception.ServiceException;

public interface StatusService {
	
	List<Status> takeAllStatus() throws ServiceException;

}
