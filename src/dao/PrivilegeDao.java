package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import interfaces.PrivilegeDaoInterface;
import sqlQueries.StringQueries;

public class PrivilegeDao extends BaseDao implements PrivilegeDaoInterface{

	static PrivilegeDao instance = null;

	/**
	 * Singleton Class constructor
	 */
	private PrivilegeDao() {
	}
	public static PrivilegeDao getInstance() {
		if(instance == null) {
			return new PrivilegeDao();
		}
		return instance;
	}
	
	/**
	 * @param privilegeName
	 * @return integer
	 * Returns privilege id corresponding to the input privilege name.
	 */
	public int getPrivilegeIdByName(String privilegeName) 
	{
		ResultSet result = null;
		int privilegeId = 0;
		try {
			Class.forName(JDBC_DRIVER); 
			Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			String sql = StringQueries.FIND_PRIVILEGE_ID_BY_NAME;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, privilegeName);
			result = pstmt.executeQuery();
			while(result.next()) {
				privilegeId = result.getInt("id");
			}
			pstmt.close();
			conn.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return privilegeId;
	}

	/**
	 * @param roleId
	 * @return list of privilege Ids for the input role Id.
	 */
	public ArrayList<Integer> getPrivilegeNameByRole(int roleId) 
	{
		ResultSet result = null;
		ArrayList<Integer> privileges = new ArrayList<Integer>();
		try {
			Class.forName(JDBC_DRIVER); 
			Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			String sql = StringQueries.FIND_PRIVILEGE_ID_BY_ROLE;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roleId);
			result = pstmt.executeQuery();
			while(result.next()) {
				privileges.add(result.getInt("privilege"));
			}
			pstmt.close();
			conn.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return privileges;
	}

	
	/**
	 * @param developerId
	 * @param websiteId
	 * @param roleId
	 * Inserts privileges for a given website , for the input role Id.
	 */
	public void createWebsitePrivilege(int developerId, int websiteId, int roleId) {
		
		ArrayList<Integer> privileges = getPrivilegeNameByRole(roleId);
		Iterator<Integer> privilegeItr = privileges.iterator();
		while(privilegeItr.hasNext()) {
			int privilegeId = privilegeItr.next();
			assignWebsitePrivilege( developerId, websiteId, privilegeId);
		}
	}


	/* (non-Javadoc)
	 * @see jdbc.interfaces.PrivilegeDaoInterface#assignWebsitePrivilege(int, int, int)
	 */
	@Override
	public void assignWebsitePrivilege(int developerId, int websiteId, int privilegeId) {

		try {
			Class.forName(JDBC_DRIVER); 
			Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			String sql = StringQueries.ASSIGN_WEBISTE_PRIVILEGE;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, privilegeId);
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


	/**
	 * @param developerId
	 * @param pageId
	 * @param roleId
	 * Inserts privileges for a given page , given input developer Id, page id, role Id
	 */
	public void createPagePrivilege(int developerId, int pageId, int roleId) {
		
		ArrayList<Integer> privileges = getPrivilegeNameByRole(roleId);
		Iterator<Integer> privilegeItr = privileges.iterator();
		while(privilegeItr.hasNext()) {
			int privilegeId = privilegeItr.next();
			assignPagePrivilege(developerId, pageId, privilegeId);
		}
	}

	/* (non-Javadoc)
	 * @see interfaces.PrivilegeDaoInterface#assignPagePrivilege(int, int, int)
	 */
	@Override
	public void assignPagePrivilege(int developerId, int pageId, int privilegeId) {
		try {
			Class.forName(JDBC_DRIVER); 
			Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			String sql = StringQueries.ASSIGN_PAGE_PRIVILEGE;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, privilegeId);
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

	/* (non-Javadoc)
	 * @see interfaces.PrivilegeDaoInterface#deleteWebsitePrivilege(int, int, int)
	 */
	@Override
	public void deleteWebsitePrivilege(int developerId, int websiteId, int privilegeId) {

		try {
			Class.forName(JDBC_DRIVER); 
			Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			String sql = StringQueries.DELETE_WEBSITE_PRIVILEGE;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, developerId);
			pstmt.setInt(2, websiteId);
			pstmt.setInt(3, privilegeId);
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
	 * Deletes all the privileges related to a Website, given input website Id.
	 */
	
	public void deleteWebsitePrivilege(int websiteId) 
	{
		try {
			Class.forName(JDBC_DRIVER); 
			Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			String sql = StringQueries.DELETE_WEBSITE_PRIVILEGE_BY_WEBSITE_ID;
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
	 * Deletes all the privileges related to the Page, given input page Id.
	 */
	public void deletePagePrivilege(int pageId) { 		
		try {
			Class.forName(JDBC_DRIVER); 
			Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			String sql = StringQueries.DELETE_PAGE_PRIVILEGE_BY_PAGE_ID;
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
	 * @see interfaces.PrivilegeDaoInterface#deletePagePrivilege(int, int, int)
	 */
	@Override
	public void deletePagePrivilege(int developerId, int pageId, int privilegeId) {
		try {
			Class.forName(JDBC_DRIVER); 
			Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			String sql = StringQueries.DELETE_PAGE_PRIVILEGE;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, developerId);
			pstmt.setInt(2, pageId);
			pstmt.setInt(3, privilegeId);
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
