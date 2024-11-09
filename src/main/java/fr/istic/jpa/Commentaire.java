package fr.istic.jpa;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@XmlRootElement(name="Commentaire")
public class Commentaire implements Serializable {
	//@JsonBackReference
	@JsonBackReference("defaultReference")
	private User user;
	private int idCommentaire;
	private String texteCommentaire;
	private Ticket ticket;
	
	
	public Commentaire(User user, int idCommentaire, String texteCommentaire,Ticket ticket) {
		super();
		this.user = user;
		this.idCommentaire = idCommentaire;
		this.texteCommentaire = texteCommentaire;
		this.ticket = ticket;
		
	}
	public Commentaire() {
		super();
		
	}
	@ManyToOne
	public Ticket getTicket() {
		return ticket;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	
	@ManyToOne
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	public int getIdCommentaire() {
		return idCommentaire;
	}
	public void setIdCommentaire(int idCommentaire) {
		this.idCommentaire = idCommentaire;
	}
	public String getTexteCommentaire() {
		return texteCommentaire;
	}
	public void setTexteCommentaire(String texteCommentaire) {
		this.texteCommentaire = texteCommentaire;
	}

	

}
