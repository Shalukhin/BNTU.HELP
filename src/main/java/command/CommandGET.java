package command;

import javax.servlet.http.HttpServletRequest;

public interface CommandGET {
	PageManager execute(HttpServletRequest request);
}
