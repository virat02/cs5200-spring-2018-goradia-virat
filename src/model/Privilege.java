package model;

public class Privilege {
	
	private int id;
	private String privilegeName;
	
	public Privilege() {
		super();
	}	

	public Privilege(int id) {
		super();
		this.id = id;
	}
	
	public Privilege(int id, String privilegeName) {
		super();
		this.id = id;
		this.privilegeName = privilegeName;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getPrivilegeName() {
		return privilegeName;
	}

	public void setId(String privilegeName) {
		this.privilegeName = privilegeName;
	}
	
}


