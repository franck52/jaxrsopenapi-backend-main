package fr.istic.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.istic.jpa.Commentaire;
import fr.istic.jpa.Tag;


public class TicketDTO {
	private List<Tag>tag = new ArrayList<Tag>();
	private UserDTO userdto;
	private String label;
	private int userId;
	private int idCommentaire;
	private int idTag;
	private String bug_Description;
	private int id_ticket;
	private int descriminator;
	@JsonProperty("priority")
	private int priority;
	private int discriminator;
	private String title;
	
	public TicketDTO() {
		
	}
	public TicketDTO(int id_ticket,String label, int userId, int idCommentaire, int idTag, int priority, String dbug, int discriminator, String title) {
		super();
		this.id_ticket = id_ticket;
		this.label = label;
		this.userId = userId;
		this.idCommentaire = idCommentaire;
		this.idTag = idTag;
		this.priority = priority;
		this.discriminator = discriminator;
		this.title = title;
		this.bug_Description = dbug;
	}
	public TicketDTO(List<Tag> tag, UserDTO userdto, String label, int userId, int idCommentaire, int idTag,
			List<Commentaire> listCommentaire) {
		super();
		this.tag = tag;
		this.userdto = userdto;
		this.label = label;
		this.userId = userId;
		this.idCommentaire = idCommentaire;
		this.idTag = idTag;
		ListCommentaire = listCommentaire;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getIdCommentaire() {
		return idCommentaire;
	}
	public void setIdCommentaire(int idCommentaire) {
		this.idCommentaire = idCommentaire;
	}
	public int getIdTag() {
		return idTag;
	}
	public void setIdTag(int idTag) {
		this.idTag = idTag;
	}
	private List<Commentaire> ListCommentaire =  new ArrayList<Commentaire>();
	public List<Tag> getTag() {
		return tag;
	}
	public void setTag(List<Tag> tag) {
		this.tag = tag;
	}
	public UserDTO getUserdto() {
		return userdto;
	}
	public void setUserdto(UserDTO user) {
		this.userdto = user;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public List<Commentaire> getListCommentaire() {
		return ListCommentaire;
	}
	public void setListCommentaire(List<Commentaire> listCommentaire) {
		ListCommentaire = listCommentaire;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getBug_Description() {
		return bug_Description;
	}
	public void setBug_Description(String bug_Description) {
		this.bug_Description = bug_Description;
	}
	public int getDescriminator() {
		return descriminator;
	}
	public void setDescriminator(int descriminator) {
		this.descriminator = descriminator;
	}
	public int getId_ticket() {
		return id_ticket;
	}
	public void setId_ticket(int id_ticket) {
		this.id_ticket = id_ticket;
	}
	public int getDiscriminator() {
		return discriminator;
	}
	public void setDiscriminator(int discriminator) {
		this.discriminator = discriminator;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
	
	
	
	

}
