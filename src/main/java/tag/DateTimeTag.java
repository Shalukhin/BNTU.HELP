package tag;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import static command.constant.ValueConstant.*;

public class DateTimeTag extends TagSupport {
	
	private static final long serialVersionUID = 5166438846584970397L;
	
	private LocalDateTime value;

	public void setValue(LocalDateTime value) {
		this.value = value;
	}

	@Override
	public int doStartTag() throws JspException {
		
		String dateTimeStr = value.format(DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN_FOR_WEB_TABLE));
		
		JspWriter writerOut = pageContext.getOut();
		try {
			writerOut.write(dateTimeStr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {		
		return EVAL_PAGE;
	}
	
	

}
