package command.impl;

import javax.servlet.http.HttpServletRequest;

import command.Command;
import command.PageManager;
import static command.constant.AttributeNameConstant.*;
import static command.constant.AttributeValueConstant.*;

public class PayCommand implements Command {

	@Override
	public PageManager execute(HttpServletRequest request) {
		
		request.setAttribute(PAY_MAIN_MENU_POSITION, ACTIVE);
		
		return PageManager.PAY_PAGE;
	}

}
