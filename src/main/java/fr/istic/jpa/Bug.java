package fr.istic.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity 
@DiscriminatorValue("1")
public class Bug extends Ticket  {
	
	
	private String bug_Description;
	public Bug() {
		super();
	}
	
	public Bug(String bug_Description) {
		super();
		this.bug_Description = bug_Description;
	} 
	
	

	public String getBug_Description() {
		return bug_Description;
	}
	
	public void setBug_Description(String bug_Description) {
		this.bug_Description = bug_Description;
	}
	

}
