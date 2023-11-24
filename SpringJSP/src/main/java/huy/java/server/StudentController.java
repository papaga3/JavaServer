package huy.java.server;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	private final StudentService studentService;

	public StudentController() {
	        this.studentService = new StudentService();
	}

	@GetMapping("/viewStudent")
	public String viewBooks(Model model) {
		model.addAttribute("students", studentService.findAll());
		return "view-student";
	}
	
	@GetMapping("/addStudent")
	public String addStudenView(Model model) {
		model.addAttribute("student", new StudentEntity());
		return "add-student";
	}
	
	@PostMapping("/addStudent")
	public RedirectView addStudent(
			@ModelAttribute("student") StudentEntity student,
			RedirectAttributes redirectAttributes
	) {
		final RedirectView rv = new RedirectView("/student/addStudent", true);
		StudentEntity savedStudent = studentService.addStudent(student);
		redirectAttributes.addFlashAttribute("student", savedStudent);
		redirectAttributes.addFlashAttribute("addStudentSuccess", true);
		return rv;
	}
}
