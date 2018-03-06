package model;

import java.util.ArrayList;
import java.util.Date;

public class Page {

	
	public Page() {
		super();
	}
	
	public Page(String title, String description, int views, ArrayList<PageRole> pageRoles) {
		super();
		this.title = title;
		this.description = description;
		this.views = views;
		this.pageRoles = pageRoles;
	}

	public Page(String title, Date created, Date updated, Website website, ArrayList<Widget> widgets,
			ArrayList<PageRole> pageRoles, ArrayList<PagePrivilege> pagePrivileges) {
		super();
		this.title = title;
		this.created = created;
		this.updated = updated;
		this.website = website;
		this.widgets = widgets;
		this.pageRoles = pageRoles;
		this.pagePrivileges = pagePrivileges;
	}

	
	public Page(int id, String title, String description, Date created, Date updated, int views, Website website,
			ArrayList<Widget> widgets, ArrayList<PageRole> pageRoles, ArrayList<PagePrivilege> pagePrivileges) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.created = created;
		this.updated = updated;
		this.views = views;
		this.website = website;
		this.widgets = widgets;
		this.pageRoles = pageRoles;
		this.pagePrivileges = pagePrivileges;
	}

	private int id;
	private String title;
	private String description;
	private Date created;
	private Date updated;
	private int views;
	private Website website = new Website();
	private ArrayList<Widget> widgets = new ArrayList<Widget>();
	private ArrayList<PageRole> pageRoles = new ArrayList<PageRole>();
	private ArrayList<PagePrivilege> pagePrivileges = new ArrayList<PagePrivilege>();
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public Website getWebsite() {
		return website;
	}

	public void setWebsite(Website website) {
		this.website = website;
	}

	public ArrayList<Widget> getWidgets() {
		return widgets;
	}

	public void setWidgets(ArrayList<Widget> widgets) {
		this.widgets = widgets;
	}

	public ArrayList<PageRole> getPageRoles() {
		return pageRoles;
	}

	public void setPageRoles(ArrayList<PageRole> pageRoles) {
		this.pageRoles = pageRoles;
	}

	public ArrayList<PagePrivilege> getPagePriviledges() {
		return pagePrivileges;
	}

	public void setPagePriviledges(ArrayList<PagePrivilege> pagePrivileges) {
		this.pagePrivileges = pagePrivileges;
	}

	@Override
	public String toString() {
		return "Page [id=" + id + ", title=" + title + ", description=" + description + ", created=" + created
				+ ", updated=" + updated + ", views=" + views + "]\n";
	}
	
}

