package controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import controller.command.Command;

public class Chemistry implements Command{

	@Override
	public String execute(HttpServletRequest request) {
		String kindOfChemistry = request.getParameter("kindOfChemistry");		
		request.setAttribute("chemistryMenuPosition", "active");
		request.setAttribute(kindOfChemistry, "active");
		return "WEB-INF/view/chemistry/chemistry.jsp";
	}

}
