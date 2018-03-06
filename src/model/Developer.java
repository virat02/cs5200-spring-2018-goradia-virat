package model;

public class Developer {
	
	public Developer() {
		super();
	}
	
	public Developer(String developerKey, Person person) {
		super();
		this.developerKey = developerKey;
		this.person = person;
	}
	
	
	public Developer(int id, String developerKey, Person person) {
		super();
		this.id = id;
		this.developerKey = developerKey;
		this.person = person;
	}


	private int id;
	private String developerKey;
	private Person person = new Person();
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDeveloperKey() {
		return developerKey;
	}

	public void setDeveloperKey(String developerKey) {
		this.developerKey = developerKey;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public String toString() {
		return "Developer [id=" + id + ", developerKey=" + developerKey + ", person=" + person + "]\n";
	}
}

