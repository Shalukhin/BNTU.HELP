package controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import controller.command.Command;

public class Sign implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		setAttributeForGUI(request);
		return "WEB-INF/view/sign.jsp";
		
	}
	
	private void setAttributeForGUI(HttpServletRequest request) {
		request.setAttribute("signMenuPosition", "active");		//for indicate main menu current item
		
		String kindOfSign = request.getParameter("kindOfSign");		//for indicate current tab
		request.setAttribute(kindOfSign, "active");
	}

}
