package email.com.gmail.youssefagagg.CourseManagement_JPA_JSF.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.REFRESH;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;

@ManagedBean(name="student")
@RequestScoped
@Entity
@Table(name = "Student")
public class Student extends Person {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Temporal(TemporalType.DATE)
	@Column(name="enrolled_since")
	private Date enrolledsince;
	@ManyToMany(cascade = { MERGE, REFRESH } , fetch = FetchType.EAGER)
	@JoinTable(name = "Course_Student",joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"))
	
	private List<Course>courses=new ArrayList<Course>();
	public Date getEnrolledsince() {
		return enrolledsince;
	}
	public void setEnrolledsince(Date enrolledsince) {
		this.enrolledsince = enrolledsince;
	}
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	@Override
	public String toString() {
		return "Student [enrolledsince=" + enrolledsince + ", courses=" + courses +", id=" + getId() +", name=" + getFirstName()+getLastName() + "]";
	}
	

}
