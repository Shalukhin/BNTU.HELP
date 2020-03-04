package controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import controller.command.Command;

public class Inorganic implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		return "WEB-INF/view/chemistry/inorganic.jsp";
	}

}
