package command.impl.get;

import javax.servlet.http.HttpServletRequest;

import command.CommandGET;
import command.PageManager;

public class ErrorCommand
implements CommandGET {

	@Override
	public PageManager execute(HttpServletRequest request) {
		return PageManager.ERROR_PAGE;
	}
}
