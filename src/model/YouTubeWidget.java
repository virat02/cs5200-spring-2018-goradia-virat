package model;

public class YouTubeWidget extends Widget {

	public YouTubeWidget() {
		super();
	}

	public YouTubeWidget(String url, boolean expandable, boolean shareable) {
		super();
		this.url = url;
		this.expandable = expandable;
		this.shareable = shareable;
	}
	public YouTubeWidget(String name, int widgetOrder, String type, Page page,String url, boolean expandable, boolean shareable) {
		super(name, widgetOrder, type, page);
		this.url = url;
		this.expandable = expandable;
		this.shareable = shareable;	
	}

	public YouTubeWidget(int id, String name, int width, int height, String cssClass, String cssStyle, String text,
			int widgetOrder, String type, Page page,String url, boolean expandable, boolean shareable) {
		super(id, name, width, height, cssClass, cssStyle, text, widgetOrder, type, page);
		this.url = url;
		this.expandable = expandable;
		this.shareable = shareable;	
	}

	private String url;
	private boolean expandable;
	private boolean shareable;

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isExpandable() {
		return expandable;
	}
	public void setExpandable(boolean expandable) {
		this.expandable = expandable;
	}
	public boolean isShareable() {
		return shareable;
	}
	public void setShareble(boolean shareable) {
		this.shareable = shareable;
	}

}
