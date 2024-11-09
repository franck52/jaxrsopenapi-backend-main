package fr.istic.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@XmlRootElement(name ="User")
public class User implements Serializable {
	private int userId;
	private String userName;
	private String userAddress;
	private String Password;
	private String email;
	private String lastname;
	
	@JsonIgnore
	private List<Ticket> ticket = new ArrayList<Ticket>();
	 @JsonManagedReference
	private List<Commentaire> ListCommentaire = new ArrayList<Commentaire>();
	
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(int id) {
		super();
		// TODO Auto-generated constructor stub
		this.userId =id;
	}
	public User(String userName, String userAddress) {
		super();
		this.userName = userName;
		this.userAddress = userAddress;
		
	}
	public User(String userName, String userAddress, List<Ticket> ticket, List<Commentaire> ListCommentaire, String email, String lastname ) {
		super();
		this.userName = userName;
		this.userAddress = userAddress;
		this.ticket = ticket;
		this.ListCommentaire = ListCommentaire;
		this.lastname = lastname;
		this.email = email;
	}
	
	

	public String getUserName() {
		return userName;
	}
	@OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
	public List<Commentaire> getListCommentaire() {
		return ListCommentaire;
	}
	public void setListCommentaire(List<Commentaire> listCommentaire) {
		ListCommentaire = listCommentaire;
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
	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
	public List<Ticket> getTicket() {
		return ticket;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public void setTicket(List<Ticket> ticket) {
		this.ticket = ticket;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	

}
