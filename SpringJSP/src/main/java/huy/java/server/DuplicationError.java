package huy.java.server;

public class DuplicationError extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private final StudentEntity student;
	
	public DuplicationError(StudentEntity student) {
		this.student = student;
	}

	public StudentEntity getStudent() {
		return student;
	}
	
}
