package fr.istic.jpa;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity 
@DiscriminatorValue("2")
public class Evolution extends Ticket {
	
	private int priority;
	
	
	public Evolution() {
		super();
		
	}
	public Evolution(int priority) {
		super();
		this.priority = priority;
		
	}
	
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	

}
