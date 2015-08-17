package tr.org.lkd.lyk2015.camp.dal;

import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import tr.org.lkd.lyk2015.camp.model.Course;

@Repository
public class CourseDao extends GenericDao<Course> {

    @Override
    public List<Course> getAll() {

        final Session session = sessionFactory.getCurrentSession();
        final Criteria criteria = session.createCriteria(Course.class, "course");
        criteria.add(Restrictions.eq("course.active", true));
        criteria.add(Restrictions.eq("course.deleted", false));
        
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.setFetchMode("*", FetchMode.JOIN);

        return criteria.list();
    }
    
    @SuppressWarnings("unchecked")
	public List<Course> getByIds(List<Long> ids) {
		// TODO Auto-generated method stub
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.in("id",ids));
		
		return criteria.list();
	}
}
