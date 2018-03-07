package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;

import interfaces.PageDaoInterface;
import model.Page;
import model.PageRole;
import sqlQueries.StringQueries;

public class PageDao extends BaseDao implements PageDaoInterface{

	static PageDao instance = null;

	/**
	 * Singleton Class constructor
	 */
	private PageDao() {
	}
	public static PageDao getInstance() {
		if(instance == null) {
			return new PageDao();
		}
		return instance;
	}

	/**
	 * @param websiteName
	 * @param page
	 * Creates pages for a given website.
	 */
	public void createPageForWebsite(String websiteName, Page page) {
		WebsiteDao webDao = WebsiteDao.getInstance();
		int websiteId = webDao.getWebsiteIdByWebsiteName(websiteName);
		createPageForWebsite(websiteId, page);
	}

	/* (non-Javadoc)
	 * @see interfaces.PageDaoInterface#createPageForWebsite(int, model.Page)
	 */
	public int createPageForWebsite(int websiteId, Page page) 
	{
		int result = -1;
		try {
			RoleDao rDao = RoleDao.getInstance();
			DeveloperDao devDao = DeveloperDao.getInstance();
			PrivilegeDao pDao = PrivilegeDao.getInstance();
			Class.forName(JDBC_DRIVER); 
			Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			String sql = StringQueries.CREATE_PAGE;
			PreparedStatement pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, page.getId());
			pstmt.setString(2, page.getTitle());
			pstmt.setString(3, page.getDescription());
			Date assignmentDueDate = new Date(2018-03-06);
			pstmt.setDate(4,  (java.sql.Date) assignmentDueDate);
			pstmt.setDate(5, (java.sql.Date) assignmentDueDate);
			pstmt.setInt(6, page.getViews());
			pstmt.setInt(7, websiteId);
			result = pstmt.executeUpdate();
			int pageId = getPageIdByPageName(page.getTitle());
			ArrayList<PageRole> roles = page.getPageRoles();
			Iterator<PageRole> roleItr = roles.iterator();
			while(roleItr.hasNext()) {
				PageRole pageRole = roleItr.next();
				int devId = devDao.getDeveloperIdByUsername(pageRole.getDeveloperUsername());
				int roleId = rDao.getRoleIdByDescription(pageRole.getRoleDescription());
				rDao.assignPageRole(devId, pageId , roleId) ; 
				pDao.createPagePrivilege(devId, pageId , roleId) ; 
			}
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Records for "+page.getTitle() +" created successfully.");
		return result;
	}

	/**
	 * @param pgName
	 * @return integer
	 * Returns the page id given the input page name.
	 */
	public int getPageIdByPageName(String pgName) 
	{
		ResultSet result = null;
		int pgId = 0;
		try {
			Class.forName(JDBC_DRIVER); 
			Connection conn= DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			String sql = StringQueries.FIND_PAGE_ID_BY_PAGE_NAME;
			PreparedStatement pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, pgName);
			result = pstmt.executeQuery();
			while(result.next()) {
				pgId = result.getInt("id");
			}
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return pgId;
	}

	/**
	 * @param pgName
	 * @param websiteId
	 * @return page id for the page of the input website.
	 */
	public int getPageIdByPageNameAndWebsiteId(String pgName, int websiteId) {

		ResultSet result = null;
		int pgId = 0;
		try {
			Class.forName(JDBC_DRIVER); 
			Connection conn= DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			String sql = StringQueries.FIND_PAGE_ID_BY_PAGE_NAME_AND_WEBSITE_ID;
			PreparedStatement pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, pgName);
			pstmt.setInt(2, websiteId);
			result = pstmt.executeQuery();
			while(result.next()) {
				pgId = result.getInt("id");
			}
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return pgId;
	}
	/**
	 * @param resultSet
	 * @return list of page instances with the data retrieved from Database in resultSet.
	 */
	public ArrayList<Page> getPagesFromResultSet (ResultSet result) 
	{
		ArrayList<Page> pages= new ArrayList<Page>();
		try {
			while(result.next()) {
				Page page = new Page(result.getInt("id"),
									 result.getString("title"),
									 result.getString("description"),
									 result.getDate("created"),
									 result.getDate("updated"),
									 result.getInt("views"));
				page.getWebsite().setId(result.getInt("website"));
				pages.add(page);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return pages;
	}
	
	/* (non-Javadoc)
	 * @see interfaces.PageDaoInterface#findAllPages()
	 */
	public ArrayList<Page> findAllPages()
	{
		ResultSet result = null;
		ArrayList<Page> pages= new ArrayList<Page>();
		try {
			Class.forName(JDBC_DRIVER); 
			Connection conn= DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			String sql = StringQueries.FIND_ALL_PAGES;
			PreparedStatement pstmt= conn.prepareStatement(sql);
			result = pstmt.executeQuery();
			pages = getPagesFromResultSet (result) ;
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return pages;
	}

	/* (non-Javadoc)
	 * @see interfaces.PageDaoInterface#findPageById(int)
	 */
	public Page findPageById(int pageId) 	
	{
		ResultSet result = null;
		ArrayList<Page> pages= new ArrayList<Page>();
		Page page = new Page();
		try {
			Class.forName(JDBC_DRIVER); 
			Connection conn= DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			String sql = StringQueries.FIND_PAGE_BY_ID;
			PreparedStatement pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, pageId);
			result = pstmt.executeQuery();
			pages = getPagesFromResultSet (result) ;
			if(!pages.isEmpty())
				page = pages.get(0);
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return page;

	}

	/* (non-Javadoc)
	 * @see interfaces.PageDaoInterface#findPagesForWebsite(int)
	 */
	public ArrayList<Page> findPagesForWebsite(int websiteId)
	{

		ResultSet result = null;
		ArrayList<Page> pages = new ArrayList<Page>();
		try {
			Class.forName(JDBC_DRIVER); 
			Connection conn= DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			String sql = StringQueries.FIND_PAGE_BY_WEBSITE_ID;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, websiteId);
			result = pstmt.executeQuery();
			pages = getPagesFromResultSet (result) ;
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return pages;
	}
	public ArrayList<PageRole> findRolesAssignedToPage(int pageId)
	{

		ResultSet result = null;
		ArrayList<PageRole> pageRoles= new ArrayList<PageRole>();
		try {
			Class.forName(JDBC_DRIVER); 
			Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			String sql = StringQueries.FIND_ROLES_FOR_PAGE;
			PreparedStatement pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, pageId);
			result = pstmt.executeQuery();
			while(result.next()) {
				PageRole pRole = new PageRole();
				pRole.setDeveloperId(result.getInt("developer"));
				pRole.setRoleId(result.getInt("role"));
				pRole.setPageId(result.getInt("page"));
				pageRoles.add(pRole);
			}
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return pageRoles;
	}
	
	public int findLastUpdatedPageForWebsite(int websiteId) 	
	{
		ResultSet result = null;
		int widgetId = 0;
		try {
			Class.forName(JDBC_DRIVER); 
			Connection conn= DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			String sql = StringQueries.FIND_LAST_UPDATED_PAGE_ID_BY_WEBSITE_ID;
			PreparedStatement pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, websiteId);
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
	 * @see interfaces.PageDaoInterface#updatePage(int, model.Page)
	 */
	public int updatePage(int pageId, Page page)  
	{
		int result = -1;
		Page oldPage = new Page();
		try {
			Class.forName(JDBC_DRIVER); 
			Connection conn= DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			oldPage = findPageById(pageId);
			page = getUpdatedPage( page, oldPage);
			String sql = StringQueries.UPDATE_PAGE; 
			PreparedStatement pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, page.getTitle());
			pstmt.setString(2, page.getDescription());
			pstmt.setDate(3, (java.sql.Date) page.getCreated());
			pstmt.setDate(4, (java.sql.Date) page.getUpdated());
			pstmt.setInt(5, page.getViews());
			pstmt.setInt(6, page.getWebsite().getId());
			pstmt.setInt(7, pageId);
			result = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) { 
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(page.getTitle() +" details updated successfully.");
		return result;
	}
	/**
	 * @param newPage
	 * @param oldPage
	 * @return A Page
	 * Depending on input, merges the page given by the user and the one in the database. 
	 */
	public Page getUpdatedPage(Page newPage, Page oldPage)
	{
		try {
			if (newPage.getId() == 0) 
				newPage.setId(oldPage.getId());
			if (StringUtils.isBlank (newPage.getTitle())) 
				newPage.setTitle(oldPage.getTitle());
			if (StringUtils.isBlank (newPage.getDescription())) 
				newPage.setDescription(oldPage.getDescription());
			if (null == newPage.getCreated()) 
				newPage.setCreated(oldPage.getCreated());
			if (null == newPage.getUpdated()) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");
				LocalDate localDate = LocalDate.now();
				Date date = null;
				java.sql.Date sqlDate = null;
				try {
					date = (Date) dateFormat.parse(localDate.toString());
					sqlDate = new java.sql.Date(date.getTime());
				} catch (ParseException e) {
					e.printStackTrace();
				} 
				newPage.setUpdated(sqlDate);
			}
			if (newPage.getViews() == 0) 
				newPage.setViews(oldPage.getViews());
			if ((newPage.getWebsite().getId()) == 0) 
				newPage.getWebsite().setId(oldPage.getWebsite().getId());
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return newPage;
	}

	
	/**
	 * @param websiteName
	 * Deletes the page last updated for the given website.
	*/
	public void deleteLastUpdatedPageForWebsite(String websiteName) 	
	{
		WebsiteDao webDao = WebsiteDao.getInstance();
		int websiteId = webDao.getWebsiteIdByWebsiteName(websiteName);
		int pageId = findLastUpdatedPageForWebsite(websiteId);
		deletePage(pageId);
	}

	/* (non-Javadoc)
	 * @see interfaces.PageDaoInterface#deletePage(int)
	 */
	public int deletePage(int pageId) 	
	{
		int result = -1;
		try {
			Class.forName(JDBC_DRIVER); 
			Connection conn= DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			String sql = StringQueries.DELETE_PAGE;
			PreparedStatement pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, pageId);
			result = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) { 
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(result + " record(s) deleted successfully.");
		return result;
	}

	
	/**
	 * @param toAppend
	 * @param websiteName
	 * Appends the given string to all the page names of the given website.
	 */
	public void appendWebsiteNameToPageNamesForWebsite(String toAppend, String websiteName) {
		WebsiteDao webDao = WebsiteDao.getInstance();
		int websiteId = webDao.getWebsiteIdByWebsiteName(websiteName);
		if (websiteId != 0) {
			ArrayList<Page> pages = findPagesForWebsite(websiteId);
			Iterator<Page> pageItr = pages.iterator();
			while(pageItr.hasNext()) {
				Page page = pageItr.next();
				page.setTitle(toAppend + page.getTitle());
				updatePage(page.getId(),page);
			}
		}
		else 
			System.out.println("Website not found!");
	}
}
