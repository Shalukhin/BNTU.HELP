package command.impl;

import javax.servlet.http.HttpServletRequest;

import command.Command;
import command.PageManager;

public class ErrorCommand
implements Command {

	@Override
	public PageManager execute(HttpServletRequest request) {
		return PageManager.ERROR_PAGE;
	}
}
