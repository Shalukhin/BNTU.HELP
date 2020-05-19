package command.impl.get;


import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import command.CommandGET;
import command.PageManager;

public class TestCommand implements CommandGET {

	@Override
	public PageManager execute(HttpServletRequest request) {
		System.out.println("Hi");		
		
		Enumeration<Object> params = request.getParameterNames();
		StringBuilder sb = new StringBuilder();	
		
		while (params.hasMoreElements()) {
			
			String par = params.nextElement().toString();
			System.out.print(par);
			sb.append(par);
			System.out.print("=");
			sb.append("=");
			System.out.println(request.getParameter(par));
			sb.append(request.getParameter(par));
			sb.append("<br>");			
		}		
		request.setAttribute("xxx", sb.toString());
		
		return PageManager.TEST_PAGE;
	}
}
