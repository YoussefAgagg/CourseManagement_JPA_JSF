package email.com.gmail.youssefagagg.CourseManagement_JPA_JSF.service_bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import email.com.gmail.youssefagagg.CourseManagement_JPA_JSF.bean.Teacher;
import email.com.gmail.youssefagagg.CourseManagement_JPA_JSF.jpa.service.TeacherService;


@ManagedBean(name="teacherServiceBean")
@RequestScoped

public class TeacherServiceBean {
	List<Teacher>teachers;
	@ManagedProperty(value = "#{teacher}")
	private Teacher teacher;
	private TeacherService teacherService ;
	@ManagedProperty(value="#{emFactoryBean}")
	private EntityManagerFactoryBean factoryBean;
	@PostConstruct
	public void init() {
		teacherService = new TeacherService(factoryBean);
	}
	public String updateButtonFromListTeacherPage(int teacherid) {
		
		
		return "updateTeacher?id="+teacherid+"faces-redirect=true";
	}
	public void getTeacherById(String teacherid) {
		if(!teacherid.trim().isEmpty()) {
		Teacher t=teacherService.getTeacher(Integer.parseInt(teacherid));
		teacher.setId(t.getId());
		teacher.setFirstName(t.getFirstName());
		teacher.setLastName(t.getLastName());
		teacher.setDesignation(t.getDesignation());
		}
		
	}
	
	public String deleteButtonFromListTeacherPage(int teacherid) {
		teacherService.deleteTeacher(teacherid);
		return "listTeacher?faces-redirect=true";
	}
	public String updateTeacher() {
		
		teacherService.updateTeacher(teacher);
		System.out.println(teacher.getFirstName()+" "+teacher.getId());
		return "listTeacher?faces-redirect=true";
		
	}

	public String addTeacher() {
		
		teacherService.addTeacher(teacher);

		return "listTeacher?faces-redirect=true";
	}
	public List<Teacher> getTeachers() {
		if(teachers==null) {
			teachers=teacherService.getTeachers();
		}
		return teachers;
		}
	public Teacher getTeacherByID(int id) {
		return teacherService.getTeacher(id);
	}
	
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	public void setFactoryBean(EntityManagerFactoryBean factoryBean) {
		this.factoryBean = factoryBean;
	}
	


}
