package fr.istic.taa.jaxrs.dao.generic;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.istic.jpa.Ticket;
import fr.istic.jpa.User;

public abstract class AbstractJpaDao<K, T extends Serializable> implements IGenericDao<K, T> {

	private Class<T> clazz;

	protected EntityManager entityManager;

	public AbstractJpaDao() {
		this.entityManager = EntityManagerHelper.getEntityManager();
	}

	public void setClazz(Class<T> clazzToSet) {
		this.clazz = clazzToSet;
	}

	public T findOne(K id) {
		return entityManager.find(clazz, id);
	}

	public List<T> findAll() {
		return entityManager.createQuery("select e from " + clazz.getName() + " as e",clazz).getResultList();
	}

	public void save(T entity) {
		EntityTransaction t = this.entityManager.getTransaction();
		t.begin();
		entityManager.persist(entity);
		t.commit();

	}

	public T update(final T entity) {
		EntityTransaction t = this.entityManager.getTransaction();
		t.begin();
		T res = entityManager.merge(entity);
		t.commit();
		return res;

	}
	public void update(T entity, final T entity1) {
		entity = entity1;
		this.update(entity);
	}

	public void delete(T entity) {
		EntityTransaction t = this.entityManager.getTransaction();
		t.begin();
		entityManager.remove(entity);
		t.commit();

	}

	public void deleteById(K entityId) {
		T entity = findOne(entityId);
		delete(entity);
	}
	public User findUserByUsernameAndPassword(String username, String password) {
	    TypedQuery<User> query = entityManager.createQuery(
	        "SELECT u FROM User u WHERE u.userName = :username AND u.password = :password", User.class);
	    query.setParameter("username", username);
	    query.setParameter("password", password);
	    List<User> users = query.getResultList();
	    if (!users.isEmpty()) {
	        return users.get(0);
	    }
	    return null;
	}
	
	public User findUserByEmail(String email) {
	    TypedQuery<User> query = entityManager.createQuery(
	        "SELECT u FROM User u WHERE u.email = :email", User.class);
	    query.setParameter("email", email);
	    try {
	        return query.getSingleResult();
	    } catch (NoResultException e) {
	        return null;
	    }
	}
	
	public List<Ticket> findAllTicketsByUser(int userId) {
	    TypedQuery<Ticket> query = entityManager.createQuery(
	            "SELECT t FROM Ticket t WHERE t.user.id = :userId", Ticket.class);
	    query.setParameter("userId", userId);
	    return query.getResultList();
	}
	
	/*public Long countCommentsByTicket(int id) {
	    TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(c) FROM Comment c WHERE c.ticket.id = :ticketId", Long.class);
	    query.setParameter("ticketId", id);
	    return query.getSingleResult();
	}*/
	public Long countCommentsByTicket(int idTicket) {
	   // EntityManager em = entityManagerFactory.createEntityManager();
	    try {
	        TypedQuery<Long> query = entityManager.createQuery(
	            "SELECT COUNT(c) FROM Commentaire c WHERE c.ticket.id_ticket = :idTicket",
	            Long.class);
	        query.setParameter("idTicket", idTicket);
	        return query.getSingleResult();
	    } finally {
	        //entityManager.close();
	    }
	}
	
	public int updateUserPassword(int userId, String newPassword) {
	  
	    // Utiliser une requête JPQL pour mettre à jour le mot de passe de l'utilisateur
	    entityManager.getTransaction().begin();
	    Query query = entityManager.createQuery("UPDATE User u SET u.password = :password WHERE u.id = :id");
	    query.setParameter("password", newPassword);
	    query.setParameter("id", userId);
	    int numUpdated = query.executeUpdate();
	    entityManager.getTransaction().commit();

	    //entityManager.close();

	    return numUpdated;
	}






	
	
	
}
