package controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import controller.command.Command;

public class Chemistry implements Command{

	@Override
	public String execute(HttpServletRequest request) {
		setAttributeForGUI(request);
		return "WEB-INF/view/chemistry/chemistry.jsp";
	}
	
	private void setAttributeForGUI(HttpServletRequest request) {
		request.setAttribute("chemistryMenuPosition", "active");		//for indicate main menu current item
		
		String kindOfChemistry = request.getParameter("kindOfChemistry");		//for indicate current tab
		request.setAttribute(kindOfChemistry, "active");
	}

}
