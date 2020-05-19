package command.impl.get;

import javax.servlet.http.HttpServletRequest;

import command.CommandGET;
import command.PageManager;
import static command.constant.AttributeNameConstant.*;
import static command.constant.ValueConstant.*;

public class PayCommand implements CommandGET {

	@Override
	public PageManager execute(HttpServletRequest request) {
		
		request.setAttribute(PAY_MAIN_MENU_POSITION, ACTIVE);		
		return PageManager.PAY_PAGE;
	}
}
