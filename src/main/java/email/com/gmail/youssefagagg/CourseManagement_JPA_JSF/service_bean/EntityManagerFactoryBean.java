package email.com.gmail.youssefagagg.CourseManagement_JPA_JSF.service_bean;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ManagedBean(name = "emFactoryBean",eager = true )
@ApplicationScoped
public class EntityManagerFactoryBean {
	
	private EntityManagerFactory entityManagerFactory;
	public EntityManagerFactoryBean() {
		entityManagerFactory =
		Persistence.createEntityManagerFactory("CourseManagementJPA");
		}
		public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
		}

}
