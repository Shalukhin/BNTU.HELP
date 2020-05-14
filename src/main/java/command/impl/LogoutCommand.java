package command.impl;

import javax.servlet.http.HttpServletRequest;

import command.Command;
import command.PageManager;
import static command.constant.AttributeNameConstant.*;
import static command.constant.AttributeValueConstant.*;


public class LogoutCommand implements Command {

	@Override
	public PageManager execute(HttpServletRequest request) {
		
		request.getSession().removeAttribute(USER);
		request.getSession().removeAttribute(PERSONAL_DATA);
		request.getSession().removeAttribute(LIST_ALL_TASK);
		request.getSession().removeAttribute(LIST_USER_ORDER);		
		request.setAttribute(SIGN_MAIN_MENU_POSITION, ACTIVE);		// for indicate main menu current item
		request.setAttribute(LOGIN_TAB_POSITION, ACTIVE);		// for indicate current tab
		
		return PageManager.SIGN_PAGE;
	}	
	
}
