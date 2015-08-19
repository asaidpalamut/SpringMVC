package tr.org.lkd.lyk2015.camp.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;


/*
*cengizhan - Aug 16, 2015
*/

@Entity
public class Student extends AbstractUser {

	public enum Sex {
		MALE, FEMALE
	}

	@Enumerated(EnumType.STRING)
	private Sex sex;
 
	@OneToMany(mappedBy = "owner")
	private Set<Application> application;
	
	public Set<Application> getApplication() {
		return application;
	}

	public void setApplication(Set<Application> application) {
		this.application = application;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}
	
	
	
}
