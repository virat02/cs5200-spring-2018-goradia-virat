package model;

import java.util.ArrayList;

public class RolePrivilege {

	
	public RolePrivilege() {
		super();
	}
	
	public RolePrivilege(String roleDescription, ArrayList<Privilege> privileges) {
		super();
		this.roleDescription = roleDescription;
		this.privileges = privileges;
	}

	public RolePrivilege(int roleId, int privilege) {
		super();
		this.roleId = roleId;
		this.privilege = privilege;
	}

	public RolePrivilege(String roleDescription, ArrayList<Privilege> privileges, int roleId, int privilege) {
		super();
		this.roleDescription = roleDescription;
		this.privileges = privileges;
		this.roleId = roleId;
		this.privilege = privilege;
	}

	private String roleDescription;
	private ArrayList<Privilege> privileges = new  ArrayList<Privilege>();
	private int roleId;
	private int privilege;
	
	public String getRoleDescription() {
		return roleDescription;
	}


	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}


	public ArrayList<Privilege> getPrivileges() {
		return privileges;
	}


	public void setPrivileges(ArrayList<Privilege> privileges) {
		this.privileges = privileges;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getPrivilege() {
		return privilege;
	}

	public void setPrivilege(int privilege) {
		this.privilege = privilege;
	}

}

