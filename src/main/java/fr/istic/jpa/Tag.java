package fr.istic.jpa;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Tag implements Serializable {
private int idTag;
private String tagLab;
private Ticket ticket;
private int idticket;

public Tag(String tagLab, Ticket ticket) {
	super();
	this.tagLab = tagLab;
	this.ticket = ticket;
}
public Tag() {
	super();
	
}


public int getIdticket() {
	return idticket;
}
public void setIdticket(int idticket) {
	this.idticket = idticket;
}
@ManyToOne
public Ticket getTicket() {
	return ticket;
}
public void setTicket(Ticket ticket) {
	this.ticket = ticket;
}
@Id
@GeneratedValue(strategy = GenerationType.TABLE)
public int getIdTag() {
	return idTag;
}
public void setIdTag(int idTag) {
	this.idTag = idTag;
}
public String getTagLab() {
	return tagLab;
}
public void setTagLab(String tagLab) {
	this.tagLab = tagLab;
}
}
