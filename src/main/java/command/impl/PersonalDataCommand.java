package command.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import builder.PersonalDataBuilder;
import command.Command;
import command.PageManager;
import command.constant.ParameterNameConstant;
import entity.PersonalData;
import entity.User;
import exception.ServiceException;
import factory.ServiceFactory;
import service.PersonalDataService;
import service.impl.PersonalDataServiceImpl;
import util.Parser;

import static command.constant.AttributeNameConstant.*;
import static command.constant.AttributeValueConstant.*;
import static command.constant.ParameterNameConstant.*;

import java.math.BigDecimal;

public class PersonalDataCommand implements Command {
	
	private static final Logger LOGGER = LogManager.getLogger(PersonalDataServiceImpl.class.getName());
	
	private PersonalDataBuilder personalDataBuilder = new PersonalDataBuilder();
	private PersonalDataService personalDataService = ServiceFactory.getInstance().getPersonalDataService();

	@Override
	public PageManager execute(HttpServletRequest request) {
		
		User currentUser = (User) request.getSession().getAttribute(USER);
		
		if (currentUser == null) {
			request.setAttribute(SIGN_MAIN_MENU_POSITION, ACTIVE);
			request.setAttribute(LOGIN_SUBMENY_POSITION, ACTIVE);
			return PageManager.SIGN_PAGE;
		}
						
		PersonalData personalDataFromGUI = personalDataBuilder
											.createNewPersonalData()
											.withIdUser(currentUser.getId())
											.withName(Parser.getStringParameterByName(request, NAME))
											.withPhone(Parser.getStringParameterByName(request, PHONE))
											.withEmail(Parser.getStringParameterByName(request, EMAIL))
											.build();
		
		PersonalData personalDataFromDB;
		try {
			personalDataFromDB = personalDataService.saveUserPersonalData(personalDataFromGUI);
		} catch (ServiceException e) {
			LOGGER.error("Error personal data", e);
			return PageManager.ERROR_PAGE;
		}
		
		request.getSession().setAttribute(PERSONAL_DATA, personalDataFromDB);
		request.setAttribute(MESSAGE_PERSONAL, SAVED);
		request.setAttribute(ACCOUNT_MAIN_MENU_POSITION, ACTIVE);		
		request.setAttribute(Parser.getStringParameterByName(request, KIND_OF_ACCOUNT), ACTIVE);	//for indicate current tab
		return PageManager.ACCOUNT_PAGE;
	}

}
