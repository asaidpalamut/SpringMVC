package tr.org.lkd.lyk2015.camp.service;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tr.org.lkd.lyk2015.camp.dal.ApplicationDao;
import tr.org.lkd.lyk2015.camp.dal.CourseDao;
import tr.org.lkd.lyk2015.camp.dal.StudentDao;
import tr.org.lkd.lyk2015.camp.model.Application;
import tr.org.lkd.lyk2015.camp.model.Course;
import tr.org.lkd.lyk2015.camp.model.Student;
import tr.org.lkd.lyk2015.camp.model.dto.ApplicationFormDto;

@Service
@Transactional
public class ApplicationService extends GenericService<Application> {

	@Autowired
	private CourseDao courseDao;

	@Autowired
	private StudentService studentService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private StudentDao studentDao;

	@Autowired
	private ApplicationDao applicationDao;

	@Autowired
	private MailService mailService;

	public String generateUUID() {

		UUID uuid = UUID.randomUUID();

		return uuid.toString();
	}

	public String createConfirmationUrl() {

		String url = "http://localhost:8080/camp/applicationForm/validate/" + this.generateUUID();

		return url;
	}

	public void create(ApplicationFormDto applicationFormDto) {

		Application application = applicationFormDto.getApplication();
		Student student = applicationFormDto.getStudent();
		List<Long> coursesIds = applicationFormDto.getPreferredCourseIds();

		// Generate email verification url
		String uuid = UUID.randomUUID().toString();
		String url = "http://localhost:8080/camp/applicationForm/validate/" + uuid;

		// TODO send mail
		this.mailService.sendEmail(student.getEmail(), "Basvuru Onayı", url);

		application.setValidationId(uuid);

		// Add preferered courses to application entity
		List<Course> courses = this.courseDao.getByIds(coursesIds);
		application.getPreferredCourses().clear();
		application.getPreferredCourses().addAll(courses);

		// Check if user exists
		Student studentFromDb = this.studentDao.getUserByTckn(student.getTckn());

		if (studentFromDb == null) {

			this.studentDao.create(student);
			studentFromDb = student;
		}

		// Set application user
		application.setOwner(studentFromDb); // db den gelmesi gerektigi icin
												// parametre olarak
												// studentFromDb yi verdik

		this.applicationDao.create(application);
	}
}
