package email.com.gmail.youssefagagg.CourseManagement_JPA_JSF.jpa.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import email.com.gmail.youssefagagg.CourseManagement_JPA_JSF.bean.Student;
import email.com.gmail.youssefagagg.CourseManagement_JPA_JSF.service_bean.EntityManagerFactoryBean;

public class StudentService {
	private EntityManagerFactory factory;

	public StudentService(EntityManagerFactoryBean factoryBean) {

		this.factory = factoryBean.getEntityManagerFactory();
	}
	public List<Student> getStudents() {
		EntityManager entityManager=factory.createEntityManager();
		CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
		CriteriaQuery<Student> criteriaQuery=criteriaBuilder.createQuery(Student.class);
		TypedQuery<Student>typedQuery=entityManager.createQuery(criteriaQuery);
		List<Student>list=typedQuery.getResultList();
		entityManager.close();
		return list;

	}
	public void addStudent (Student student) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		txn.begin();
		em.persist(student);
		txn.commit();
		em.close();
	}
	public void updateStudent (Student student) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		txn.begin();
		em.merge(student);
		txn.commit();
		em.close();
	}
	public Student getStudent (int id) {
		EntityManager em = factory.createEntityManager();
		Student student=em.find(Student.class, id);
		em.close();
				
		return student;
	}
	public void deleteStudent (int studentid) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		txn.begin();
		Student mergedStudent = em.find(Student.class,studentid);
		em.remove(mergedStudent);
		txn.commit();
		em.close();
	}
}
