package command.impl;

import javax.servlet.http.HttpServletRequest;

import command.Command;
import command.PageManager;
import static command.constant.AttributeNameConstant.*;
import static command.constant.AttributeValueConstant.*;


public class LogoutCommand implements Command {

	@Override
	public PageManager execute(HttpServletRequest request) {
		
		request.getSession().setAttribute(USER, null);
		request.setAttribute(SIGN_MAIN_MENU_POSITION, ACTIVE);		// for indicate main menu current item
		request.setAttribute(LOGIN_SUBMENY_POSITION, ACTIVE);		// for indicate current tab
		
		return PageManager.SIGN_PAGE;
	}	
	
}
