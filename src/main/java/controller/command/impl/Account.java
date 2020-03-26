package controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import controller.command.Command;

public class Account implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		setAttributeForGUI(request);
		return "WEB-INF/view/account.jsp";
	}

	private void setAttributeForGUI(HttpServletRequest request) {
		request.setAttribute("accountMenuPosition", "active"); // for indicate main menu current item
	}

}
