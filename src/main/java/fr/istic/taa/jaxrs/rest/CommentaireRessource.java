package fr.istic.taa.jaxrs.rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import fr.istic.dto.CommentaireDTO;
import fr.istic.jpa.Commentaire;
import fr.istic.jpa.Ticket;
import fr.istic.jpa.User;
import fr.istic.taa.jaxrs.dao.generic.CommentaireDOA;
import fr.istic.taa.jaxrs.dao.generic.EntityManagerHelper;
import fr.istic.taa.jaxrs.dao.generic.TicketDAO;
import fr.istic.taa.jaxrs.dao.generic.UserDAO;
import io.swagger.v3.oas.annotations.Parameter;

@Path("/commentaires")
@Produces({"application/json"})
public class CommentaireRessource {
	CommentaireDOA dao = new CommentaireDOA();
	UserDAO userdao = new UserDAO();
	TicketDAO ticketdao = new TicketDAO();
	 @GET
	  public List<Commentaire> getCommentaire()  {
	      // return tags
	      return  this.dao.findAll();
	  }
	 @POST	  
	  @Consumes("application/json")
	  @Path("/createCommentaire")
	 public Response createCommentaire(@PathParam("ticket") Integer id,
			 @Parameter(description = "commentaire object that needs to be added to the store", required = true) CommentaireDTO commentairedto) {
		 Commentaire newCommentaire = new Commentaire();
		 int idticket = commentairedto.getTicket_id_ticket();
		 Ticket ticket = ticketdao.findOne(idticket);
		 int userId = commentairedto.getUserId();
		
		 User user = userdao.findOne(userId);
		 if(userdao.findOne(userId)!=null && ticketdao.findOne(idticket)!=null) {
			 newCommentaire.setTexteCommentaire(commentairedto.getTexteCommentaire());
			 
			 newCommentaire.setTicket(ticket);
			 newCommentaire.setUser(user);
			 dao.save(newCommentaire);
			 return Response.ok().entity(commentairedto).build();
			 
		 }
		 
		 else {
			 return Response.ok().entity("erreur lors de l'enregistrement!!!").build();
		 }
		 
	 }
	 
	 
	 
	 @GET  
	  @Consumes("application/json")
	  @Path("/delete/{commentaireId}")
	  public Response deleteCommentaire(
	     @PathParam("commentaireId")  Integer id) {
		 if(dao.findOne(id)!=null) {
			 dao.deleteById(id);
			 return Response.ok().entity(id).build();
		 }else {
			 return Response.ok().entity("erreur!!").build();
			 
		 }
		 
		  
	    
	  }
	 @PUT  
	  @Consumes("application/json")
	  @Path("/update/{id}")
	 public Response updateCommentaire(
		      @Parameter(description = "commentaire object that needs to be added to the store", required = true)Commentaire update
		      , @PathParam("id")  Integer id){
		   dao.update(update);
		return Response.ok().entity(update).build();
	  }
	
	 
	 
	 @GET
	 @Path("/{id}")
	 @Produces({"application/json"})
	 public Commentaire getCommentaireById(@PathParam("id")Integer id ) {
		 Commentaire cm = dao.findOne(id);
		 if(cm==null) throw new NotFoundException();
		 return cm;
	 }
}
