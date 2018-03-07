package model;

public class HtmlWidget extends Widget {

	private String html;
	
	public HtmlWidget() {
		super();
	}

	public HtmlWidget(String html) {
		super();
		this.html = html;
	}
	
	public HtmlWidget(String name, int widgetOrder, String type, Page page,String html) {
		super(name, widgetOrder, type, page);
		this.html = html;
	}
	
	public HtmlWidget(int id, String name, int width, int height, String cssClass, String cssStyle, String text,
			int widgetOrder, String type, Page page,String html) {
		super(id, name, width, height, cssClass, cssStyle, text, widgetOrder, type, page);
		this.html = html;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}
}
