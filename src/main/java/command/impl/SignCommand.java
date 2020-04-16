package command.impl;

import static command.constant.AttributeNameConstant.*;
import static command.constant.AttributeValueConstant.*;
import static command.constant.ParameterNameConstant.*;

import javax.servlet.http.HttpServletRequest;

import command.Command;
import command.PageManager;


public class SignCommand implements Command {

	@Override
	public PageManager execute(HttpServletRequest request) {
		request.setAttribute(SIGN_MAIN_MENU_POSITION, ACTIVE);
		request.setAttribute(request.getParameter(KIND_OF_SIGN), ACTIVE);
		return PageManager.SIGN_PAGE;
		
	}

}
