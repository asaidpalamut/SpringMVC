package tr.org.lkd.lyk2015.camp.dal;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tr.org.lkd.lyk2015.camp.model.Application;

@Repository
public class ApplicationDao extends GenericDao<Application> {

	@Autowired
	private SessionFactory sessionFactory;

	public Application validation(String confirmationCode) {

		Criteria criteria = this.createCriteria();
		criteria.add(Restrictions.eq("validationId", confirmationCode));

		return (Application) criteria.uniqueResult();
	}

}
