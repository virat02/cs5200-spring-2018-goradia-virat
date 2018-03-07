package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import interfaces.WidgetDaoInterface;
import model.Widget;
import sqlQueries.StringQueries;

public class WidgetDao extends BaseDao implements WidgetDaoInterface{

	static WidgetDao instance = null;

	/**
	 * Singleton Class constructor
	 */
	private WidgetDao() {
	}
	public static WidgetDao getInstance() {
		if(instance == null) {
			return new WidgetDao();
		}
		return instance;
	}

	/**
	 * @param widgetName
	 * @return Returns a hashmap with widget id and page id of the input widget name.
	 */
	public Map<String,Integer> getWidgetDetialsByWidgetName(String widgetName) 
	{
		ResultSet result = null;
		int widgetId = 0;
		int pageId = 0;
		Map<String,Integer> widgetDetails = new HashMap<String,Integer>();
		try {
			Class.forName(JDBC_DRIVER); 
			Connection conn= DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			String sql = StringQueries.FIND_WIDGET_ID_BY_WIDGETNAME;
			PreparedStatement pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, widgetName);
			result = pstmt.executeQuery();
			while(result.next()) {
				widgetId = result.getInt("id");
				pageId = result.getInt("page");
				widgetDetails.put("widgetId", widgetId);
				widgetDetails.put("pageId", pageId);
			}
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return widgetDetails;
	}

	/**
	 * @param pageName 
	 * @param widget
	 * Inserts details of a given widget into database for a given page.
	 */
	public void createWidgetForPage(String pageName, Widget widget)  {
		PageDao pageDao = PageDao.getInstance();
		int pageId = pageDao.getPageIdByPageName(pageName);
		createWidgetForPage( pageId, widget);
	}

	/* (non-Javadoc)
	 * @see interfaces.WidgetDaoInterface#createWidgetForPage(int, model.Widget)
	 */
	public int createWidgetForPage(int pageId, Widget widget)  		
	{

		int result = -1;
		try {
			Class.forName(JDBC_DRIVER); 
			Connection conn= DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			String sql = StringQueries.CREATE_WIDGET;
			PreparedStatement pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, widget.getId());
			pstmt.setString(2, widget.getName());
			pstmt.setInt(3, widget.getWidth());
			pstmt.setInt(4, widget.getHeight());
			pstmt.setString(5, widget.getCssStyle());
			pstmt.setString(6, widget.getCssClass());
			pstmt.setString(7, widget.getText());
			pstmt.setInt(8, widget.getWidgetOrder());
			pstmt.setString(9, widget.getUrl());
			pstmt.setInt(10, widget.getShareable());
			pstmt.setInt(11, widget.getExpandable());
			pstmt.setString(12, widget.getSrc());
			pstmt.setInt(13, widget.getSize());
			pstmt.setString(14, widget.getHtml());
			pstmt.setString(15, widget.getType());
			pstmt.setInt(16, pageId);
			result = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Records for "+widget.getName() +" created successfully.");
		return result;
	}

	/**
	 * @param resultSet
	 * @return list of widget instances with the data received from DB in resultset.
	 */
	public ArrayList<Widget> getWidgetsFromResultSet (ResultSet result) 
	{
		ArrayList<Widget> widgets= new ArrayList<Widget>();
		try {
			while(result.next()) {
				Widget widget = new Widget();
				widget.setId(result.getInt("id"));
				widget.setName(result.getString("name"));
				widget.setWidth(result.getInt("width"));
				widget.setHeight(result.getInt("height"));
				widget.setCssStyle(result.getString("cssstyle"));
				widget.setCssClass(result.getString("cssclass"));
				widget.setText(result.getString("text"));
				widget.setWidgetOrder(result.getInt("widgetOrder"));
				widget.setUrl(result.getString("url"));
				widget.setShareable(result.getInt("shareable"));
				widget.setExpandable(result.getInt("expandable"));
				widget.setSrc(result.getString("src"));
				widget.setSize(result.getInt("size"));
				widget.setHtml(result.getString("html"));
				widget.setType(result.getString("type"));
				widget.getPage().setId(result.getInt("page"));
				widgets.add(widget);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return widgets;
	}

	/* (non-Javadoc)
	 * @see interfaces.WidgetDaoInterface#findAllWidgets()
	 */
	public ArrayList<Widget> findAllWidgets() 
	{
		ResultSet result = null;
		ArrayList<Widget> widgets= new ArrayList<Widget>();
		try {
			Class.forName(JDBC_DRIVER); 
			Connection conn= DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			String sql = StringQueries.FIND_ALL_WIDGETS;
			PreparedStatement pstmt= conn.prepareStatement(sql);
			result = pstmt.executeQuery();
			widgets = getWidgetsFromResultSet(result);
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return widgets;
	}

	/* (non-Javadoc)
	 * @see interfaces.WidgetDaoInterface#findWidgetById(int)
	 */
	public Widget findWidgetById(int widgetId)
	{
		ResultSet result = null;
		ArrayList<Widget> widgets= new ArrayList<Widget>();
		Widget widget = new Widget();
		try {
			Class.forName(JDBC_DRIVER); 
			Connection conn= DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			String sql = StringQueries.FIND_WIDGET_BY_ID;
			PreparedStatement pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, widgetId);
			result = pstmt.executeQuery();
			widgets = getWidgetsFromResultSet(result);
			if(!widgets.isEmpty())
				widget = widgets.get(0);
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return widget;
	}

	/* (non-Javadoc)
	 * @see interfaces.WidgetDaoInterface#findWidgetsForPage(int)
	 */
	public ArrayList<Widget> findWidgetsForPage(int pageId) 	
	{
		ResultSet result = null;
		ArrayList<Widget> widgets= new ArrayList<Widget>();
		try {
			Class.forName(JDBC_DRIVER); 
			Connection conn= DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			String sql = StringQueries.FIND_WIDGET_BY_PAGE_ID;
			PreparedStatement pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, pageId);
			result = pstmt.executeQuery();
			widgets = getWidgetsFromResultSet(result);
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return widgets;
	}

	public int findLastUpdatedWidgetIdForPage(int pageId) 	
	{
		ResultSet result = null;
		int widgetId = 0;
		try {
			Class.forName(JDBC_DRIVER); 
			Connection conn= DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			String sql = StringQueries.FIND_LAST_UPDATED_WIDGET_ID_BY_PAGE_ID;
			PreparedStatement pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, pageId);
			result = pstmt.executeQuery();
			while(result.next()) {
				widgetId = result.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return widgetId;
	}

	/* (non-Javadoc)
	 * @see interfaces.WidgetDaoInterface#updateWidget(int, model.Widget)
	 */
	public int updateWidget(int widgetId, Widget widget)  	
	{
		int result = -1;
		try {
			Class.forName(JDBC_DRIVER); 
			Connection conn= DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			Widget oldWidget = findWidgetById(widgetId);
			widget = getUpdatedWidget( widget, oldWidget);
			String sql = StringQueries.UPDATE_WIDGET; 
			PreparedStatement pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, widget.getName());
			pstmt.setInt(2, widget.getWidth());
			pstmt.setInt(3, widget.getHeight());
			pstmt.setString(4, widget.getCssStyle());
			pstmt.setString(5, widget.getCssClass());
			pstmt.setString(6, widget.getText());
			pstmt.setInt(7, widget.getWidgetOrder());
			pstmt.setString(8, widget.getUrl());
			pstmt.setInt(9, widget.getShareable());
			pstmt.setInt(10, widget.getExpandable());
			pstmt.setString(11, widget.getSrc());
			pstmt.setInt(12, widget.getSize());
			pstmt.setString(13, widget.getHtml());
			pstmt.setString(14, widget.getType());
			pstmt.setInt(15, widget.getPage().getId());
			pstmt.setInt(16, widgetId);
			result = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) { 
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(widget.getName() +" details updated successfully.");
		return result;
	}

	/**
	 * @param newWidget
	 * @param oldWidget
	 * @return A widget
	 * Merges the details of widget given by user and in database, depending on user-input.
	 */
	public Widget getUpdatedWidget(Widget newWidget, Widget oldWidget)
	{
		try {
			if (newWidget.getId() == 0) 
				newWidget.setId(oldWidget.getId());
			if (StringUtils.isBlank (newWidget.getName())) 
				newWidget.setName(oldWidget.getName());
			if (0 == newWidget.getWidth()) 
				newWidget.setWidth(oldWidget.getWidth());
			if (0 == newWidget.getHeight()) 
				newWidget.setHeight(oldWidget.getHeight());
			if (StringUtils.isBlank (newWidget.getCssStyle())) 
				newWidget.setCssStyle(oldWidget.getCssStyle());
			if (StringUtils.isBlank (newWidget.getCssClass())) 
				newWidget.setCssClass(oldWidget.getCssClass());
			if (StringUtils.isBlank (newWidget.getText())) 
				newWidget.setText(oldWidget.getText());
			if (0 == newWidget.getWidgetOrder()) 
				newWidget.setWidgetOrder(oldWidget.getWidgetOrder());
			if (StringUtils.isBlank (newWidget.getUrl())) 
				newWidget.setUrl(oldWidget.getUrl());
			if (0 == newWidget.getShareable()) 
				newWidget.setShareable(oldWidget.getShareable());
			if (0 == newWidget.getExpandable()) 
				newWidget.setExpandable(oldWidget.getExpandable());
			if (StringUtils.isBlank (newWidget.getSrc())) 
				newWidget.setSrc(oldWidget.getSrc());
			if (0 == newWidget.getSize()) 
				newWidget.setSize(oldWidget.getSize());
			if (StringUtils.isBlank (newWidget.getHtml())) 
				newWidget.setHtml(oldWidget.getHtml());
			if (StringUtils.isBlank (newWidget.getType())) 
				newWidget.setType(oldWidget.getType());
			if (0 == (newWidget.getPage().getId())) 
				newWidget.getPage().setId(oldWidget.getPage().getId());
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return newWidget;
	}

	/**
	 * @param widgetName
	 * @param newWidgetOrder
	 * Updates the relative order of widget on the page, note that the other widget's order are updated as well.
	 */
	public void updateWidgetOrder (String widgetName, int newWidgetOrder) {
		Map<String,Integer> widgetDetails = getWidgetDetialsByWidgetName(widgetName);

		if (widgetDetails.size() > 0) {
			int pageId = widgetDetails.get("pageId");
			int widgetId = widgetDetails.get("widgetId");
			ArrayList<Widget> widgets = new ArrayList<Widget>();
			if (pageId != 0) {
				widgets = findWidgetsForPage(pageId);
				int totalWidgetsOnPage = widgets.size();
				Iterator<Widget> widgetItr = widgets.iterator();
				Iterator<Widget> otherWidgetItr = widgets.iterator();

				while(widgetItr.hasNext()) {
					Widget widget = widgetItr.next();
					if (StringUtils.equalsIgnoreCase(widgetName, widget.getName())) {
						while(otherWidgetItr.hasNext()) {
							Widget otherWidget = otherWidgetItr.next();
							if (!StringUtils.equalsIgnoreCase(widgetName, otherWidget.getName())) {
								int newOtherWidgetOrder = getNewWidgetOrderForOtherWidgets(otherWidget.getWidgetOrder(),totalWidgetsOnPage,newWidgetOrder, widget.getWidgetOrder());
								otherWidget.setWidgetOrder(newOtherWidgetOrder);
								updateWidget(otherWidget.getId(), otherWidget);
							}
						}
						widget.setWidgetOrder(newWidgetOrder);
						updateWidget(widgetId, widget);
					}
				}
			}
			else 
				System.out.println("Widget not found.");
		} else 
			System.out.println("Widget not found.");
	}

	/**
	 * @param oldOtherWidgetOrder
	 * @param totalWidgetsOnPage
	 * @param newWidgetOrder
	 * @param oldWidgetOrder
	 * @return Updates the relative order of other widget on the page.
	 */
	public int getNewWidgetOrderForOtherWidgets(int oldOtherWidgetOrder, int totalWidgetsOnPage,int newWidgetOrder, int oldWidgetOrder) {
		int newOrder = 0;
		int diff = newWidgetOrder - oldWidgetOrder;
		newOrder = oldOtherWidgetOrder + diff ;
		if(newOrder <= 0)
			newOrder += totalWidgetsOnPage;
		if (newOrder > totalWidgetsOnPage)
			newOrder = (newOrder % totalWidgetsOnPage) ;
		return newOrder;

	}



	/**
	 * @param pageName
	 * Deleted the last widget to a given page.
	 * The last widget is the one with the highest value in the widgetOrder field.
	 */
	public void deleteLastWidgetFromPage(String pageName) 	
	{
		PageDao pageDao = PageDao.getInstance();
		int pageId = pageDao.getPageIdByPageName(pageName);
		int widgetId = findLastUpdatedWidgetIdForPage(pageId);
		deleteWidget(widgetId);
	}

	/* (non-Javadoc)
	 * @see interfaces.WidgetDaoInterface#deleteWidget(int)
	 */
	public int deleteWidget(int widgetId) 	
	{
		int result = -1;
		try {
			Class.forName(JDBC_DRIVER); 
			Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			String sql = StringQueries.DELETE_WIDGET;
			PreparedStatement pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, widgetId);
			result = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) { 
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(result +" record(s) deleted successfully.");
		return result;

	}

}
