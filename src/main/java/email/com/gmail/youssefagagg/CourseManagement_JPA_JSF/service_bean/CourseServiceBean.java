package email.com.gmail.youssefagagg.CourseManagement_JPA_JSF.service_bean;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import email.com.gmail.youssefagagg.CourseManagement_JPA_JSF.bean.Course;
import email.com.gmail.youssefagagg.CourseManagement_JPA_JSF.jpa.service.CourseService;




@ManagedBean(name="courseServiceBean")
@RequestScoped
public class CourseServiceBean {
	List<Course>courses;
	@ManagedProperty(value = "#{course}")
	private Course course;
	private int teacherid;
	private CourseService courseService ;
	@ManagedProperty(value="#{emFactoryBean}")
	private EntityManagerFactoryBean factoryBean;
	@ManagedProperty(value = "#{teacherServiceBean}")
	private TeacherServiceBean  teacherServiceBean;
	@PostConstruct
	public void init() {
		courseService  = new CourseService(factoryBean);
	}
	public String addCourse() {
		
		if(teacherid!=0)
		course.setTeacher(teacherServiceBean.getTeacherByID(teacherid));
		
		courseService.addCourse(course);
		return  "listCourse?faces-redirect=true";
	}
	public  List<Course> getCourses() {
		if(courses==null) {
		courses=courseService.getCourses();

		}
		return courses;
		}
	public String updateButtonFromListCoursePage(int courseid) {
		course=courseService.getCourse(courseid);
		if(course.getTeacher()!=null)
		teacherid=course.getTeacher().getId();
		return "updateCourse?id="+courseid+"&faces-redirect=true";
		
	}
	public void getCourseById(String courseid) {
		if(!courseid.trim().isEmpty()) {
		Course s=courseService.getCourse(Integer.parseInt(courseid.trim()));
		System.out.println(s);
		course.setId(s.getId());
		course.setName(s.getName());
		course.setCredits(s.getCredits());
		course.setTeacher(s.getTeacher());
		}
		
	}
	
public String deleteButtonFromListCoursePage(int courseid) {
		courseService.deleteCourse(courseid);
		return "listCourse?faces-redirect=true";
	}
	public String updateCourse() {
		if(teacherid!=0)
			course.setTeacher(teacherServiceBean.getTeacherByID(teacherid));
			
		courseService.updateCourse(course);
		return "listCourse?faces-redirect=true";
	}
	
	public void setCourse(Course course) {
		this.course = course;
	}
	
	public void setTeacherid(int teacherid) {
		this.teacherid = teacherid;
	}
	
	
	public int getTeacherid() {
		return teacherid;
	}
	public CourseService getCourseService() {
		return courseService;
	}
	public void setFactoryBean(EntityManagerFactoryBean factoryBean) {
		this.factoryBean = factoryBean;
	}
	
	public void setTeacherServiceBean(TeacherServiceBean teacherServiceBean) {
		this.teacherServiceBean = teacherServiceBean;
	}
	



}
