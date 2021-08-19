package email.com.gmail.youssefagagg.CourseManagement_JPA_JSF.service_bean;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import email.com.gmail.youssefagagg.CourseManagement_JPA_JSF.bean.Course;
import email.com.gmail.youssefagagg.CourseManagement_JPA_JSF.bean.Student;
import email.com.gmail.youssefagagg.CourseManagement_JPA_JSF.jpa.service.CourseService;
import email.com.gmail.youssefagagg.CourseManagement_JPA_JSF.jpa.service.StudentService;

@ManagedBean(name = "studentServiceBean")
@RequestScoped
public class StudentServiceBean {
	private List<String>coursesids;
	private List<Student>students;
	@ManagedProperty(value = "#{student}")
	private Student student;
	private StudentService studentService  ;
	@ManagedProperty(value="#{emFactoryBean}")
	private EntityManagerFactoryBean factoryBean;
	private CourseService courseService;
	@PostConstruct
	public void init() {
		studentService = new StudentService(factoryBean);
		courseService=new CourseService(factoryBean);
	}
	
	public String addStudent() {
		addCoursesToStudent();
		student.setEnrolledsince(new Date());
		studentService.addStudent(student);
		
		return "listStudent?faces-redirect=true";
	}

	private void addCoursesToStudent() {
		if(coursesids!=null&&!coursesids.isEmpty()) {
			for(String id:coursesids) {
				Course course=courseService.getCourse(Integer.parseInt(id));
				student.getCourses().add(course);
				
			}
		}
	}
	public void getStudentById(String studentid) {
		if(!studentid.trim().isEmpty()) {
		Student s=studentService.getStudent(Integer.parseInt(studentid.trim()));
		System.out.println(s);
		student.setId(s.getId());
		student.setFirstName(s.getFirstName());
		student.setLastName(s.getLastName());
		student.setEnrolledsince(s.getEnrolledsince());
		}
		
	}
	
	
	public List<Student> getStudents() {
		if(students==null) {
			students=studentService.getStudents();
		}
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public String updateButtonFromListStudentPage(int studentid) {
		
		return "updateStudent?id=+"+studentid+"&faces-redirect=true";
	}
	public String deleteButtonFromListStudentPage(int studentid) {
		studentService.deleteStudent(studentid);
		return "listStudent?faces-redirect=true";
	}
	public String updateStudent() {
		Student s=studentService.getStudent(student.getId());
		student.setEnrolledsince(s.getEnrolledsince());
		addCoursesToStudent();
		System.out.println(student+" from up");
		
		studentService.updateStudent(student);
		return "listStudent?faces-redirect=true";
	}
	public List<String> getCoursesids() {
		return coursesids;
	}
	public void setCoursesids(List<String> coursesids) {
		this.coursesids = coursesids;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	

	public void setFactoryBean(EntityManagerFactoryBean factoryBean) {
		this.factoryBean = factoryBean;
	}
	

}
