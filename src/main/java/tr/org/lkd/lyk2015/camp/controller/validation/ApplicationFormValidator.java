package tr.org.lkd.lyk2015.camp.controller.validation;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import tr.org.lkd.lyk2015.camp.model.Application.WorkStatus;
import tr.org.lkd.lyk2015.camp.model.Student;
import tr.org.lkd.lyk2015.camp.model.dto.ApplicationFormDto;
import tr.org.lkd.lyk2015.camp.service.BlackListService;
import tr.org.lkd.lyk2015.camp.service.ExamService;
import tr.org.lkd.lyk2015.camp.service.MailService;
import tr.org.lkd.lyk2015.camp.service.TcknValidationService;

@Component
public class ApplicationFormValidator implements Validator {

	@Autowired
	private TcknValidationService tcknValidationService;

	@Autowired
	private BlackListService blackListService;

	@Autowired
	private ExamService examService;

	@Autowired
	private MailService mailService;

	@Override
	public boolean supports(Class<?> clazz) {

		return clazz.equals(ApplicationFormDto.class);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ApplicationFormDto applicationFormDto = (ApplicationFormDto) target;

		// Prevent inconsistent working status
		if (applicationFormDto.getApplication().getWorkStatus() == WorkStatus.NOT_WORKING
				&& applicationFormDto.getApplication().getOfficer()) {

			errors.rejectValue("workStatus", "error.notWorkingOfficer", "Hem calismayıp nasıl memuru secebiliyorsun");
		}

		// Check course selection size
		applicationFormDto.getPreferredCourseIds().removeAll(Collections.singleton(null)); // ???????

		if (applicationFormDto.getPreferredCourseIds().size() == 0) {

			errors.rejectValue("preferredCourseIds", "error.preferredCourseNoSelection", "En az bir kurs secmelisiniz");
		}

		// Prevent same course selection
		int listSize = applicationFormDto.getPreferredCourseIds().size();
		Set<Long> set = new HashSet<>(applicationFormDto.getPreferredCourseIds());
		int setSize = set.size();

		if (listSize != setSize) {

			errors.rejectValue("preferredCourseIds", "error.preferredCourseIds", "Aynı kursu iki kez sectiniz!");
		}

		// Validate Tckn from web service
		Student student = applicationFormDto.getStudent();
		boolean tcknValid = this.tcknValidationService.validate(student.getName(), student.getSurname(),
				student.getBirthDate(), student.getTckn());

		if (!tcknValid) {
			errors.rejectValue("student.tckn", "error.tcknInvalid", "TC Kimlik No hatali"); // ?????????????????

		}

		// Validate Exam
		boolean blackListValid = this.blackListService.isBlackList(student.getTckn(), student.getEmail(),
				student.getName(), student.getSurname());
		if (!blackListValid) {

			errors.rejectValue("student.name", "error.nameInvalid", "Kara listedesin dostum!!!");
		}

		// .....

		boolean examValid = this.examService.isExamSuccess(student.getTckn(), student.getEmail());

		if (!examValid) {

			errors.rejectValue("student.email", "error.examNotSuccess", "Sınavı gecemediniz ..");
		}

		// ..
		boolean mailVaild = this.mailService.sendEmail(student.getEmail(), "KONU", "dogrulama linki");

		if (!mailVaild) {

			errors.rejectValue("student.email", "error.mailInvalid", "Dogrulama linkini tıklayın!!");
		}

	}

}
