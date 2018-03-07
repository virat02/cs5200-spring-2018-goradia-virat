package main;

import java.util.ArrayList;

import dao.DeveloperDao;
import dao.PageDao;
import dao.RoleDao;
import dao.WebsiteDao;
import dao.WidgetDao;
import model.Developer;
import model.Page;
import model.PageRole;
import model.Person;
import model.Website;
import model.WebsiteRole;
import model.Widget;

public class hw3_jdbc_goradia_virat {

	public static void main (String args[]) {

		WebsiteDao websiteDao = WebsiteDao.getInstance();
		PageDao pageDao =  PageDao.getInstance();
		DeveloperDao developerDao = DeveloperDao.getInstance();
		WidgetDao widgetDao = WidgetDao.getInstance();
		RoleDao roleDao = RoleDao.getInstance();

		//CREATING DEVELOPERS AND USERS
		
		//ALICE.
		Person a = new Person("Alice","Wonder","alice","alice","alice@wonder.com",null);
		Developer d = new Developer("4321rewq",a);
		developerDao.createDeveloper(d);

		//BOB
		Person b = new Person("Bob","Marley","bob","bob","bob@marley.com",null);
		d = new Developer("5432trew",b);
		developerDao.createDeveloper(d);

		//CHARLES
		Person c = new Person("Charles","Garcia","charlie","charlie","chuch@garcia.com",null);
		d = new Developer("6543ytre",c);
		developerDao.createDeveloper(d);

		//DAN
		Person d1 = new Person("Dan","Martin","dan","dan","dan@martin.com",null);
		d = new Developer("7654fda",d1);
		developerDao.createDeveloper(d);

		//ED
		Person e = new Person("Ed","Karaz","ed","ed","ed@kar.com",null);
		d = new Developer("5678dfgh",e);
		developerDao.createDeveloper(d);
		

		//CREATING WEBSITES FOR RESPECTIVE DEVELOPERS
		
		//FACEBOOK WEBSITE
		ArrayList<WebsiteRole> websiteRoles = new ArrayList<WebsiteRole>();

		WebsiteRole websiteOwner = new WebsiteRole();
		websiteOwner.setDeveloperUsername("alice");
		websiteOwner.setRoleDescription("owner");
		websiteRoles.add(websiteOwner);
		WebsiteRole websiteEditor = new WebsiteRole();
		websiteEditor.setDeveloperUsername("bob");
		websiteEditor.setRoleDescription("editor");
		websiteRoles.add(websiteEditor);
		WebsiteRole websiteAdmin = new WebsiteRole();
		websiteAdmin.setDeveloperUsername("charlie");
		websiteAdmin.setRoleDescription("admin");
		websiteRoles.add(websiteAdmin);
		Website fb = new Website("Facebook","an online social media and social networking service",1234234,websiteRoles);
		websiteDao.createWebsiteForDeveloper("alice", fb);

		//TWITTER WEBSITE
		websiteRoles = new ArrayList<WebsiteRole>();
		websiteOwner = new WebsiteRole();
		websiteOwner.setDeveloperUsername("bob");
		websiteOwner.setRoleDescription("owner");
		websiteRoles.add(websiteOwner);
		websiteEditor = new WebsiteRole();
		websiteEditor.setDeveloperUsername("charlie");
		websiteEditor.setRoleDescription("editor");
		websiteRoles.add(websiteEditor);
		websiteAdmin = new WebsiteRole();
		websiteAdmin.setDeveloperUsername("alice");
		websiteAdmin.setRoleDescription("admin");
		websiteRoles.add(websiteAdmin);
		Website twt = new Website("Twitter","an online news and social networking service",4321543,websiteRoles);
		websiteDao.createWebsiteForDeveloper("bob", twt);

		//WIKIPEDIA WEBSITE
		websiteRoles = new ArrayList<WebsiteRole>();
		websiteOwner = new WebsiteRole();
		websiteOwner.setDeveloperUsername("charlie");
		websiteOwner.setRoleDescription("owner");
		websiteRoles.add(websiteOwner);
		websiteEditor = new WebsiteRole();
		websiteEditor.setDeveloperUsername("alice");
		websiteEditor.setRoleDescription("editor");
		websiteRoles.add(websiteEditor);
		websiteAdmin = new WebsiteRole();
		websiteAdmin.setDeveloperUsername("bob");
		websiteAdmin.setRoleDescription("admin");
		websiteRoles.add(websiteAdmin);
		Website wiki = new Website("Wikipedia","a free online encyclopedia",3456654,websiteRoles);
		websiteDao.createWebsiteForDeveloper("charlie", wiki);

		//CNN WEBSITE
		websiteRoles = new ArrayList<WebsiteRole>();
		websiteOwner = new WebsiteRole();
		websiteOwner.setDeveloperUsername("alice");
		websiteOwner.setRoleDescription("owner");
		websiteRoles.add(websiteOwner);
		websiteEditor = new WebsiteRole();
		websiteEditor.setDeveloperUsername("bob");
		websiteEditor.setRoleDescription("editor");
		websiteRoles.add(websiteEditor);
		websiteAdmin = new WebsiteRole();
		websiteAdmin.setDeveloperUsername("charlie");
		websiteAdmin.setRoleDescription("admin");
		websiteRoles.add(websiteAdmin);
		Website cnn = new Website("CNN","an American basic cable and satellite television news channel",6543345,websiteRoles);
		websiteDao.createWebsiteForDeveloper("alice", cnn);

		//CNET WEBSITE
		websiteRoles = new ArrayList<WebsiteRole>();
		websiteOwner = new WebsiteRole();
		websiteOwner.setDeveloperUsername("bob");
		websiteOwner.setRoleDescription("owner");
		websiteRoles.add(websiteOwner);
		websiteEditor = new WebsiteRole();
		websiteEditor.setDeveloperUsername("charlie");
		websiteEditor.setRoleDescription("editor");
		websiteRoles.add(websiteEditor);
		websiteAdmin = new WebsiteRole();
		websiteAdmin.setDeveloperUsername("alice");
		websiteAdmin.setRoleDescription("admin");
		websiteRoles.add(websiteAdmin);
		Website cnet = new Website("CNET","an American media website that publishes reviews, news, articles, blogs, podcasts and videos on technology and consumer electronics",5433455,websiteRoles);
		websiteDao.createWebsiteForDeveloper("bob", cnet);

		//GIZMODO WEBSITE
		websiteRoles = new ArrayList<WebsiteRole>();
		websiteOwner = new WebsiteRole();
		websiteOwner.setDeveloperUsername("charlie");
		websiteOwner.setRoleDescription("owner");
		websiteRoles.add(websiteOwner);
		websiteEditor = new WebsiteRole();
		websiteEditor.setDeveloperUsername("alice");
		websiteEditor.setRoleDescription("editor");
		websiteRoles.add(websiteEditor);
		websiteAdmin = new WebsiteRole();
		websiteAdmin.setDeveloperUsername("bob");
		websiteAdmin.setRoleDescription("admin");
		websiteRoles.add(websiteAdmin);
		Website gizmodo = new Website("Gizmodo","a design, technology, science and science fiction website that also writes articles on politics",4322345,websiteRoles);
		websiteDao.createWebsiteForDeveloper("charlie", gizmodo);

		
		//CREATING PAGES FOR THE RESPECTIVE WEBSITES
		
		//HOME PAGE FOR CNET
		ArrayList<PageRole> pageRoles = new ArrayList<PageRole>();
		PageRole pageEditor = new PageRole();
		pageEditor.setDeveloperUsername("alice");
		pageEditor.setRoleDescription("editor");
		pageRoles.add(pageEditor);
		PageRole pageReviewer = new PageRole();
		pageReviewer.setDeveloperUsername("bob");
		pageReviewer.setRoleDescription("reviewer");
		pageRoles.add(pageReviewer);
		PageRole pageWriter = new PageRole();
		pageWriter.setDeveloperUsername("charlie");
		pageWriter.setRoleDescription("writer");
		pageRoles.add(pageWriter);
		Page homePage = new Page("Home","Landing page",123434,pageRoles);
		pageDao.createPageForWebsite("CNET", homePage);

		//ABOUT PAGE FOR GIZMODO
		pageRoles = new ArrayList<PageRole>();
		pageEditor = new PageRole();
		pageEditor.setDeveloperUsername("bob");
		pageEditor.setRoleDescription("editor");
		pageRoles.add(pageEditor);
		pageReviewer = new PageRole();
		pageReviewer.setDeveloperUsername("charlie");
		pageReviewer.setRoleDescription("reviewer");
		pageRoles.add(pageReviewer);
		pageWriter = new PageRole();
		pageWriter.setDeveloperUsername("alice");
		pageWriter.setRoleDescription("writer");
		pageRoles.add(pageWriter);
		Page abtPg = new Page("About","Website description",234545,pageRoles);
		pageDao.createPageForWebsite("Gizmodo", abtPg);

		//CONTACT PAGE FOR WIKIPEDIA
		pageRoles = new ArrayList<PageRole>();
		pageEditor = new PageRole();
		pageEditor.setDeveloperUsername("charlie");
		pageEditor.setRoleDescription("editor");
		pageRoles.add(pageEditor);
		pageReviewer = new PageRole();
		pageReviewer.setDeveloperUsername("alice");
		pageReviewer.setRoleDescription("reviewer");
		pageRoles.add(pageReviewer);
		pageWriter = new PageRole();
		pageWriter.setDeveloperUsername("bob");
		pageWriter.setRoleDescription("writer");
		pageRoles.add(pageWriter);
		Page contactPg = new Page("Contact","Addresses, phones, and contact info",345656,pageRoles);
		pageDao.createPageForWebsite("Wikipedia", contactPg);

		//PREFERENCES PAGE FOR CNN
		pageRoles = new ArrayList<PageRole>();
		pageEditor = new PageRole();
		pageEditor.setDeveloperUsername("alice");
		pageEditor.setRoleDescription("editor");
		pageRoles.add(pageEditor);
		pageReviewer = new PageRole();
		pageReviewer.setDeveloperUsername("bob");
		pageReviewer.setRoleDescription("reviewer");
		pageRoles.add(pageReviewer);
		pageWriter = new PageRole();
		pageWriter.setDeveloperUsername("charlie");
		pageWriter.setRoleDescription("writer");
		pageRoles.add(pageWriter);
		Page prefPg = new Page("Preferences","Where users can configure their preferences",456776,pageRoles);
		pageDao.createPageForWebsite("CNN", prefPg);

		//PROFILE PAGE FOR CNN
		pageRoles = new ArrayList<PageRole>();
		pageEditor = new PageRole();
		pageEditor.setDeveloperUsername("bob");
		pageEditor.setRoleDescription("editor");
		pageRoles.add(pageEditor);
		pageReviewer = new PageRole();
		pageReviewer.setDeveloperUsername("charlie");
		pageReviewer.setRoleDescription("reviewer");
		pageRoles.add(pageReviewer);
		pageWriter = new PageRole();
		pageWriter.setDeveloperUsername("alice");
		pageWriter.setRoleDescription("writer");
		pageRoles.add(pageWriter);
		Page profilePg = new Page("Profile","Users can configure their personal information",567878,pageRoles);
		pageDao.createPageForWebsite("CNET", profilePg);

		
		//CREATING WIDGETS FOR THE RESPECTIVE PAGES
		
		//Head123
		Widget head123 = new Widget("head123","heading",0,"Welcome");
		widgetDao.createWidgetForPage("Home", head123);

		//post234
		Widget post234 = new Widget("post234","html",0,"<p>Lorem</p>");
		widgetDao.createWidgetForPage("About", post234);

		//head345
		Widget head345 = new Widget("head345","heading",1,"Hi");
		widgetDao.createWidgetForPage("Contact", head345);

		//intro456
		Widget intro456 = new Widget("intro456","html",2,"<h1>Hi</h1>");
		widgetDao.createWidgetForPage("Contact", intro456);

		//image345
		Widget image345 = new Widget("image345", 50, 100, 3,"/img/567.png", "image");
		widgetDao.createWidgetForPage("Contact", image345);

		//video456
		Widget video456 = new Widget("video456", 400, 300, 0,"https://youtu.be/h67VX51QXiQ", "youtube");
		widgetDao.createWidgetForPage("Preferences", video456);


		//IMPLEMENTING UPDATES
		
		//Updates the relative order of widget "head345" to 3 and update other widget's orders as well on the page.
		widgetDao.updateWidgetOrder ("head345", 3); 
		
		//Update page - Append 'CNET - ' to the beginning of all CNET's page titles
		pageDao.appendWebsiteNameToPageNamesForWebsite("CNET-",  "CNET"); 
		
		//Update roles - Swap Charlie's and Bob's role in CNET's Home page
		roleDao.swapDeveloperPageRoles("charlie","bob","CNET","CNET-Home"); 
		
		
		//IMPLEMENTING DELETES
		
		//Delete widget - Remove the last widget in the Contact page.
		//The last widget is the one with the highest value in the order field. 
		widgetDao.deleteLastWidgetFromPage("Contact");  
       
	    //Delete page - Remove the last updated page in Wikipedia
		pageDao.deleteLastUpdatedPageForWebsite( "Wikipedia"); 
		
	    //Delete website - Remove the CNET web site, as well as all related roles and privileges 
		websiteDao.deleteWebsiteAndRelatedEntries("CNET");   	 
	}

}
