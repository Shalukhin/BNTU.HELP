package command.impl.post;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandPOST;
import command.URLManager;
import command.constant.QueryURLConstant;

public class GoToPayCommand implements CommandPOST {

	@Override
	public URLManager execute(HttpServletRequest request, HttpServletResponse response) {
		
		return new URLManager(QueryURLConstant.PAY_QUERY_URL);
	}

}
