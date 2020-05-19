package command.impl.post;

import javax.servlet.http.HttpServletRequest;

import command.CommandPOST;
import command.URLManager;
import command.constant.QueryURLConstant;

public class ErrorPOSTCommand implements CommandPOST {

	@Override
	public URLManager execute(HttpServletRequest request) {
		
		return new URLManager(QueryURLConstant.ERROR_QUERY_URL);
	}

}
