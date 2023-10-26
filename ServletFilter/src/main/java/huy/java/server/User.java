package huy.java.server;

public class User {

	private String name;

	public User(String inputName) {
		name = inputName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
