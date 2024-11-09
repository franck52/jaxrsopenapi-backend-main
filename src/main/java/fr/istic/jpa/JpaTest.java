package fr.istic.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import fr.istic.taa.jaxrs.dao.generic.EntityManagerHelper;



public class JpaTest {
	private EntityManager manager;

 
	/**
	 * @param args
	 */
	public JpaTest(EntityManager manager) {
		super();
		this.manager = manager;
	}
	public static void main(String[] args) {

		EntityManager manager = EntityManagerHelper.getEntityManager();
		JpaTest test = new JpaTest(manager);
		EntityTransaction tx = manager.getTransaction();
		tx.begin();


		try {
			//test.creatUsers("koudou", "Fabrice");
			//test.creatUsers();

		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();


		manager.close();
		EntityManagerHelper.closeEntityManagerFactory();
		//		factory.close();
		
		
	}
	
	/*private void creatUsers() {
		int numOfEmployees = manager.createQuery("Select a From User a", User.class).getResultList().size();
		if (numOfEmployees < 10) {
			
			//manager.persist(new User(nom,prenom));
			manager.persist(new User("Captain Nemo","35000"));
			manager.persist(new User("Tata","35511"));
			manager.persist(new User("Toto","35700"));
			manager.persist(new User("Froto","35520"));
			manager.persist(new User("Diaz","35020"));

		}
	}
	

	private void listUsers() {
		List<User> resultList = manager.createQuery("Select a From User a", User.class).getResultList();
		System.out.println("num of USER:" + resultList.size());
		for (User next : resultList) {
			System.out.println("next user: " + next);
		}
}
*/

}
