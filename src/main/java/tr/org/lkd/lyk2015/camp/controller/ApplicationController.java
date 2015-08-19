package tr.org.lkd.lyk2015.camp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tr.org.lkd.lyk2015.camp.controller.validation.ApplicationFormValidator;
import tr.org.lkd.lyk2015.camp.model.dto.ApplicationFormDto;
import tr.org.lkd.lyk2015.camp.service.ApplicationService;
import tr.org.lkd.lyk2015.camp.service.CourseService;

@Controller
@RequestMapping("/applicationForm")
public class ApplicationController {

	@Autowired
	private CourseService courseService;

	@Autowired
	private ApplicationFormValidator applicationFormValidator;

	@Autowired
	private ApplicationService applicationService;

	@InitBinder
	protected void initBinder(final WebDataBinder binder) {

		binder.addValidators(this.applicationFormValidator);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getApplicationForm(@ModelAttribute("form") ApplicationFormDto applicationFormDto, Model model) {

		model.addAttribute("courses", this.courseService.getAll());

		return "applicationForm";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String postApplicationForm(@ModelAttribute("form") @Valid ApplicationFormDto applicationFormDto,
			BindingResult bindingResult, Model model) {

		// this.applicationService.fillApplication(applicationFormDto);

		if (bindingResult.hasErrors()) {

			model.addAttribute("courses", this.courseService.getAll());
			return "applicationForm";
		}

		else {
			this.applicationService.create(applicationFormDto);
			model.addAttribute("successMessage", "Basvuru Kaydınız Yapılmıştır");

		}
		return "applicationSuccess";
	}

	@RequestMapping(value = "/validate/{confirmationCode}", method = RequestMethod.GET)
	public String getValidate(@PathVariable("confirmationCode") String confirmationCode, Model model) {

		model.addAttribute("successMessage", "Basvuru Kaydınız Yapılmıştır");

		return "confirmation";
	}

}
