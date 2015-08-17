package tr.org.lkd.lyk2015.camp.service;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tr.org.lkd.lyk2015.camp.dal.CourseDao;
import tr.org.lkd.lyk2015.camp.model.Course;


@Transactional
@Service
public class CourseService extends GenericService<Course> {

    @Autowired
    private CourseDao courseDao;

//	@Override
//	public List<Course> getAll() {
//		
//		return courseDao.getAll();
//	}
    
    
}