package dao;

import org.apache.commons.lang3.StringUtils;
import sqlQueries.StringQueries;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import interfaces.DeveloperDaoInterface;
import model.Developer;
import model.Person;

public class DeveloperDao extends BaseDao implements DeveloperDaoInterface{

	static DeveloperDao instance = null;

	/**
	 * Singleton Class constructor
	 */
	private DeveloperDao() {
	}
	public static DeveloperDao getInstance() {
		if(instance == null) {
			return new DeveloperDao();
		}
		return instance;
	}
	
	/**
	 * @param person
	 * @return integer
	 * Method to create a person in Database, inserts properties in Person instance parameter in table Person.
	 */
	public int createPerson(Person person) 
	{
		int result = -1;
		Developer developer = new Developer();
		try {
			Class.forName(JDBC_DRIVER); 
			Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			String sql = StringQueries.INSERT_PERSON_QUERY;
			PreparedStatement pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, person.getId());
			pstmt.setString(2, person.getFirstName());
			pstmt.setString(3, person.getLastName());
			pstmt.setString(4, person.getUsername());
			pstmt.setString(5, person.getPassword());
			pstmt.setString(6, person.getEmail());
			pstmt.setDate(7, (Date) person.getDob());
			result = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Record for person "+developer.getPerson().getFirstName()+" "+developer.getPerson().getLastName()+" inserted successfully.");
		return result;
	}

	/* (non-Javadoc)
	 * @see interfaces.DeveloperPersonInterface#createDeveloper(model.Developer)
	 */
	public int createDeveloper(Developer developer) 
	{
		int result = -1;
		try {

			Class.forName(JDBC_DRIVER); 
			Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			createPerson(developer.getPerson());
			String sql = StringQueries.INSERT_DEVELOPER_QUERY;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, developer.getId());
			pstmt.setInt(2, getPersonIdByUsername(developer.getPerson().getUsername()));
			pstmt.setString(3, developer.getDeveloperKey());
			result = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		System.out.println("Record for developer "+developer.getPerson().getFirstName()+" "+developer.getPerson().getLastName()+" inserted successfully.");
		return result;
	}

	/**
	 * @param username
	 * @return integer
	 * Given input as the person's username, returns their Id.
	 */
	public int getPersonIdByUsername(String username) 
	{
		ResultSet result = null;
		int personId = 0;
		try {
			Class.forName(JDBC_DRIVER); 
			Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			String sql = StringQueries.FIND_PERSON_ID_BY_NAME;
			PreparedStatement pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, username);
			result = pstmt.executeQuery();
			while(result.next()) {
				personId = result.getInt("id");
			}
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return personId;
	}

	/**
	 * @param developerUsername
	 * @return integer
	 * Given input as the developer's username, returns their developer Id.
	 */
	public int getDeveloperIdByUsername(String developerUsername) 
	{
		ResultSet result = null;
		int developerId = 0;
		try {
			Class.forName(JDBC_DRIVER); 
			Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			String sql = StringQueries.FIND_DEVELOPER_ID_BY_NAME;
			PreparedStatement pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, developerUsername);
			result = pstmt.executeQuery();
			while(result.next()) {
				developerId = result.getInt("id");
			}
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return developerId;
	}
	
	/* (non-Javadoc)
	 * @see interfaces.DeveloperPersonInterface#findAllDevelopers()
	 */
	public ArrayList <Developer> findAllDevelopers() 
	{
		ResultSet resultSet = null;
		ArrayList <Developer> developers = new ArrayList<Developer>();
		try {
			Class.forName(JDBC_DRIVER); 
			Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			String sql = StringQueries.FIND_ALL_DEVELOPERS;
			PreparedStatement pstmt= conn.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				Person person = new Person (resultSet.getString("firstName"), resultSet.getString("lastName"),resultSet.getString("username"),resultSet.getString("password"),resultSet.getString("email"),resultSet.getDate("dob"));
				
				Developer developer = new Developer(resultSet.getInt("id"),resultSet.getString("developerKey"),person);
				
				developers.add(developer); 
			}
			pstmt.close();
			conn.close();
		} catch (SQLException e) { 
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return developers;
	}
	
	/* (non-Javadoc)
	 * @see interfaces.DeveloperPersonInterface#findDeveloperById(int)
	 */
	public Developer findDeveloperById(int developerId) 
	{
		ResultSet resultSet = null;
		Developer developer1 = new Developer();
		ArrayList<Developer> developers= new ArrayList<Developer>();
		try {
			Class.forName(JDBC_DRIVER); 
			Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			String sql = StringQueries.FIND_DEVELOPER_BY_ID;
			PreparedStatement pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, developerId);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				Person person = new Person (resultSet.getString("firstName"), resultSet.getString("lastName"),resultSet.getString("username"),resultSet.getString("password"),resultSet.getString("email"),resultSet.getDate("dob"));
				
				Developer developer = new Developer(resultSet.getInt("id"),resultSet.getString("developerKey"),person);
				
				developers.add(developer); 
			}
			if(!developers.isEmpty())
				developer1 = developers.get(0);
			pstmt.close();
			conn.close();
		} catch (SQLException e) { 
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return developer1;
	}
	
	/* (non-Javadoc)
	 * @see interfaces.DeveloperPersonInterface#findDeveloperByUsername(java.lang.String)
	 */
	public Developer findDeveloperByUsername(String username)  
	{ 
		ResultSet resultSet = null;
		Developer developer1 = new Developer();
		ArrayList<Developer> developers= new ArrayList<Developer>();
		try {
			Class.forName(JDBC_DRIVER); 
			Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			String sql = StringQueries.FIND_DEVELOPER_BY_USERNAME;
			PreparedStatement pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, username);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				Person person = new Person (resultSet.getString("firstName"), resultSet.getString("lastName"),resultSet.getString("username"),resultSet.getString("password"),resultSet.getString("email"),resultSet.getDate("dob"));
				
				Developer developer = new Developer(resultSet.getInt("id"),resultSet.getString("developerKey"),person);
				
				developers.add(developer); 
			}
			if(!developers.isEmpty())
				developer1 = developers.get(0);
			pstmt.close();
			conn.close();
		} catch (SQLException e) { 
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return developer1;
	}
	
	/* (non-Javadoc)
	 * @see interfaces.DeveloperPersonInterface#findDeveloperByCredentials(java.lang.String, java.lang.String)
	 */
	public Developer findDeveloperByCredentials(String username, String password) 
	{ 
		ResultSet resultSet = null;
		Developer developer1 = new Developer();
		ArrayList<Developer> developers = new ArrayList<Developer>();

		try {
			Class.forName(JDBC_DRIVER); 
			Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			String sql = StringQueries.FIND_DEVELOPER_BY_CREDENTIALS;
			PreparedStatement pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				Person person = new Person (resultSet.getString("firstName"), 
											resultSet.getString("lastName"),
											resultSet.getString("username"),
											resultSet.getString("password"),
											resultSet.getString("email"),
											resultSet.getDate("dob"));
				
				Developer developer = new Developer(resultSet.getInt("id"),
													resultSet.getString("developerKey"),
													person);
				
				developers.add(developer); 
			}
			if(!developers.isEmpty())
				developer1 = developers.get(0);
			pstmt.close();
			conn.close();
		} catch (SQLException e) { 
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return developer1;
	}
	
	/* (non-Javadoc)
	 * @see interfaces.DeveloperPersonInterface#updateDeveloper(int, model.Developer)
	 */
	public int updateDeveloper(int developerId, Developer developer) 	
	{
		int result = -1;
		try {
			Class.forName(JDBC_DRIVER); 
			Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			String sql = StringQueries.UPDATE_DEVELOPER;
			Developer oldDeveloper = findDeveloperById(developerId);
			developer.setPerson( getUpdatedPerson (developer.getPerson(), oldDeveloper.getPerson()));
			PreparedStatement pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, developer.getPerson().getId());
			pstmt.setString(2, developer.getDeveloperKey());
			pstmt.setInt(3, developerId);
			updatePerson(developerId, developer.getPerson());
			result = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) { 
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(developer.getPerson().getFirstName() +" details updated successfully.");
		return result;
	}

	/**
	 * @param newPerson
	 * @param oldPerson
	 * @return Person
	 * Merge the person details depending on details received from user and Database.
	 */
	public Person getUpdatedPerson(Person newPerson, Person oldPerson)
	{
		try {
			if (newPerson.getId() == 0) 
				newPerson.setId(oldPerson.getId());
			if (StringUtils.isBlank (newPerson.getFirstName())) 
				newPerson.setFirstName(oldPerson.getFirstName());
			if (StringUtils.isBlank (newPerson.getLastName())) 
				newPerson.setLastName(oldPerson.getLastName());
			if (StringUtils.isBlank (newPerson.getUsername())) 
				newPerson.setUsername(oldPerson.getUsername());
			if (StringUtils.isBlank (newPerson.getPassword())) 
				newPerson.setPassword(oldPerson.getPassword());
			if (StringUtils.isBlank (newPerson.getEmail())) 
				newPerson.setEmail(oldPerson.getEmail());
			if ((newPerson.getDob()) != null) 
				newPerson.setDob(oldPerson.getDob());
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return newPerson;
	}

	/**
	 * @param developerId
	 * @param person
	 * @return integer
	 * Updates person details in database.
	 */
	public int updatePerson(int developerId, Person person) 	 
	{
		int result = -1;
		try {
			Class.forName(JDBC_DRIVER); 
			Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			String sql = StringQueries.UPDATE_PERSON;
			PreparedStatement pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, person.getFirstName());
			pstmt.setString(2, person.getLastName());
			pstmt.setString(3, person.getUsername());
			pstmt.setString(4, person.getPassword());
			pstmt.setString(5, person.getEmail());
			pstmt.setDate(6, (Date) person.getDob());
			pstmt.setInt(7, developerId);
			result = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) { 
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @param developerId
	 * @return integer
	 * Deletes person from database, on basis of developer id.
	 */
	public int deletePerson(int developerId)   
	{
		int result = -1;
		try {
			Class.forName(JDBC_DRIVER); 
			Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			String sql = StringQueries.DELETE_PERSON;
			PreparedStatement pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, developerId);
			result = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) { 
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/* (non-Javadoc)
	 * @see interfaces.DeveloperPersonInterface#deleteDeveloper(int)
	 */
	public int deleteDeveloper(int developerId)  
	{
		int result = -1;
		try {
			Class.forName(JDBC_DRIVER); 
			Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			String sql = StringQueries.DELETE_DEVELOPER;
			PreparedStatement pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, developerId);
			deletePerson(developerId);
			result = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) { 
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(result + " record(s) deleted successfully.");
		return result;
	}
}