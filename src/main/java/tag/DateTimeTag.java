package tag;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static command.constant.ValueConstant.*;

public class DateTimeTag extends TagSupport {
	
	private static final long serialVersionUID = 5166438846584970397L;
	
	private final static Logger LOGGER = LogManager.getLogger(DateTimeTag.class.getName());
	
	private LocalDateTime value;

	public void setValue(LocalDateTime value) {
		this.value = value;
	}

	@Override
	public int doStartTag() throws JspException {
		
		String dateTimeStr;
		
		if (value instanceof LocalDateTime) {
			dateTimeStr = value.format(DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN_FOR_WEB_TABLE));
		} else {
			dateTimeStr = "";
		}
		
		JspWriter writerOut = pageContext.getOut();
		try {
			writerOut.write(dateTimeStr);
		} catch (IOException e) {
			LOGGER.error("Error - tag date-time not executed");
		}
		
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {		
		return EVAL_PAGE;
	}	

}
