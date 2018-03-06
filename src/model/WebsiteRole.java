package model;

public class WebsiteRole {

	public WebsiteRole() {
		super();
	}
	
	public WebsiteRole(String developerUserName, String roleDescription) {
		super();
		this.developerUserName = developerUserName;
		this.roleDescription = roleDescription;
	}

	private String developerUserName;
	private String roleDescription;
	
	public String getDeveloperUserName() {
		return developerUserName;
	}

	public void setDeveloperUserName(String developerUserName) {
		this.developerUserName = developerUserName;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	
}

