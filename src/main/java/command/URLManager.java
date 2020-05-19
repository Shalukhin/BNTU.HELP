package command;

import command.constant.QueryURLConstant;

public class URLManager {
	
	private String url = "";
	
	public URLManager(QueryURLConstant query) {
		this.url = query.getUrl();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public URLManager addParameterURL(String name, String value) {
		StringBuilder sb = new StringBuilder(url);
		sb.append("&");
		sb.append(name);
		sb.append("=");
		sb.append(value);		
		
		setUrl(sb.toString());
		
		return this;
	}

}
