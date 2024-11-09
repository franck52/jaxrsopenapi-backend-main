package fr.istic.taa.jaxrs.dao.generic;

import fr.istic.jpa.Evolution;

public class EvolutionDAO extends AbstractJpaDao<Integer, Evolution>{
	public EvolutionDAO() {
		this.setClazz(Evolution.class);
		
	}

}
