package huy.java.server;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StudentService {
	private final Map<String, StudentEntity> storedStudent;
	
	public StudentService() {
		storedStudent = new HashMap<>();
	}
	
	public Collection<StudentEntity>findAll() {
		if(storedStudent.isEmpty()) {
			return Collections.emptyList();
		}
		return storedStudent.values();
	}
	
	public StudentEntity addStudent(StudentEntity student) {
		if(storedStudent.containsKey(student.getId())) {
			throw new DuplicationError(student);
		}
		storedStudent.put(student.getId(), student);
		return student;
	}
}
