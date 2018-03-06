package model;

public class Role {
	
	public Role() {
		super();
	}
	
	public Role(String roleDescription) {
		super();
		this.roleDescription = roleDescription;
	}

	public Role(int id, String roleDescription) {
		super();
		this.id = id;
		this.roleDescription = roleDescription;
	}

	private int id;
	private String roleDescription;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	
}

