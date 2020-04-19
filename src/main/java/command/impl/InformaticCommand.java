package command.impl;

import static command.constant.AttributeNameConstant.*;
import static command.constant.AttributeValueConstant.*;
import static command.constant.ParameterNameConstant.*;

import javax.servlet.http.HttpServletRequest;

import command.Command;
import command.PageManager;
import util.Parser;

public class InformaticCommand implements Command {

	@Override
	public PageManager execute(HttpServletRequest request) {
		request.setAttribute(INFORMATIC_MAIN_MENU_POSITION, ACTIVE);		//for indicate main menu current item
		
		boolean isTextbookPage = Boolean.valueOf(Parser.getStringParameterByName(request, TEXTBOOK));
		
		if (isTextbookPage) {
			return PageManager.INFORMATIC_TEXTBOOK_PAGE;
		}
		
		String example = Parser.getStringParameterByName(request, EXAMPLE);
		
		if (example == null || !(example.equals("1") || example.equals("2") || example.equals("3"))) {
			return PageManager.INFORMATIC_PAGE;
		}
		
		request.setAttribute(EXAMPLE_NUMBER, example);
		return PageManager.INFORMATIC_EXAMPLE_PAGE;
	}
	

}
