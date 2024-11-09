package fr.istic.taa.jaxrs.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;


import fr.istic.jpa.Bug;
import fr.istic.taa.jaxrs.dao.generic.BugDAO;

@Path("/bugs")
public class BugRessource {
	BugDAO dao = new BugDAO();
	@GET
	  @Produces("application/json")
	  public List<Bug> getBugs() {
	    // code pour retourner la liste des bugs
		return this.dao.findAll();
	  }

	  /*@GET
	  @Path("/{id}")
	  @Produces("application/json")
	  public Bug getBug(@PathParam("id") int id) {
		return null;
	    // code pour retourner un bug spécifique en fonction de son ID
	  }

	  @POST
	  @Consumes("application/json")
	  public void addBug(Bug bug) {
	    // code pour ajouter un nouveau bug
	  }

	  @PUT
	  @Path("/{id}")
	  @Consumes("application/json")
	  public void updateBug(@PathParam("id") int id, Bug bug) {
	    // code pour mettre à jour un bug existant
	  }

	  @DELETE
	  @Path("/{id}")
	  public void deleteBug(@PathParam("id") int id) {
	    // code pour supprimer un bug existant
	  }*/

}
