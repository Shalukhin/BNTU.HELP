package controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import controller.command.Command;

public class Index implements Command{

	@Override
	public String execute(HttpServletRequest request) {
		setAttributeForGUI(request);
		return "WEB-INF/view/index.jsp";
	}
	
	private void setAttributeForGUI(HttpServletRequest request) {
		request.setAttribute("indexMenuPosition", "active");		//for indicate main menu current item
		HttpSession session = request.getSession();
		Object user = session.getAttribute("user");
		if (user == null) {
			
		}
	}

}
