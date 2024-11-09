package fr.istic.taa.jaxrs.dao.generic;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import fr.istic.jpa.User;
/*
  @NamedQuery(
     name="T_User.findAll",
     query=select p from  T_User p)
  */

public class UserDAO extends AbstractJpaDao<Integer, User> {
	private EntityManager manager;
	public UserDAO() {
		this.setClazz(User.class);
		this.manager = EntityManagerHelper.getEntityManager();
	}
	public List<User> getAlltUsers() {
		List<User> resultList = manager.createQuery("Select a From User a", User.class).getResultList();
		System.out.println("num of USER:" + resultList.size());
		for (User next : resultList) {
			System.out.println("next user: " + next);
		}
		return resultList;
}

}
