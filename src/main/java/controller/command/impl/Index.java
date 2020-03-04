package controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import controller.command.Command;

public class Index implements Command{

	@Override
	public String execute(HttpServletRequest request) {
		request.setAttribute("indexMenuPosition", "active");
		return "WEB-INF/view/index.jsp";
	}

}
