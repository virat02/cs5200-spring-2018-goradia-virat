package model;

public class Widget {

	public Widget() {
		super();
	}
	
	public Widget(String name, String text, int widgetOrder, String type) {
		super();
		this.name = name;
		this.text = text;
		this.widgetOrder = widgetOrder;
		this.type = type;
	}

	public Widget(String name, int widgetOrder, String type, Page page) {
		super();
		this.name = name;
		this.widgetOrder = widgetOrder;
		this.type = type;
		this.page = page;
	}

	public Widget(String name, int width, int height, int widgetOrder, String url, String type) {
		super();
		this.name = name;
		this.width = width;
		this.height = height;
		this.widgetOrder = widgetOrder;
		this.url = url;
		this.type = type;
	}

	public Widget(int id, String name, int width, int height, String cssClass, String cssStyle, String text,
			int widgetOrder, String type, Page page) {
		super();
		this.id = id;
		this.name = name;
		this.width = width;
		this.height = height;
		this.cssClass = cssClass;
		this.cssStyle = cssStyle;
		this.text = text;
		this.widgetOrder = widgetOrder;
		this.type = type;
		this.page = page;
	}

	public Widget(int id, String name, int width, int height, String cssClass, String cssStyle, String text,
			int widgetOrder, String url, int shareable, int expandable, String src, int size, String html, String type,
			Page page) {
		super();
		this.id = id;
		this.name = name;
		this.width = width;
		this.height = height;
		this.cssClass = cssClass;
		this.cssStyle = cssStyle;
		this.text = text;
		this.widgetOrder = widgetOrder;
		this.url = url;
		this.shareable = shareable;
		this.expandable = expandable;
		this.src = src;
		this.size = size;
		this.html = html;
		this.type = type;
		this.page = page;
	}


	private int id;
	private String name;
	private int width;
	private int height;
	private String cssClass;
	private String cssStyle;
	private String text;
	private int widgetOrder;
	private String url;
	private int shareable;
	private int expandable;
	private String src;
	private int size;
	private String html;
	private String type;
	private Page page = new Page();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getCssClass() {
		return cssClass;
	}
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
	public String getCssStyle() {
		return cssStyle;
	}
	public void setCssStyle(String cssStyle) {
		this.cssStyle = cssStyle;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getWidgetOrder() {
		return widgetOrder;
	}
	public void setWidgetOrder(int widgetOrder) {
		this.widgetOrder = widgetOrder;
	}
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getShareable() {
		return shareable;
	}

	public void setShareable(int shareable) {
		this.shareable = shareable;
	}

	public int getExpandable() {
		return expandable;
	}

	public void setExpandable(int expandable) {
		this.expandable = expandable;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}

	@Override
	public String toString() {
		return "Widget [id=" + id + ", name=" + name + ", width=" + width + ", height=" + height + ", cssClass="
				+ cssClass + ", cssStyle=" + cssStyle + ", text=" + text + ", widgetOrder=" + widgetOrder + ", url="
				+ url + ", shareable=" + shareable + ", expandable=" + expandable + ", src=" + src + ", size=" + size
				+ ", html=" + html + ", type=" + type + "]\n";
	}
}
