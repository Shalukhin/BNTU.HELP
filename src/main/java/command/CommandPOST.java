package command;

import javax.servlet.http.HttpServletRequest;

public interface CommandPOST {
	
	URLManager execute(HttpServletRequest request);
}
