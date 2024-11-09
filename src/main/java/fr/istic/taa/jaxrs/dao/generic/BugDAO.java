package fr.istic.taa.jaxrs.dao.generic;

import fr.istic.jpa.Bug;

public class BugDAO extends AbstractJpaDao<Integer, Bug> {
	public BugDAO() {
		this.setClazz(Bug.class);
	}

}
