package interfaces;

import java.util.Collection;
import model.Website;

public interface WebsiteDaoInterface {
	
	/**
	 * This method inserts properties in website instance parameter into the Website table. 
	 * The website's developerId foreign key refer to Developer table primary key id whose value is equal to the developerId parameter.
	 * @param developerId
	 * @param website
	 * @return integer
	*/
	public int createWebsiteForDeveloper(int developerId, Website website); 
	
	/**
	 * @return Collection<Website>; all records from Website table as a Collection of Website instances.
	*/
	public Collection<Website> findAllWebsites(); 
	
	/**
	 * @param developerId
	 * @return Collection<Website>; all records from Website table as a Collection of Website instances whose developerId is equal to the developerId parameter.
	*/
	public Collection<Website> findWebsitesForDeveloper(int developerId); 
	
	/**
	 * @param websiteId
	 * @return a record from Website table whose id field is equal to the websiteId parameter.
	*/
	public Website findWebsiteById(int websiteId);
	
	/**
	 * This method updates record in Website table whose id field is equal to websiteId parameter. New record field values are set to the values in the website instance parameter.
	 * @param websiteId
	 * @param website
	 * @return integer
	*/
	public int updateWebsite(int websiteId, Website website); 
	
	/**
	 * This method deletes record from Website table whose id field is equal to websiteId parameter.
	 * @param websiteId
	 * @return integer
	*/
	public int deleteWebsite(int websiteId); 
}
