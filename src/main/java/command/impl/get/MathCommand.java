package command.impl.get;

import static command.constant.AttributeNameConstant.*;
import static command.constant.ValueConstant.*;

import javax.servlet.http.HttpServletRequest;

import command.CommandGET;
import command.PageManager;
import static command.constant.ParameterNameConstant.*;
import util.Parser;

public class MathCommand implements CommandGET {

	@Override
	public PageManager execute(HttpServletRequest request) {
		request.setAttribute(MATH_MAIN_MENU_POSITION, ACTIVE);		//for indicate main menu current item
				
		String example = Parser.getStringParameterByName(request, EXAMPLE);
		
		if (example == null || !(example.equals("1") || example.equals("2") || example.equals("3"))) {
			return PageManager.MATH_PAGE;
		}
		
		request.getSession().setAttribute(EXAMPLE_NUMBER, example);
		return PageManager.MATH_EXAMPLE_PAGE;
	}

}
