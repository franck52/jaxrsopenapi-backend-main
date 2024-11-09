package fr.istic.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fr.istic.jpa.Ticket;
import fr.istic.jpa.User;

public class CommentaireDTO {
	//private User user;
	//private UserDTO userdto;
	private String texteCommentaire;
	//private Ticket ticket;
	private int ticket_id_ticket;
	private int userId;
	
	private int idCommentaire;
	
	public CommentaireDTO() {
		super();
	}
	
	public CommentaireDTO(int  idCommentaire,String texteCommentaire, int iduser,  int ticket_id_ticket) {
		super();
		
		this.texteCommentaire = texteCommentaire;
		this.ticket_id_ticket = ticket_id_ticket;
		this.userId = iduser;
		this.idCommentaire =  idCommentaire;
		//this.ticket = ticket;
		//this.user = user;
		//this.userdto = userdto;
		//this.ticket= ticket;
	}
	
	

	public int getIdCommentaire() {
		return idCommentaire;
	}

	public void setIdCommentaire(int idCommentaire) {
		this.idCommentaire = idCommentaire;
	}

	/*public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}*/
	public String getTexteCommentaire() {
		return texteCommentaire;
	}
	public void setTexteCommentaire(String texteCommentaire) {
		this.texteCommentaire = texteCommentaire;
	}
	/*public Ticket getTicket() {
		return ticket;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	public int getIdTicket() {
		return idTicket;
	}
	public void setIdTicket(int idTicket) {
		this.idTicket = idTicket;
	}*/
	public int getUserId() {
		return userId;
	}
	public void setUserId(int iduser) {
		this.userId = iduser;
	}

	/*public UserDTO getUserdto() {
		return userdto;
	}

	public void setUserdto(UserDTO userdto) {
		this.userdto = userdto;
	}*/

	public int getTicket_id_ticket() {
		return ticket_id_ticket;
	}

	public void setTicket_id_ticket(int ticket_id_ticket) {
		this.ticket_id_ticket = ticket_id_ticket;
	}
	
	
	
}
