package email.com.gmail.youssefagagg.CourseManagement_JPA_JSF.jpa.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import email.com.gmail.youssefagagg.CourseManagement_JPA_JSF.bean.Course;
import email.com.gmail.youssefagagg.CourseManagement_JPA_JSF.service_bean.EntityManagerFactoryBean;


public class CourseService {
	private EntityManagerFactory factory;

	public CourseService(EntityManagerFactoryBean factoryBean) {

		this.factory = factoryBean.getEntityManagerFactory();
	}
	public List<Course> getCourses() {
		EntityManager entityManager=factory.createEntityManager();
		factory.getCache().evictAll();
		CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
		CriteriaQuery<Course> criteriaQuery=criteriaBuilder.createQuery(Course.class);
		TypedQuery<Course>typedQuery=entityManager.createQuery(criteriaQuery);
		List<Course>list=typedQuery.getResultList();
		entityManager.close();
		return list;

	}
	public void addCourse (Course course) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		txn.begin();
		em.persist(course);
		txn.commit();
		em.close();
	}
	public void updateCourse (Course course) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		txn.begin();
		em.merge(course);
		txn.commit();
		em.close();
	}
	public Course getCourse (int id) {
		EntityManager em = factory.createEntityManager();
		Course course=em.find(Course.class, id);
		em.close();
				
		return course;
	}
	public void deleteCourse (int courseid) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		txn.begin();
		Course mergedCourse = em.find(Course.class, courseid);
		em.remove(mergedCourse);
		txn.commit();
		em.close();
	}
}
