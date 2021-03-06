package tr.org.lkd.lyk2015.camp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


/*
*cengizhan - Aug 16, 2015
*/

@Entity
public class Course extends AbstractBaseModel{
 
	private String name;
	private String description;
	private String prerequisites;
	private String detailPageLink;
	private Boolean active = true;
	

	@ManyToMany(mappedBy = "courses") // iki fiel aynı oamaz
	private Set<Instructor> instructors = new HashSet<>();

	
	
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrerequisites() {
		return prerequisites;
	}

	public void setPrerequisites(String prerequisites) {
		this.prerequisites = prerequisites;
	}

	public String getDetailPageLink() {
		return detailPageLink;
	}

	public void setDetailPageLink(String detailPageLink) {
		this.detailPageLink = detailPageLink;
	}

	public Set<Instructor> getInstructors() {
		return instructors;
	}

	public void setInstructors(Set<Instructor> instructors) {
		this.instructors = instructors;
	} 
	
}
