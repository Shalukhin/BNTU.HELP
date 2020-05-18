package command.impl;

import static command.constant.AttributeNameConstant.*;
import static command.constant.AttributeValueConstant.*;

import javax.servlet.http.HttpServletRequest;

import command.Command;
import command.PageManager;

public class AboutCommand implements Command {

	@Override
	public PageManager execute(HttpServletRequest request) {
		
		request.setAttribute(ABOUT_MAIN_MENU_POSITION, ACTIVE);		//for indicate main menu current item
		return PageManager.ABOUT_PAGE;
	}

}
