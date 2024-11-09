package fr.istic.taa.jaxrs.dao.generic;

import fr.istic.jpa.Ticket;

public class TicketDAO extends AbstractJpaDao<Integer, Ticket> {
	public TicketDAO() {
		this.setClazz(Ticket.class);
	}
}
