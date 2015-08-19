package tr.org.lkd.lyk2015.camp.dal;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import tr.org.lkd.lyk2015.camp.model.Instructor;

@Repository
public class InstructorDao extends GenericDao<Instructor> {

	
	public Instructor getByIdWithCourses(Long id) {
		
		Criteria criteria = createCriteria();
		
		criteria.add(Restrictions.idEq(id));
		criteria.setFetchMode("courses", FetchMode.JOIN);
		
		return (Instructor) criteria.uniqueResult();
	}
	
}
