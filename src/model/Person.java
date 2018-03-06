package model;

import java.util.ArrayList;
import java.util.Date;

public class Person {

	public Person() {
		super();
	}

	public Person(String firstName, String lastName, String username, String password, String email, Date dob) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.dob = dob;
	}

	public Person(String firstName, String lastName, String username, String password, ArrayList<Developer> developers,
			ArrayList<User> users) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.developers = developers;
		this.users = users;
	}

	public Person(int id, String firstName, String lastName, String username, String password, String email, Date dob,
			ArrayList<Developer> developers,
			ArrayList<User> users) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.dob = dob;
		this.developers = developers;
		this.users = users;
	}

	private int id;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String email;
	private Date dob;
	private ArrayList<Developer> developers = new ArrayList<Developer>();
	private ArrayList<User> users = new ArrayList<User>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public ArrayList<Developer> getDevelopers() {
		return developers;
	}

	public void setDevelopers(ArrayList<Developer> developers) {
		this.developers = developers;
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
				+ ", password=" + password + ", email=" + email + ", dob=" + dob  +"]\n";
	}


}
