package interfaces;

import java.util.Collection;
import model.Page;

public interface PageDaoInterface {
	
	/**
	 * This method inserts properties in page instance parameter into the Page table. The page's websiteId foreign key refer to Website table primary key id whose value is equal to the websiteId parameter
	 * @param websiteId
	 * @param page
	 * @return integer
	*/
	public int createPageForWebsite(int websiteId, Page page);
	
	/**
	 * @return Collection<Page>; all records from Page table as a Collection of Page instances
	*/
	public Collection<Page> findAllPages();
	
	/**
	 * @param pageId
	 * @return a record from Page table whose id field is equal to the pageId parameter
	*/
	public Page findPageById(int pageId);
	
	/**
	 * @param websiteId
	 * @return Collection<Page>; all records from Page table as a Collection of Page instances whose websiteId is equal to the websiteId parameter
	*/
	public Collection<Page> findPagesForWebsite(int websiteId);
	
	/**
	 * This method updates record in Page table whose id field is equal to pageId parameter. New record field values are set to the values in the page instance parameter
	 * @param pageId
	 * @param page
	 * @return integer
	*/
	public int updatePage(int pageId, Page page);
	
	/**
	 * This method deletes record from Page table whose id field is equal to pageId parameter
	 * @param pageId
	 * @return integer
	*/
	public int deletePage(int pageId);
}

