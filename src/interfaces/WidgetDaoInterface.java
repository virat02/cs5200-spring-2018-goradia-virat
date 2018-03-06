package interfaces;

import java.util.Collection;
import model.Widget;

public interface WidgetDaoInterface {
	
	/**
	 * This method inserts properties in widget instance parameter into the Widget table. The widget's pageId foreign key refer to Page table primary key id whose value is equal to the pageId parameter
	 * @param pageId
	 * @param widget
	 * @return integer
	*/
	public int createWidgetForPage(int pageId, Widget widget); 
	
	/**
	 * @return Collection<Widget>; all records from Widget table as a Collection of Widget instances
	*/
	public Collection<Widget> findAllWidgets(); 
	
	/**
	 * @param widgetId
	 * @return a record from Widget table whose id field is equal to the widgetId parameter
	*/
	public Widget findWidgetById(int widgetId); 
	
	/**
	 * This method is used to find the widgets on a given page
	 * @param pageId
	 * @return Collection<Widget>; returns all records from Widget table as a Collection of Widget instances whose pageId is equal to the pageId parameter
	*/
	public Collection<Widget> findWidgetsForPage(int pageId); 
	
	/**
	 * This method updates record in Widget table whose id field is equal to widgetId parameter. New record field values are set to the values in the widget instance parameter
	 * @param widgetId
	 * @param widget
	 * @return integer
	 */
	public int updateWidget(int widgetId, Widget widget); 
	
	/**
	 * This method deletes record from Widget table whose id field equals the widgetId parameter
	 * @param widgetId
	 * @return integer
	 */
	public int deleteWidget(int widgetId); 
}

