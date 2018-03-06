package model;

public class Privilege {
	
	public Privilege() {
		super();
	}
	
	public Privilege(String privilegeDescription) {
		super();
		this.privilegeDescription = privilegeDescription;
	}
	

	public Privilege(int id, String privilegeDescription) {
		super();
		this.id = id;
		this.privilegeDescription = privilegeDescription;
	}

	private int id;
	private String privilegeDescription;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPrivilegeDescription() {
		return privilegeDescription;
	}

	public void setPrivilegeDescription(String privilegeDescription) {
		this.privilegeDescription = privilegeDescription;
	}
	
}


