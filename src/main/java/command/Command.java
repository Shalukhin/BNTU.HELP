package command;

import javax.servlet.http.HttpServletRequest;

public interface Command {
	PageManager execute(HttpServletRequest request);

}
