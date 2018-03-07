package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import interfaces.RoleDaoInterface;
import model.PageRole;
import sqlQueries.StringQueries;

public class RoleDao extends BaseDao implements RoleDaoInterface{

	static RoleDao instance = null;

	/**
	 * Singleton Class constructor
	 */
	private RoleDao() {
	}
	public static RoleDao getInstance() {
		if(instance == null) {
			return new RoleDao();
		}
		return instance;
	}

	/**
	 * @param roleDescription
	 * @return role id given the input role description.
	 */
	public int getRoleIdByDescription(String roleDescription) 
	{
		ResultSet result = null;
		int roleId = 0;
		try {
			Class.forName(JDBC_DRIVER); 
			Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			String sql = StringQueries.FIND_ROLE_ID_BY_NAME;
			PreparedStatement pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, roleDescription);
			result = pstmt.executeQuery();
			while(result.next()) {
				roleId = result.getInt("id");
			}
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return roleId;
	}


	/**
	 * @param developerId
	 * @param pageId
	 * @return role id given the input page Id and developer Id.
	 */
	public int getPersonRoleByDeveloperIdAndPageId(int developerId, int pageId) 
	{
		ResultSet result = null;
		int roleId = 0;
		try {
			Class.forName(JDBC_DRIVER); 
			Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			String sql = StringQueries.FIND_ROLE_ID_BY_DEV_ID_AND_PAGE_ID;
			PreparedStatement pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, developerId);
			pstmt.setInt(2, pageId);
			result = pstmt.executeQuery();
			while(result.next()) {
				roleId = result.getInt("role");
			}
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return roleId;
	}

	/* (non-Javadoc)
	 * @see interfaces.RoleDaoInterface#assignWebsiteRole(int, int, int)
	 */
	public void assignWebsiteRole(int developerId, int websiteId, int roleId) 
	{
		try {
			Class.forName(JDBC_DRIVER); 
			Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			String sql = StringQueries.ASSIGN_WEBISTE_ROLE;
			PreparedStatement pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, roleId);
			pstmt.setInt(2, websiteId);
			pstmt.setInt(3, developerId);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see interfaces.RoleDaoInterface#assignPageRole(int, int, int)
	 */
	public void assignPageRole(int developerId, int pageId, int roleId) 
	{
		try {
			Class.forName(JDBC_DRIVER); 
			Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			String sql = StringQueries.ASSIGN_PAGE_ROLE;
			PreparedStatement pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, roleId);
			pstmt.setInt(2, pageId);
			pstmt.setInt(3, developerId);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @param firstUsername
	 * @param secondUsername
	 * @param websiteName
	 * @param pageName
	 * Swaps the page roles of the input two developers.
	 */
	public void swapDeveloperPageRoles(String firstUsername, String secondUsername, String websiteName, String pageName) {
		WebsiteDao webDao = WebsiteDao.getInstance();
		PageDao pageDao =  PageDao.getInstance();
		DeveloperDao devDao = DeveloperDao.getInstance();
		int websiteId = webDao.getWebsiteIdByWebsiteName(websiteName);
		int pageId = pageDao.getPageIdByPageNameAndWebsiteId(pageName,websiteId);

		if (websiteId != 0 && pageId != 0) {
			int fPersonId = devDao.getPersonIdByUsername(firstUsername);
			int sPersonId = devDao.getPersonIdByUsername(secondUsername);
			int fPersonRole = getPersonRoleByDeveloperIdAndPageId(fPersonId,pageId);
			int sPersonRole = getPersonRoleByDeveloperIdAndPageId(sPersonId,pageId);
			ArrayList<PageRole> roleList = pageDao.findRolesAssignedToPage(pageId);
			Iterator<PageRole> roleItr = roleList.iterator();
			while (roleItr.hasNext()) {
				PageRole role = roleItr.next();
				int roleId = role.getRoleId();
				int developerId = role.getDeveloperId();
				if (roleId == fPersonRole && fPersonId == developerId) {
					updatePageRole(fPersonId, pageId, roleId, sPersonRole);
				} 
				else if (roleId == sPersonRole && sPersonId == developerId) {					
					updatePageRole(sPersonId, pageId, roleId, fPersonRole);
				}
			}
			System.out.println("Role of " +firstUsername+ " swapped with " + secondUsername + " for "+ websiteName + " "+ pageName +" page.");
		}
		else 
			System.out.println("Incorrect details, please rectify and try again!");
	}
	
	/**
	 * @param developerId
	 * @param pageId
	 * @param oldRoleId
	 * @param newRoleId
	 * Updates the role of a given developer for a given page by deleting the old role 
	 * and assigning the new role
	*/
	public void updatePageRole(int developerId, int pageId, int oldRoleId, int newRoleId) 
	{
		deletePageRole(developerId,  pageId,  oldRoleId);
		assignPageRole(developerId,  pageId,  newRoleId);
	}

	/* (non-Javadoc)
	 * @see interfaces.RoleDaoInterface#deleteWebsiteRole(int, int, int)
	*/
	@Override
	public void deleteWebsiteRole(int developerId, int websiteId, int roleId) {
		try {
			Class.forName(JDBC_DRIVER); 
			Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			String sql = StringQueries.DELETE_WEBSITE_ROLE;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, developerId);
			pstmt.setInt(2, websiteId);
			pstmt.setInt(3, roleId);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) { 
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param websiteId
	 * deletes from table Role a record that removes roleId from developerId, on websiteId
	*/
	public void deleteWebsiteRole(int websiteId) 
	{
		try {
			Class.forName(JDBC_DRIVER); 
			Connection conn= DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			String sql = StringQueries.DELETE_WEBSITE_ROLE_BY_WEBSITE_ID;
			PreparedStatement pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, websiteId);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) { 
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param pageId
	 * deletes from table Role a record that removes roleId from developerId, on pageId
	*/
	public void deletePageRole(int pageId) { 		
		try {
			Class.forName(JDBC_DRIVER); 
			Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			String sql = StringQueries.DELETE_PAGE_ROLE_BY_PAGE_ID;
			PreparedStatement pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, pageId);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) { 
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see interfaces.RoleDaoInterface#deletePageRole(int, int, int)
	*/
	@Override
	public void deletePageRole(int developerId, int pageId, int roleId) {
		try {
			Class.forName(JDBC_DRIVER); 
			Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			String sql = StringQueries.DELETE_PAGE_ROLE;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, developerId);
			pstmt.setInt(2, pageId);
			pstmt.setInt(3, roleId);
			int result = pstmt.executeUpdate();
			System.out.println(result +" record(s) deleted successfully.");
			pstmt.close();
			conn.close();
		} catch (SQLException e) { 
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
