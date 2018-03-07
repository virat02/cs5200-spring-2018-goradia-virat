package model;

public class PageRole {
	
	public PageRole() {
		super();
	}
	
	public PageRole(String pageName, String developerUsername, String roleDescription) {
		super();
		this.pageName = pageName;
		this.developerUsername = developerUsername;
		this.roleDescription = roleDescription;
	}
	
	public PageRole(int pageId, int developerId, int roleId) {
		super();
		this.pageId = pageId;
		this.developerId = developerId;
		this.roleId = roleId;
	}

	public PageRole(String pageName, String developerUsername, String roleDescription, int pageId, int developerId,
			int roleId) {
		super();
		this.pageName = pageName;
		this.developerUsername = developerUsername;
		this.roleDescription = roleDescription;
		this.pageId = pageId;
		this.developerId = developerId;
		this.roleId = roleId;
	}
	
	private String pageName;
	private String developerUsername;
	private String roleDescription;
	private int pageId;
	private int developerId;
	private int roleId;
	
	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getDeveloperUsername() {
		return developerUsername;
	}

	public void setDeveloperUsername(String developerUsername) {
		this.developerUsername = developerUsername;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public int getDeveloperId() {
		return developerId;
	}

	public void setDeveloperId(int developerId) {
		this.developerId = developerId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
}
