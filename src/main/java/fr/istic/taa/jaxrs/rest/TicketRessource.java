package fr.istic.taa.jaxrs.rest;

import java.util.List;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import fr.istic.dto.TicketDTO;
import fr.istic.dto.UserDTO;
import fr.istic.jpa.Bug;
import fr.istic.jpa.Ticket;
import fr.istic.jpa.User;
import fr.istic.taa.jaxrs.dao.generic.TicketDAO;
import fr.istic.taa.jaxrs.dao.generic.UserDAO;
import fr.istic.taa.jaxrs.dao.generic.EntityManagerHelper;
import io.swagger.v3.oas.annotations.Parameter;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.CrossOrigin;
@CrossOrigin(origins = "http://localhost:4200")
@Path("/tickets")
@Produces({"application/json", "application/xml"})
public class TicketRessource {
	private EntityManager entityManager;
	
	public TicketRessource(EntityManager entityManager) {
		this.entityManager = entityManager;
		
	}
	public TicketRessource() {
	super();
		
	}
	
    
	TicketDAO t_dao= new TicketDAO();
	UserDAO userDAO = new UserDAO();
	@GET
	public List<Ticket> getAllTicket(){
		return  t_dao.findAll();
	}
	@POST
	@Consumes("application/json")
	@Path("/create")
	public Response createTicket(
			@PathParam("usId") Integer id,
			@Parameter( description = "Ticket object that needs to be added to the store", required = true) TicketDTO ticketdto ) {
		Ticket newticket = new Ticket();
		//TicketDTO ticketDto = new TicketDTO();
		 int userId = ticketdto.getUserId();

		  // Load the user object from the database using the user_id field
		  User user = userDAO.findOne(userId);
		  Bug bug = new Bug();
		  // Create a new Ticket object with the data from the JSON request
		  Ticket ticket = new Ticket();
		  ticket.setLabel(ticketdto.getLabel());
		  ticket.setBug_Description(ticketdto.getBug_Description());
		  ticket.setPriority(ticketdto.getPriority());
		  ticket.setDescriminator(ticketdto.getDescriminator());
		  ticket.setTitle(ticketdto.getTitle());
		  ticket.setUser(user);

		  // Save the new Ticket object to the database
		  t_dao.save(ticket);

		  //return Response.status(Response.Status.CREATED).build();
		  return Response.ok().entity(ticketdto).build();	
		
	}
	
	@GET@ 
	Path("/{id}")
	@Produces({"application/json"})
	public Response getTicketById(@PathParam("id") Integer id){
	 Ticket ticket = t_dao.findOne(id);
	return Response.ok().entity(ticket).build();
	 
	}
	  
	  @GET  
	  @Path("/deleted/{ticketId}")
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)
	  public Response findUserById(
	     @PathParam("ticketId")  Integer id) {
		  //t_dao.deleteById(id);
		  if(t_dao.findOne(id)!=null) {
			  t_dao.deleteById(id);
			  return Response.ok().entity("{\"message\": \"SUCCESS\"}").build();
		  }
		  return Response.ok().entity("{\"message\": \"impossible de supprimer un élément qui n'existe pas!!\"}").build();
	  }
	  
	  @GET  
	  @Path("/comment/count-by-ticket/{ticketId}")
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)
	  public Response findCommentOfTicket(
	     @PathParam("ticketId")  int id) {
		  //t_dao.deleteById(id);
		  if(t_dao.findOne(id)!=null) {
			  Long nbr=t_dao.countCommentsByTicket(id);
			  return Response.ok().entity(nbr).build();
		  }
		  return Response.ok().entity("impossible !!").build();
	  }
	  @PATCH
	  @Path("/update/{id}")
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)
	  public Response updateTicket(
			  @Parameter(description = "ticket object that needs to be added to the store", required = true)TicketDTO ticketdto,@PathParam("id") int id) {
		  Ticket tick= t_dao.findOne(id);
		  
		  if(tick==null) {
			  return Response.status(Response.Status.NOT_FOUND).build();
		  }
		  else {
			  int uid = ticketdto.getUserId();
			  User user = userDAO.findOne(uid);
			  if( ticketdto.getUserId()>0 ) tick.setUser(user);
			  if(ticketdto.getLabel()!=null) tick.setLabel(ticketdto.getLabel());
			  if(ticketdto.getBug_Description()!=null) tick.setBug_Description(ticketdto.getBug_Description());
			  if(ticketdto.getPriority()>=0) tick.setPriority(ticketdto.getPriority());
			  if(ticketdto.getTitle()!=null)tick.setTitle(ticketdto.getTitle());
			  t_dao.update(tick);
			 return Response.ok(tick).build();	  
			  
		  }
	  }
	  @POST
	  @Path("/ajouterTag/{id}")
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)
	  public Ticket ajouterTag(@Parameter(description = "ticket object that needs to be added to the store", required = true)TicketDTO ticketdto,@PathParam("id") int id) {
	    Ticket tick= t_dao.findOne(id);
	    if(tick !=null) {
	    	
	    }
		return null;
		  
	  }
	  
	  @GET
	    @Path("/user/{userId}")
	    public Response findAllTicketsByUser(@PathParam("userId") int userId) {
	        List<Ticket> tickets = t_dao.findAllTicketsByUser(userId);
	        return Response.ok(tickets).build();
	    }
	  
	
}
