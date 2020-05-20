package command.impl.post;

import static command.constant.AttributeNameConstant.*;
import static command.constant.ParameterNameConstant.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandPOST;
import command.URLManager;
import command.constant.QueryURLConstant;
import static command.constant.ValueConstant.*;
import util.Parser;

public class PrepareOrderCommand implements CommandPOST {

	@Override
	public URLManager execute(HttpServletRequest request, HttpServletResponse response) {

		request.getSession().setAttribute(ORDER_FIRST_OPTION, Parser.getStringParameterByName(request, TASK_FOR_ORDER));
		request.getSession().setAttribute(ORDER_NOTE, Parser.getStringParameterByName(request, NOTE_FOR_ORDER));
		request.getSession().setAttribute(ORDER_INSTRUCTION, Parser.getStringParameterByName(request, INSTRUCTION_FOR_ORDER));
		
		return new URLManager(QueryURLConstant.ACCOUNT_QUERY_URL).addParameterURL(TAB, ORDER_TAB_POSITION);
	}
}
