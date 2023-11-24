package huy.java.server;

public class StudentEntity {
	
	private String id;
	private String lastName;
	private String firstName;
	
	public StudentEntity() {}
	
	public StudentEntity(String inId, String inLastName, String inFirstName) {
		id = inId;
		lastName = inLastName;
		firstName = inFirstName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	
}
