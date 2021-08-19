package email.com.gmail.youssefagagg.CourseManagement_JPA_JSF.jpa.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import email.com.gmail.youssefagagg.CourseManagement_JPA_JSF.bean.Teacher;
import email.com.gmail.youssefagagg.CourseManagement_JPA_JSF.service_bean.EntityManagerFactoryBean;

public class TeacherService {
	private EntityManagerFactory factory;

	public TeacherService(EntityManagerFactoryBean factoryBean) {

		this.factory = factoryBean.getEntityManagerFactory();
	}
	public List<Teacher> getTeachers() {
		EntityManager entityManager=factory.createEntityManager();
		CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
		CriteriaQuery<Teacher> criteriaQuery=criteriaBuilder.createQuery(Teacher.class);
		TypedQuery<Teacher>typedQuery=entityManager.createQuery(criteriaQuery);
		List<Teacher>list=typedQuery.getResultList();
		entityManager.close();
		return list;

	}
	public void addTeacher (Teacher teacher) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		txn.begin();
		em.persist(teacher);
		txn.commit();
		em.close();
	}
	public void updateTeacher (Teacher teacher) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		txn.begin();
		em.merge(teacher);
		txn.commit();
		em.close();
	}
	public Teacher getTeacher (int id) {
		EntityManager em = factory.createEntityManager();
		Teacher teacher=em.find(Teacher.class, id);
		em.close();
				
		return teacher;
	}
	public void deleteTeacher (int teacherid) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		txn.begin();
		Teacher mergedTeacher = em.find(Teacher.class,teacherid);
		em.remove(mergedTeacher);
		txn.commit();
		em.close();
	}
}
