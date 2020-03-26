package controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import controller.command.Command;
import entity.User;

public class Logout implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		setAttributeForGUI(request);
		return "WEB-INF/view/sign.jsp";
	}
	
	private void setAttributeForGUI(HttpServletRequest request) {
		request.getSession().setAttribute("user", null);
		
		request.setAttribute("signMenuPosition", "active"); // for indicate main menu current item
		request.setAttribute("Login", "active");	// for indicate current tab
	}

}
