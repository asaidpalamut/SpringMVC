package tr.org.lkd.lyk2015.camp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tr.org.lkd.lyk2015.camp.model.Course;
import tr.org.lkd.lyk2015.camp.service.CourseService;
import tr.org.lkd.lyk2015.camp.service.InstructorService;

@Controller
@RequestMapping("/courses")
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public String getCourseForm(@ModelAttribute Course course) {
		
		
		return "courses/courseCreateForm";
	}
	
	
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public String postCourseForm(@ModelAttribute Course course){
		
		courseService.create(course);
		
		return "redirect:/courses/";
	}

	
	@RequestMapping(value ="", method = RequestMethod.GET)
	public String getCourses(Model model){
		
		model.addAttribute("courses" , courseService.getAll());
		 
		return "courses/courseList";
	}
	
	
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String getUpdate(@PathVariable("id") Long id, Model model) {
		
		Course course = courseService.getById(id);
		model.addAttribute(course);
		
		return "courses/updateCourse";
	}
	
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String postUpdate(@ModelAttribute Course course){
		
		courseService.update(course);
		
		return "redirect:/courses";				
	}
	
}
