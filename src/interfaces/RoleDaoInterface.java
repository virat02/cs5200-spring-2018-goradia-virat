package interfaces;

public interface RoleDaoInterface {
	/**
	 * This method inserts into table Role a record that assigns a developer whose id is developerId, the role with roleId, to the website with websiteId
	 * @param developerId
	 * @param websiteId
	 * @param roleId
	*/
	public void assignWebsiteRole(int developerId, int websiteId, int roleId); 
	
	/**
	 * This method inserts into table Role a record that assigns a developer whose id is developerId, the role with roleId, to the page with pageId
	 * @param developerId
	 * @param pageId
	 * @param roleId
	*/
	public void assignPageRole(int developerId, int pageId, int roleId); 
	
	/**
	 * This method deletes from table Role a record that removes roleId from developerId, on websiteId
	 * @param developerId
	 * @param websiteId
	 * @param roleId
	*/
	public void deleteWebsiteRole(int developerId, int websiteId, int roleId); 
	
	/**
	 * This method deletes from table Role a record that removes roleId from developerId, on pageId
	 * @param developerId
	 * @param pageId
	 * @param roleId
	*/
	public void deletePageRole(int developerId, int pageId, int roleId);	
}

