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
import java.util.Date;
import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;

import interfaces.WebsiteDaoInterface;
import model.Page;
import model.Website;
import model.WebsiteRole;
import sqlQueries.StringQueries;

public class WebsiteDao extends BaseDao implements WebsiteDaoInterface{

	static WebsiteDao instance = null;

	/**
	 * Singleton Class constructor
	 */
	private WebsiteDao() {
	}
	public static WebsiteDao getInstance() {
		if(instance == null) {
			return new WebsiteDao();
		}
		return instance;
	}

	/**
	 * @param developerName
	 * @param website
	 * Inserts given website details into database for a given developer.
	 */
	public void createWebsiteForDeveloper(String developerName, Website website) {
		DeveloperDao developerDao = DeveloperDao.getInstance();
		int developerId = developerDao.getDeveloperIdByUsername(developerName);
		createWebsiteForDeveloper(developerId, website);
	}

	/**
	 * @param websiteName
	 * @return id of the website corresponding to the given website name.
	 */
	public int getWebsiteIdByWebsiteName(String websiteName) 
	{
		ResultSet result = null;
		int websiteId = 0;
		try {
			Class.forName(JDBC_DRIVER); 
			Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			String sql = StringQueries.FIND_WEBSITE_ID_BY_WEBSITE_NAME;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, websiteName);
			result = pstmt.executeQuery();
			while(result.next()) {
				websiteId = result.getInt("id");
			}
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return websiteId;
	}

	/* (non-Javadoc)
	 * @see interfaces.WebsiteDaoInterface#createWebsiteForDeveloper(int,model.Website)
	 */
	public int createWebsiteForDeveloper(int developerId, Website website) 
	{ 		
		int result = -1;
		try {
			RoleDao roleDao = RoleDao.getInstance();
			PrivilegeDao privilegeDao = PrivilegeDao.getInstance();
			DeveloperDao developerDao = DeveloperDao.getInstance();
			Class.forName(JDBC_DRIVER); 
			Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			String sql = StringQueries.CREATE_WEBSITE;
			PreparedStatement pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, website.getId());
			pstmt.setString(2, website.getName());
			pstmt.setString(3, website.getDescription());
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
			pstmt.setDate(4, sqlDate);
			pstmt.setDate(5, sqlDate);
			pstmt.setInt(6, website.getVisits());
			pstmt.setInt(7, developerId);
			result = pstmt.executeUpdate();
			int websiteId = getWebsiteIdByWebsiteName(website.getName());
			ArrayList<WebsiteRole> roles = website.getWebsiteRoles();
			Iterator<WebsiteRole> roleItr = roles.iterator();
			while(roleItr.hasNext()) {
				WebsiteRole webRole = roleItr.next();
				int dId = developerDao.getDeveloperIdByUsername(webRole.getDeveloperUsername());
				int roleId = roleDao.getRoleIdByDescription(webRole.getRoleDescription());
				roleDao.assignWebsiteRole(dId, websiteId, roleId) ;
				privilegeDao.createWebsitePrivilege(dId, websiteId, roleId);
			}
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println( "Record for "+website.getName() +" inserted successfully.");
		return result;
	}

	/**
	 * @param resultSet
	 * @return list of website instances with the data received from DB in resultset.
	 */
	public ArrayList<Website> getWebsitesFromResultSet (ResultSet result) 
	{
		ArrayList<Website> websites= new ArrayList<Website>();
		try {
			while(result.next()) {
				Website web = new Website();
				web.setId(result.getInt("id"));
				web.setName(result.getString("name"));
				web.setDescription(result.getString("description"));
				web.setCreated(result.getDate("created"));
				web.setUpdated(result.getDate("updated"));
				web.setVisits(result.getInt("visits"));
				web.getDeveloper().setId(result.getInt("developer"));
				websites.add(web);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return websites;
	}

	/* (non-Javadoc)
	 * @see interfaces.WebsiteDaoInterface#findAllWebsites()
	 */
	public ArrayList<Website> findAllWebsites() 	
	{

		ResultSet result = null;
		ArrayList<Website> websites= new ArrayList<Website>();
		try {
			Class.forName(JDBC_DRIVER); 
			Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			String sql = StringQueries.FIND_ALL_WEBSITES;
			PreparedStatement pstmt= conn.prepareStatement(sql);
			result = pstmt.executeQuery();
			websites = getWebsitesFromResultSet(result);
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return websites;
	}

	/* (non-Javadoc)
	 * @see interfaces.WebsiteDaoInterface#findWebsitesForDeveloper(int)
	 */
	public ArrayList<Website> findWebsitesForDeveloper(int developerId) 
	{
		ResultSet result = null;
		ArrayList<Website> websites= new ArrayList<Website>();
		try {
			Class.forName(JDBC_DRIVER); 
			Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			String sql = StringQueries.FIND_WEBSITES_FOR_DEVELOPER;
			PreparedStatement pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, developerId);
			result = pstmt.executeQuery();
			websites = getWebsitesFromResultSet(result);
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return websites;
	}

	/* (non-Javadoc)
	 * @see interfaces.WebsiteDaoInterface#findWebsiteById(int)
	 */
	public Website findWebsiteById(int websiteId) 
	{

		ResultSet result = null;
		ArrayList<Website> websites= new ArrayList<Website>();
		Website website= new Website();
		try {
			Class.forName(JDBC_DRIVER); 
			Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			String sql = StringQueries.FIND_WEBSITE_BY_ID;
			PreparedStatement pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, websiteId);
			result = pstmt.executeQuery();
			websites = getWebsitesFromResultSet(result);
			if(!websites.isEmpty())
				website = websites.get(0);
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return website;
	}

	/* (non-Javadoc)
	 * @see interfaces.WebsiteDaoInterface#updateWebsite(int, model.Website)
	 */
	public int updateWebsite(int websiteId, Website website) 
	{
		int result = -1;
		try {
			Class.forName(JDBC_DRIVER); 
			Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			Website oldWebsite = findWebsiteById(websiteId);
			website = getUpdatedWebsite( website, oldWebsite);
			String sql = StringQueries.UPDATE_WEBSITE; 
			PreparedStatement pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, website.getName());
			pstmt.setString(2, website.getDescription());
			pstmt.setDate(3, (java.sql.Date) website.getCreated());
			pstmt.setDate(4, (java.sql.Date) website.getUpdated());
			pstmt.setInt(5, website.getVisits());
			pstmt.setInt(6, website.getDeveloper().getId());
			pstmt.setInt(7, websiteId);
			result = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) { 
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(website.getName() +" details updated successfully.");
		return result;

	}

	/**
	 * @param newWebsite
	 * @param oldWebsite
	 * @return Website
	 * Merges the details of website given by user and in database, depending on user-input.
	 */
	public Website getUpdatedWebsite(Website newWebsite, Website oldWebsite)
	{
		try {
			if (newWebsite.getId() == 0) 
				newWebsite.setId(oldWebsite.getId());
			if (StringUtils.isBlank (newWebsite.getName())) 
				newWebsite.setName(oldWebsite.getName());
			if (StringUtils.isBlank (newWebsite.getDescription())) 
				newWebsite.setDescription(oldWebsite.getDescription());
			if (null == newWebsite.getCreated()) 
				newWebsite.setCreated(oldWebsite.getCreated());
			if (null == newWebsite.getUpdated()) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				LocalDate localDate = LocalDate.now();
				Date date = null;
				java.sql.Date sqlDate = null;
				try {
					date = (Date) dateFormat.parse(localDate.toString());
					sqlDate = new java.sql.Date(date.getTime());
				} catch (ParseException e) {
					e.printStackTrace();
				} 
				newWebsite.setUpdated(sqlDate);
			}
			if (newWebsite.getVisits() == 0) 
				newWebsite.setVisits(oldWebsite.getVisits());
			if ((newWebsite.getDeveloper().getId()) == 0) 
				newWebsite.getDeveloper().setId(oldWebsite.getDeveloper().getId());
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return newWebsite;
	}

	/* (non-Javadoc)
	 * @see interfaces.WebsiteDaoInterface#deleteWebsite(int)
	 */
	public int deleteWebsite(int websiteId) 
	{ 
		int result = -1;
		try {
			Class.forName(JDBC_DRIVER); 
			Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			String sql = StringQueries.DELETE_WEBSITE;
			PreparedStatement pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, websiteId);
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

	/**
	 * @param websiteName
	 * Deletes the website as well as all related roles and privileges relating developers to the Website and Pages.
	 */
	public void deleteWebsiteAndRelatedEntries(String websiteName) 	
	{
		PageDao pageDao =  PageDao.getInstance();
		RoleDao rDao = RoleDao.getInstance();
		PrivilegeDao pDao = PrivilegeDao.getInstance();
		
		int websiteId = getWebsiteIdByWebsiteName(websiteName);
		rDao.deleteWebsiteRole(websiteId);
		pDao.deleteWebsitePrivilege(websiteId);

		ArrayList<Page> pages = pageDao.findPagesForWebsite(websiteId);
		Iterator<Page> pageItr = pages.iterator();
		while(pageItr.hasNext()) {
			Page page = pageItr.next();
			rDao.deletePageRole(page.getId());
			pDao.deletePagePrivilege(page.getId());

		}
		deleteWebsite(websiteId);
	}
}
