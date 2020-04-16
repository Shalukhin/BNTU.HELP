package command;

import static command.constant.PageURLConstant.*;

public enum PageManager {
	
	SIGN_PAGE		(SIGN_PAGE_URL),
	ACCOUNT_PAGE	(ACCOUNT_PAGE_URL),
	CHEMISTRY_PAGE	(CHEMISTRY_PAGE_URL),
	INORGANIC_PAGE	(INORGANIC_PAGE_URL),
	ORGANIC_PAGE	(ORGANIC_PAGE_URL),
	ANALITIC_PAGE	(ANALITIC_PAGE_URL),
	ERROR_PAGE		(ERROR_PAGE_URL),
	CONTACT_PAGE	(CONTACT_PAGE_URL),
	INDEX_PAGE		(INDEX_PAGE_URL),
	
	
	
	;
	
	private String url;

	private PageManager(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
	
}
