package command.impl.get;

import static command.constant.AttributeNameConstant.*;
import static command.constant.ValueConstant.*;
import static command.constant.ParameterNameConstant.*;
import javax.servlet.http.HttpServletRequest;
import command.CommandGET;
import command.PageManager;


public class SignCommand implements CommandGET {

	@Override
	public PageManager execute(HttpServletRequest request) {		
		
		request.setAttribute(SIGN_MAIN_MENU_POSITION, ACTIVE);
		request.setAttribute(request.getParameter(TAB), ACTIVE);
		return PageManager.SIGN_PAGE;		
	}
}
