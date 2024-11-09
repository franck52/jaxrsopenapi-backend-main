package fr.istic.dto;

import fr.istic.jpa.Ticket;

public class TagDTO {
	private String tagLab;
	private Ticket ticket;
	private  int  idticket;
	
	public TagDTO(String label, int idticket) {
		super();
		this.tagLab = label;
		this.idticket = idticket;
	}
	public TagDTO() {
		super();
		
	}

	public int getIdticket() {
		return idticket;
	}
	public void setIdticket(int idticket) {
		this.idticket = idticket;
	}
	public String getTagLab() {
		return tagLab;
	}
	public void setTagLab(String tagLab) {
		this.tagLab = tagLab;
	}
	public Ticket getTicket() {
		return ticket;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

}
