package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandPOST {
	
	URLManager execute(HttpServletRequest request, HttpServletResponse response);
}
