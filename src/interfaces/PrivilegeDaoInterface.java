package interfaces;

public interface PrivilegeDaoInterface {
	/**
	 * This method inserts into table Privilege a record that assigns a developer whose id is developerId, the privilege with privilegeId, to the website with websiteId
	 * @param developerId
	 * @param websiteId
	 * @param privilegeId
	*/
	public void assignWebsitePrivilege(int developerId, int websiteId, int privilegeId);
	
	/**
	 * This method inserts into table Privilege a record that assigns a developer whose id is developerId, the privilege with privilegeId, to the page with pageId
	 * @param developerId
	 * @param pageId
	 * @param privilegeId
	*/
	public void assignPagePrivilege(int developerId, int pageId, int privilegeId); 
	
	/**
	 * This method deletes from table Privilege a record that removes privilegeId from developerId, on websiteId
	 * @param developerId
	 * @param websiteId
	 * @param privilegeId
	*/
	public void deleteWebsitePrivilege(int developerId, int websiteId, int privilegeId); 
	
	/**
	 * This method deletes from table privilege a record that removes privilegeId from developerId, on pageId
	 * @param developerId
	 * @param pageId
	 * @param privilegeId
	*/
	public void deletePagePrivilege(int developerId, int pageId, int privilegeId);
}

