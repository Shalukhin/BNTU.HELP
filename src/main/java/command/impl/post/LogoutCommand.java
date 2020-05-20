package command.impl.post;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import command.CommandPOST;
import command.URLManager;
import static command.constant.AttributeNameConstant.*;
import static command.constant.ParameterNameConstant.*;
import static command.constant.QueryURLConstant.*;
import static command.constant.ValueConstant.*;


public class LogoutCommand implements CommandPOST {
	
	private static final String COOKIE_LOGIN = "Login";
	private static final String COOKIE_PASSWORD = "Password";
	

	@Override
	public URLManager execute(HttpServletRequest request, HttpServletResponse response) {
		
		Cookie[] cookies = request.getCookies();
		
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(COOKIE_LOGIN) || cookie.getName().equals(COOKIE_PASSWORD)) {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}			
		}
		
		request.getSession().removeAttribute(USER);		
		request.getSession().removeAttribute(LIST_ALL_TASK);
		request.getSession().removeAttribute(LIST_USER_ORDER);
		request.getSession().removeAttribute(LIST_EXECUTE_ORDER);	
		request.getSession().removeAttribute(LIST_COMPLETE_ORDER);	
				
		return new URLManager(SIGN_QUERY_URL).addParameterURL(TAB, LOGIN_TAB_POSITION);
	}	
	
}
