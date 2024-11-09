package fr.istic.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BugDTO extends TicketDTO{
	private int idBug;
	private String bug_Description;
	private List<Integer>listIdticket = new ArrayList<Integer>();
	
	public List<Integer> getListIdticket() {
		return Collections.unmodifiableList(listIdticket);
	}

	public void setListIdticket(List<Integer> listIdticket) {
		this.listIdticket = listIdticket;
	}

	public int getIdBug() {
		return idBug;
	}

	public void setIdBug(int idBug) {
		this.idBug = idBug;
	}



	public String getBug_Description() {
		return bug_Description;
	}

	public void setBug_Description(String bug_Description) {
		this.bug_Description = bug_Description;
	}

}
