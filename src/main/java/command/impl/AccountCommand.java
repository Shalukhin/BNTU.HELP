package command.impl;

import static command.constant.AttributeNameConstant.*;
import static command.constant.AttributeValueConstant.*;
import static command.constant.ParameterNameConstant.*;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import command.Command;
import command.PageManager;
import util.Parser;

public class AccountCommand implements Command {
	
	private final static Logger LOGGER = LogManager.getLogger(AccountCommand.class.getName());	
	
	@Override
	public PageManager execute(HttpServletRequest request) {		
				
		if (request.getSession().getAttribute(USER) == null) {
			request.setAttribute(SIGN_MAIN_MENU_POSITION, ACTIVE);
			request.setAttribute(LOGIN_TAB_POSITION, ACTIVE);
			return PageManager.SIGN_PAGE;
		}			
		
		request.setAttribute(ORDER_FIRST_OPTION, Parser.getStringParameterByName(request, TASK_FOR_ORDER));
		request.setAttribute(ORDER_NOTE, Parser.getStringParameterByName(request, NOTE_FOR_ORDER));
		request.setAttribute(ORDER_INSTRUCTION, Parser.getStringParameterByName(request, INSTRUCTION_FOR_ORDER));			

		request.setAttribute(ACCOUNT_MAIN_MENU_POSITION, ACTIVE);		
		request.setAttribute(Parser.getStringParameterByName(request, TAB), ACTIVE);	//for indicate current tab
		
		return PageManager.ACCOUNT_PAGE;
	}
	
}
