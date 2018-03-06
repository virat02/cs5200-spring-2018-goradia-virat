package model;

public class ImageWidget extends Widget {


	public ImageWidget() {
		super();
	}
	
	public ImageWidget(String src) {
		super();
		this.src = src;
	}

	public ImageWidget(String name, int widgetOrder, String type, Page page,String src) {
		super(name, widgetOrder, type, page);
		this.src = src;
	}
	
	public ImageWidget(int id, String name, int width, int height, String cssClass, String cssStyle, String text,
			int widgetOrder, String type, Page page,String src) {
		super(id, name, width, height, cssClass, cssStyle, text, widgetOrder, type, page);
		this.src = src;
	}

	private String src;

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}
}

