package fr.istic.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table( name = "Ticket" )
@Inheritance( strategy = InheritanceType.SINGLE_TABLE )
@DiscriminatorColumn( name="discriminator", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue("0")      // Si la classe Payment n'était pas abstraite
@XmlRootElement(name ="Ticket")
public class Ticket implements Serializable {
	private List<Tag>tag = new ArrayList<Tag>();
	@JsonIgnore
	
	private User user;
	private int id_ticket;
	private String label;
    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Commentaire> ListCommentaire =  new ArrayList<Commentaire>();
	private int descriminator;
	private String bug_Description;
	private int priority;
	private String title;
	
	public Ticket() {
		super();
	}
	
	public Ticket(List<Tag> tag, User user, int id_ticket, String label, List<Commentaire> listCommentaire,
			int descriminator, String bug_Description, int priority, String title) {
		super();
		this.tag = tag;
		this.user = user;
		this.id_ticket = id_ticket;
		this.label = label;
		ListCommentaire = listCommentaire;
		this.descriminator = descriminator;
		this.bug_Description = bug_Description;
		this.priority = priority;
		this.title= title;
		
	}

	public Ticket(int id_ticket) {
		super();
		this.id_ticket = id_ticket;
		
		
	}
	public Ticket(int id_ticket, String label,User user) {
		super();
		this.id_ticket = id_ticket;
		this.label = label;
		this.user = user;
		
	}
	
	public Ticket( int id_ticket, String label,User user, List<Commentaire> listCommentaire, int priority) {
		super();
		this.user = user;
		this.id_ticket = id_ticket;
		this.label = label;
		ListCommentaire = listCommentaire;
		this.priority =priority;
		//this.discriminator = disc;
	}
	@JsonIgnore
	//@OneToMany(mappedBy = "ticket", cascade = CascadeType.PERSIST)
    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<Commentaire> getListCommentaire() {
		return ListCommentaire;
	}
	public void setListCommentaire(List<Commentaire> listCommentaire) {
		ListCommentaire = listCommentaire;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	public int getId_ticket() {
		return id_ticket;
	}
	public void setId_ticket(int id_ticket) {
		this.id_ticket = id_ticket;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	@JsonIgnore
	//@OneToMany(mappedBy = "ticket", cascade = CascadeType.PERSIST)
	@OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<Tag> getTag() {
		return tag;
	}
	public void setTag(List<Tag> tag) {
		this.tag = tag;
	}
	@ManyToOne
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getDescriminator() {
		return descriminator;
	}
	public void setDescriminator(int discriminator) {
		this.descriminator = descriminator;
	}
	public String getBug_Description() {
		return bug_Description;
	}
	public void setBug_Description(String bug_Description) {
		this.bug_Description = bug_Description;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	

		

}
