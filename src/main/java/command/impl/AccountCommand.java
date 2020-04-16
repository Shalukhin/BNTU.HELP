package command.impl;

import static command.constant.AttributeNameConstant.*;
import static command.constant.AttributeValueConstant.*;

import javax.servlet.http.HttpServletRequest;

import command.Command;
import command.PageManager;

public class AccountCommand implements Command {

	@Override
	public PageManager execute(HttpServletRequest request) {
		if (request.getSession().getAttribute(USER) == null) {
			request.setAttribute(SIGN_MAIN_MENU_POSITION, ACTIVE);
			request.setAttribute(LOGIN_SUBMENY_POSITION, ACTIVE);
			return PageManager.SIGN_PAGE;
		}
		request.setAttribute(ACCOUNT_MAIN_MENU_POSITION, ACTIVE);
		return PageManager.ACCOUNT_PAGE;
	}
	
}
