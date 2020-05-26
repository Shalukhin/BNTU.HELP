package command.constant;

public enum QueryURLConstant {
	
	ACCOUNT_QUERY_URL 			("/BNTU.HELP/do?command=account"),
	SIGN_QUERY_URL				("/BNTU.HELP/do?command=sign"),
	ERROR_QUERY_URL				("/BNTU.HELP/do?command=error"),
	PAY_QUERY_URL				("/BNTU.HELP/do?command=pay"),	
	ADMINISTRATOR_QUERY_URL		("/BNTU.HELP/do?command=administrator"),
	CHANGE_LANGUAGE_QUERY_URL	("");
	
	private String url;
	
	private QueryURLConstant(String url) {
		this.url = url;
	}	

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}	
	
}
