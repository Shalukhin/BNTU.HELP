package controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import controller.command.Command;

public class Contact implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		
		request.setAttribute("contactMenuPosition", "active");
		return "WEB-INF/view/contact.jsp";
	}

}
