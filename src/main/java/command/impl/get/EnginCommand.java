package command.impl.get;

import static command.constant.AttributeNameConstant.*;
import static command.constant.ValueConstant.*;
import static command.constant.ParameterNameConstant.EXAMPLE;

import javax.servlet.http.HttpServletRequest;

import command.CommandGET;
import command.PageManager;
import util.Parser;

public class EnginCommand implements CommandGET {
	
	private static final int POSITION_NUMBER_IN_URL = 24;
	
	@Override
	public PageManager execute(HttpServletRequest request) {
		
		request.setAttribute(ENGIN_MAIN_MENU_POSITION, ACTIVE); // for indicate main menu current item

		String example = Parser.getStringParameterByName(request, EXAMPLE);

		if (example == null || !(example.equals("1") || example.equals("2") || example.equals("3")
				|| example.equals("4") || example.equals("5") || example.equals("6"))) {
			return PageManager.ENGIN_PAGE;
		}
		StringBuilder stringBuilder = new StringBuilder(PageManager.ENGIN_EXAMPLE_PAGE.getUrl());
		stringBuilder.setCharAt(POSITION_NUMBER_IN_URL, example.charAt(0));
		PageManager.ENGIN_EXAMPLE_PAGE.setUrl(stringBuilder.toString());

		return PageManager.ENGIN_EXAMPLE_PAGE;
	}

}
