package jp.ne.ravi.web.dto;

import java.io.Serializable;

public class WebNewDto implements Serializable{

	private static final long serialVersionUID = -2824350901816940935L;

	private String title;

	private String url;

	private String newFlag;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNewFlag() {
		return newFlag;
	}

	public void setNewFlag(String newFlag) {
		this.newFlag = newFlag;
	}

}
