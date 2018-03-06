package interfaces;

import java.util.Collection;
import model.Developer;

/**
 * @author virat
 */
public interface DeveloperDaoInterface {

	/**
	 * This method inserts properties in developer instance parameter in tables Developer and Person
	 * @param developer
	 * @return integer
	*/
	public int createDeveloper(Developer developer);   
	
	/**
	 * @return Collection<Developer>; all joined records from Developer and Person tables as a Collection of Developer instances
	*/
	public Collection<Developer> findAllDevelopers();  
	
	/**
	 * @param developerId
	 * @return a joined record from Developer and Person tables whose id field is equal to the developerId parameter
	*/
	public Developer findDeveloperById(int developerId); 
	
	/**
	 * @param username
	 * @return a joined record from Developer and Person tables whose username field matches the parameter
	*/
	public Developer findDeveloperByUsername(String username); 
	
	/**
	 * @param username
	 * @param password
	 * @return a joined record from Developer and Person tables whose username and password fields match the parameters
	*/
	public Developer findDeveloperByCredentials(String username, String password); 
	
	/**
	 * This method updates records in Developer and Person tables whose id field is equal to developerId parameter. New record field values are set to the values in the developer instance parameter
	 * @param developerId
	 * @param developer
	 * @return integer
	*/
	public int updateDeveloper(int developerId, Developer developer); 
	
	/**
	 * This method deletes records from Developer and Person tables whose id field is equal to developerId parameter
	 * @param developerId
	 * @return integer
	 */
	public int deleteDeveloper(int developerId); 
}

