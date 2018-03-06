package model;

public class HeadingWidget extends Widget{

	public HeadingWidget() {
		super();
	}

	public HeadingWidget(int size) {
		super();
		this.size = size;
	}
	public HeadingWidget(String name, int widgetOrder, String type, Page page,int size) {
		super(name, widgetOrder, type, page);
		this.size = size;
	}

	public HeadingWidget(int id, String name, int width, int height, String cssClass, String cssStyle, String text,
			int widgetOrder, String type, Page page,int size) {
		super(id, name, width, height, cssClass, cssStyle, text, widgetOrder, type, page);
		this.size = size;
	}


	private int size;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}

