package controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import controller.command.Command;

public class ErrorProcess
implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		return "view/error.jsp";
	}
}
