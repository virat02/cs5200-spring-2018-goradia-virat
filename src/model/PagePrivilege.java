package model;

public class PagePrivilege {

	public PagePrivilege() {
		super();
	}
	
	public PagePrivilege(String pageName, String developerUserName, RolePrivilege privilege) {
		super();
		this.pageName = pageName;
		this.developerUserName = developerUserName;
		this.privilege = privilege;
	}
	
	public PagePrivilege(int pageId, int developerId, int privilegeId) {
		super();
		this.pageId = pageId;
		this.developerId = developerId;
		this.privilegeId = privilegeId;
	}

	public PagePrivilege(String pageName, String developerUserName, RolePrivilege privilege, int pageId,
			int developerId, int privilegeId) {
		super();
		this.pageName = pageName;
		this.developerUserName = developerUserName;
		this.privilege = privilege;
		this.pageId = pageId;
		this.developerId = developerId;
		this.privilegeId = privilegeId;
	}

	private String pageName;
	private String developerUserName;
	private RolePrivilege privilege;
	private int pageId;
	private int developerId;
	private int privilegeId;
	
	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getDeveloperUserName() {
		return developerUserName;
	}

	public void setDeveloperUserName(String developerUserName) {
		this.developerUserName = developerUserName;
	}

	public RolePrivilege getPrivilege() {
		return privilege;
	}

	public void setPrivilege(RolePrivilege privilege) {
		this.privilege = privilege;
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

	public int getPrivilegeId() {
		return privilegeId;
	}

	public void setPrivilegeId(int privilegeId) {
		this.privilegeId = privilegeId;
	}
	
}
