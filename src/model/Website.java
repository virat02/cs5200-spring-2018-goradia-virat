package model;

import java.util.ArrayList;
import java.util.Date;

public class Website {
	
	public Website() {
		super();
	}
	
	public Website(String name, String description, int visits, ArrayList<WebsiteRole> websiteRoles) {
		super();
		this.name = name;
		this.description = description;
		this.visits = visits;
		this.websiteRoles = websiteRoles;
	}

	public Website(String name, Date created, Date updated, Developer developer, ArrayList<Page> pages,
			ArrayList<WebsiteRole> websiteRoles, ArrayList<WebsitePrivilege> websitePrivileges) {
		super();
		this.name = name;
		this.created = created;
		this.updated = updated;
		this.developer = developer;
		this.pages = pages;
		this.websiteRoles = websiteRoles;
		this.websitePrivileges = websitePrivileges;
	}

	public Website(int id, String name, String description, Date created, Date updated, int visits, Developer developer,
			ArrayList<Page> pages, ArrayList<WebsiteRole> websiteRoles, ArrayList<WebsitePrivilege> websitePrivileges) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.created = created;
		this.updated = updated;
		this.visits = visits;
		this.developer = developer;
		this.pages = pages;
		this.websiteRoles = websiteRoles;
		this.websitePrivileges = websitePrivileges;
	}
	
	private int id;
	private String name;
	private String description;
	private Date created;
	private Date updated;
	private int visits;
	private Developer developer = new Developer();
	private ArrayList<Page> pages = new ArrayList<Page>();
	private ArrayList<WebsiteRole> websiteRoles = new ArrayList<WebsiteRole>();
	private ArrayList<WebsitePrivilege> websitePrivileges = new ArrayList<WebsitePrivilege>();
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public int getVisits() {
		return visits;
	}
	public void setVisits(int visits) {
		this.visits = visits;
	}
	public Developer getDeveloper() {
		return developer;
	}
	public void setDeveloper(Developer developer) {
		this.developer = developer;
	}
	public ArrayList<Page> getPages() {
		return pages;
	}
	public void setPages(ArrayList<Page> pages) {
		this.pages = pages;
	}
	public ArrayList<WebsiteRole> getWebsiteRoles() {
		return websiteRoles;
	}
	public void setWebsiteRoles(ArrayList<WebsiteRole> websiteRoles) {
		this.websiteRoles = websiteRoles;
	}
	public ArrayList<WebsitePrivilege> getWebsitePrivileges() {
		return websitePrivileges;
	}
	public void setWebsitePriviledges(ArrayList<WebsitePrivilege> websitePrivileges) {
		this.websitePrivileges = websitePrivileges;
	}

	@Override
	public String toString() {
		return "Website [id=" + id + ", name=" + name + ", description=" + description + ", created=" + created
				+ ", updated=" + updated + ", visits=" + visits + "]\n";
	}
}