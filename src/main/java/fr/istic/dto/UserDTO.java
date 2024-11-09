package fr.istic.dto;

import java.util.ArrayList;
import java.util.List;

import fr.istic.jpa.Commentaire;
import fr.istic.jpa.Ticket;

public class UserDTO {
	private String userName;
	private String userAddress;
	private String password;
	private int userId;
	private String email;
	
	private List<Integer> idTicket = new ArrayList<Integer>();
	private List<Integer> idCommentaire = new ArrayList<Integer>();
	
	public UserDTO() {
		
	}
	
	public UserDTO(int id,String userName, String userAddress,List<Integer> idTicket, List<Integer> idCommentaire) {
		super();
		this.userName = userName;
		this.userAddress = userAddress;
		this.idTicket = idTicket;
		this.idCommentaire = idCommentaire;
		this.userId= id;
	}
	public UserDTO(int id,String userName, String email, String adress, String mdp) {
		super();
		this.userName = userName;
		this.password = email;
		this.userId= id;
		this.userAddress=adress;
		this.password= mdp;
	}
	

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<Integer> getIdTicket() {
		return idTicket;
	}
	public void setIdTicket(List<Integer> idTicket) {
		this.idTicket = idTicket;
	}
	public List<Integer> getIdCommentaire() {
		return idCommentaire;
	}
	public void setIdCommentaire(List<Integer> idCommentaire) {
		this.idCommentaire = idCommentaire;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
