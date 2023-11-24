package huy.java.server;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class StudentControllerAdvice {
	@ExceptionHandler(value = DuplicationError.class)
	public ModelAndView duplicateStudentException(DuplicationError e) {
		final ModelAndView mav = new ModelAndView();
		mav.addObject("ref", e.getStudent().getId());
		mav.addObject("object", e.getStudent());
		mav.addObject("message", "Cannot add an already existing book");
		mav.setViewName("error-student");
		return mav;
	}
}
