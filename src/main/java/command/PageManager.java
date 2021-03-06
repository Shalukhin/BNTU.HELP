package command;

import static command.constant.PageURLConstant.*;

public enum PageManager {
	
	INDEX_PAGE				(INDEX_PAGE_URL),
	ABOUT_PAGE				(ABOUT_PAGE_URL),
	SIGN_PAGE				(SIGN_PAGE_URL),
	ADMINISTATOR_PAGE		(ADMINISTATOR_URL),
	ACCOUNT_PAGE			(ACCOUNT_PAGE_URL),
	ORDER_PAGE				(ORDER_PAGE_URL),
	CHEMISTRY_PAGE			(CHEMISTRY_PAGE_URL),
	INORGANIC_PAGE			(INORGANIC_PAGE_URL),
	ORGANIC_PAGE			(ORGANIC_PAGE_URL),
	ANALITIC_PAGE			(ANALITIC_PAGE_URL),
	MATH_PAGE				(MATH_PAGE_URL),
	MATH_EXAMPLE_PAGE		(MATH_EXAMPLE_PAGE_URL),
	ENGIN_PAGE				(ENGIN_PAGE_URL),
	ENGIN_EXAMPLE_PAGE		(ENGIN_EXAMPLE_PAGE_URL),
	INFORMATIC_PAGE			(INFORMATIC_PAGE_URL),
	INFORMATIC_EXAMPLE_PAGE	(INFORMATIC_EXAMPLE_PAGE_URL),
	INFORMATIC_TEXTBOOK_PAGE(INFORMATIC_TEXTBOOK_PAGE_URL),
	ERROR_PAGE				(ERROR_PAGE_URL),
	CONTACT_PAGE			(CONTACT_PAGE_URL),
	PAY_PAGE				(PAY_PAGE_URL),	
	TEST_PAGE				(TEST_PAGE_URL);
	
	private String url;

	private PageManager(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}	
	
}
