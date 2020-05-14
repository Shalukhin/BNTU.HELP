package service;

import org.apache.commons.fileupload.FileItem;

import entity.FinishFile;
import exception.ServiceException;

public interface FinishFileService {
	
	FinishFile addNewFinishFile(String nameFinishFile, FileItem item) throws ServiceException;
	FinishFile takeFinishFileById(int id) throws ServiceException;

}
