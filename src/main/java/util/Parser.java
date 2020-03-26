package util;

import javax.servlet.http.HttpServletRequest;

public class Parser {
	
	public static String getStringParameterByName(HttpServletRequest request, String name) {
		return request.getParameter(name);
	}

}
