package tr.org.lkd.lyk2015.camp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tr.org.lkd.lyk2015.camp.model.Instructor;
import tr.org.lkd.lyk2015.camp.service.CourseService;
import tr.org.lkd.lyk2015.camp.service.InstructorService;



@Controller
@RequestMapping("/instructors")
public class InstructorController {

	@Autowired
	private InstructorService instructorService;
	
	@Autowired
	private CourseService courseService;
	
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public String getInstructorForm(@ModelAttribute Instructor instructor, Model model){
		
		model.addAttribute("courses", courseService.getAll());
		
		return "instructors/create";
	}
	
	@RequestMapping(value ="/create", method = RequestMethod.POST)
	public String postInstructorForm(@ModelAttribute Instructor instructor, 
								     @RequestParam("courseIds") List<Long> courseIds, 
			                         @RequestParam("passwordAgain") String passwordAgain){
		
		passwordEquals(instructor, passwordAgain, courseIds);
		
		
		return "redirect:/instructors";
	}

	private void passwordEquals(Instructor instructor, String passwordAgain, List<Long> coursesIds) {
		
		if (passwordAgain.equals(instructor.getPassword())) {
			
			instructorService.create(instructor, coursesIds);
		}
	}
	
	@RequestMapping(value ="", method = RequestMethod.GET)
	public String getInstructors(Model model){
		
		model.addAttribute("instructors" , instructorService.getAll());
		 
		return "instructors/instructorList";
	}
		
	
	@RequestMapping(value="/update/{id}", method = RequestMethod.GET)
	public String getUpdate(@PathVariable("id") Long id, Model model) {
		
		Instructor instructor = instructorService.getById(id);
		model.addAttribute("courses", courseService.getAll());
		model.addAttribute(instructor);
		
		return "instructors/updateInstructor";
	}
	
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String postUpdate(@ModelAttribute Instructor instructor) {
		
		instructorService.update(instructor);
		
		return "redirect:/instructors";
	}
	
	
	
	
}
