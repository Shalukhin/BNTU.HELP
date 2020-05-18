package service.impl;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import builder.FinishFileBuilder;
import dao.FinishFileDAO;
import entity.FinishFile;
import exception.DAOException;
import exception.ServiceException;
import factory.DAOFactory;
import service.FinishFileService;

public class FinishFileServiceImpl implements FinishFileService {
	
private static final Logger LOGGER = LogManager.getLogger(FinishFileServiceImpl.class.getName());
	
	private FinishFileDAO finishFileDAO = DAOFactory.getInstance().getFinishFileDAO();
	private FinishFileBuilder finishFileBuilder = new FinishFileBuilder();

	@Override
	public FinishFile takeFinishFileById(int id) throws ServiceException {
		
		try {
			return finishFileDAO.findById(id);
		} catch (DAOException e) {
			LOGGER.error("Error take finish file", e);
			throw new ServiceException("Error take finish file", e);
		}
	}

	@Override
	public FinishFile addNewFinishFile(String nameFinishFile, FileItem item) throws ServiceException {	
		
		if (StringUtils.isBlank(item.getName()) || item.getSize() == 0) {
			return null;
		}

		try {
			
			FinishFile finishFile = finishFileBuilder
					.createNewFinishFile()
					.withNameFinishFile(nameFinishFile)
					.withDataFinishFile(item.getInputStream())
					.build();
			
			System.out.println(finishFile);
			return finishFileDAO.create(finishFile);
			
		} catch (Exception e) {
			LOGGER.error("Error add new finish file", e);
			throw new ServiceException("Error add new finish file", e);
		}	
	}

}
