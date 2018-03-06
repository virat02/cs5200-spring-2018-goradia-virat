package model;

public class User {
	
	public User() {
		super();
	}
	
	public User(String userKey, Person person) {
		super();
		this.userKey = userKey;
		this.person = person;
	}
	

	public User(int id, boolean userAgreement, String userKey, Person person) {
		super();
		this.id = id;
		this.userAgreement = userAgreement;
		this.userKey = userKey;
		this.person = person;
	}

	private int id;
	private boolean userAgreement;
	private String userKey;
	private Person person;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isUserAgreement() {
		return userAgreement;
	}

	public void setUserAgreement(boolean userAgreement) {
		this.userAgreement = userAgreement;
	}

	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
}

