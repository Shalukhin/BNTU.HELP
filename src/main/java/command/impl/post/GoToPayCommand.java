package command.impl.post;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandPOST;
import command.URLManager;
import static command.constant.ParameterNameConstant.*;
import command.constant.QueryURLConstant;
import util.Parser;

public class GoToPayCommand implements CommandPOST {

	@Override
	public URLManager execute(HttpServletRequest request, HttpServletResponse response) {
		
		String idOrderForPay = Parser.getStringParameterByName(request, GO_TO_PAY_ORDER_ID);
		if (idOrderForPay == null) {
			return new URLManager(QueryURLConstant.PAY_QUERY_URL);
		}
		return new URLManager(QueryURLConstant.PAY_QUERY_URL).addParameterURL(GO_TO_PAY_ORDER_ID, idOrderForPay);
	}

}
