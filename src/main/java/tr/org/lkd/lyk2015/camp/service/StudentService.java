package tr.org.lkd.lyk2015.camp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.org.lkd.lyk2015.camp.dal.StudentDao;
import tr.org.lkd.lyk2015.camp.model.Student;

@Service
@Transactional
public class StudentService extends GenericService<Student> {

	@Autowired
	private StudentDao studentDao;

}
