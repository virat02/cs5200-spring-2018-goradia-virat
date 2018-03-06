package model;

public class WebsitePrivilege {

	public WebsitePrivilege() {
		super();
	}
	
	
	public WebsitePrivilege(String websiteName, String developerUserName, RolePrivilege privilege) {
		super();
		this.websiteName = websiteName;
		this.developerUserName = developerUserName;
		this.privilege = privilege;
	}


	public WebsitePrivilege(int websiteId, int developerId, int privilegeId) {
		super();
		this.websiteId = websiteId;
		this.developerId = developerId;
		this.privilegeId = privilegeId;
	}


	public WebsitePrivilege(String websiteName, String developerUserName, RolePrivilege privilege, int websiteId,
			int developerId, int privilegeId) {
		super();
		this.websiteName = websiteName;
		this.developerUserName = developerUserName;
		this.privilege = privilege;
		this.websiteId = websiteId;
		this.developerId = developerId;
		this.privilegeId = privilegeId;
	}

	private String websiteName;
	private String developerUserName;
	private RolePrivilege privilege;
	private int websiteId;
	private int developerId;
	private int privilegeId;
	
	public String getWebsiteName() {
		return websiteName;
	}


	public void setWebsiteName(String websiteName) {
		this.websiteName = websiteName;
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


	public int getWebsiteId() {
		return websiteId;
	}


	public void setWebsiteId(int websiteId) {
		this.websiteId = websiteId;
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

