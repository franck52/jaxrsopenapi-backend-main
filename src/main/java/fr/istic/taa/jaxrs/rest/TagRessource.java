package fr.istic.taa.jaxrs.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.istic.dto.TagDTO;
import fr.istic.jpa.Tag;
import fr.istic.jpa.Ticket;
import fr.istic.taa.jaxrs.dao.generic.TagDAO;
import fr.istic.taa.jaxrs.dao.generic.TicketDAO;
import io.swagger.v3.oas.annotations.Parameter;

@Path("/tags")
@Produces({"application/json"})
public class TagRessource {
	TagDAO dao = new TagDAO();
	TicketDAO tkdao= new TicketDAO();
	
	 @GET
	  public List<Tag> getAllUser()  {
	      // return tags
	      return  this.dao.findAll();
	  }
	  @POST	  
	  @Consumes("application/json")
	  @Path("/create/{id}")
	  public Response createTag(@Parameter(description = "tag object that needs to be added to the store", required = true) TagDTO tagdto, @PathParam("id") int id) {
		  Tag newtag = new Tag();
		  newtag.setTagLab(tagdto.getTagLab());
		  int idticket =tagdto.getIdticket() ;
		  Ticket tk =tkdao.findOne(id);
		  if(tk.getId_ticket() ==0) {
			  return Response.ok().entity("Erreur lors de l'execution!!").build();
			  
		  }else {
			  newtag.setTicket(tk);
			  newtag.setIdticket(tagdto.getIdticket());
			  dao.save(newtag);
			  return Response.ok().entity(newtag).build();
		  }
		  
		  
		  
	  }
	@GET
	@Path("/{id}")
	@Produces({"application/json"})
	public Tag getTagById(@PathParam("id")Integer id ) {
			Tag cm = dao.findOne(id);
			if(cm==null) throw new NotFoundException();
			 return cm;
	}
	
	 @GET  
	  @Consumes("application/json")
	  @Path("/delete/{tagId}")
	  public Response deleteTag(
	     @PathParam("tagId")  Integer id) {
		 if(dao.findOne(id)!=null) {
			 dao.deleteById(id);
			 return Response.ok().entity("operation ressi!!").build();
		 }else {
			 return Response.ok().entity("Erreur lors de l'execution").build();
		 }  
	  }
	 @PATCH
	 @Path("/update/{id}")
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.APPLICATION_JSON)
	  public Response updateTag(
			  @Parameter(description = "tag object that needs to be added to the store", required = true)TagDTO tagdto,@PathParam("id") int id) {
		  Tag newtag= dao.findOne(id);
		  
		  if(newtag==null) {
			  return Response.status(Response.Status.NOT_FOUND).build();
		  }
		  else {
			  int tickid = tagdto.getIdticket();
			  Ticket ticket = tkdao.findOne(tickid);
			  if( tagdto.getIdticket()>0 ) newtag.setTicket(ticket);
			  if(tagdto.getTagLab()!=null) newtag.setTagLab(tagdto.getTagLab());
			 
			  dao.update(newtag);
			 return Response.ok(newtag).build();	  
			  
		  }
	  }
		 
	 

}
