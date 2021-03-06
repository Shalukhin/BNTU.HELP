package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter {
	
	String encoding;
    
    public void init(FilterConfig fConfig) throws ServletException {
		encoding = fConfig.getInitParameter("encoding");
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		if (encoding != null) {
			if (!encoding.equalsIgnoreCase(request.getCharacterEncoding())) {
				request.setCharacterEncoding(encoding);
			}
			if (!encoding.equalsIgnoreCase(response.getCharacterEncoding())) {
				response.setCharacterEncoding(encoding);
			}
		}		
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		
	}		
	
}
