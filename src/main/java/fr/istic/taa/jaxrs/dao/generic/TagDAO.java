package fr.istic.taa.jaxrs.dao.generic;

import fr.istic.jpa.Tag;

public class TagDAO extends AbstractJpaDao<Integer, Tag> {
	public TagDAO() {
		this.setClazz(Tag.class);
	}

}
