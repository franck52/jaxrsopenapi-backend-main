package fr.istic.taa.jaxrs.dao.generic;

import fr.istic.jpa.Commentaire;

public class CommentaireDOA extends AbstractJpaDao<Integer, Commentaire> {
	public CommentaireDOA() {
		this.setClazz(Commentaire.class );
	}
	

}
