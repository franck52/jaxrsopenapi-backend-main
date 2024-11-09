package fr.istic.taa.jaxrs.rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.istic.dto.UserDTO;
import fr.istic.jpa.Commentaire;
import fr.istic.jpa.Ticket;
import fr.istic.jpa.User;
import fr.istic.taa.jaxrs.dao.generic.TicketDAO;
import fr.istic.taa.jaxrs.dao.generic.UserDAO;
import io.swagger.v3.oas.annotations.Parameter;


@RestController
@CrossOrigin(origins = "http://localhost:4200")

@Path("/users")
@Produces({"application/json"})
public class UserService{
	UserDAO dao= new UserDAO();
	TicketDAO ticketDAO  = new TicketDAO();
	User u = new User();
	 /* @GET
	  @Produces({"application/json"})
	  public Response hello() {
		  return Response.ok().entity("welcom").build();
		  
	  }*/
	 // @Path("/users")
	  @GET
	  @Produces({"application/json"})
	  public List<User> getAllUser()  {
	      // return USER
	      return  this.dao.findAll();
	  }
	  @POST	  
	  @Consumes("application/json")
	  @Path("/create")
	  public Response addUser(
	      @Parameter(description = "User object that needs to be added to the store", required = true) User user) {
	    // add user
		  User existingUser = dao.findUserByEmail(user.getEmail());
		  
		  if(existingUser!=null) {
			  return Response.ok().entity("Un utilisateur avec cet email existe déjà").build();
		  }
		  dao.save(user);
	    return Response.ok().entity(user).build();
	  }
	  /*
	   * 
	   * create user with dto
	   * */
	  @POST	  
	  @Consumes("application/json")
	  @Path("/createDTO")
	  public Response addUserDTO(
	      @Parameter(description = "User object that needs to be added to the store", required = true) UserDTO user) {
	    // add user 
		  User newUser = new User();
		  newUser.setUserName(user.getUserName());
		  newUser.setUserAddress(user.getUserAddress());
		  dao.save(newUser);
	    return Response.ok().entity(user).build();
	  }
	  @GET  
	  @Consumes("application/json")
	  @Path("/delete/{userId}")
	  public Response deleteUser(
	     @PathParam("userId")  Integer id) {
	    // add user
		  if(dao.findOne(id)!=null) {
			  dao.deleteById(id);
			  return Response.ok().entity("success!!").build();
			  
		  }
		  else {
			  return Response.ok().entity("user not exist!!").build();
			  
		  }
		  
	  }
	  
	  @GET  
	  @Consumes("application/json")
	  @Path("/findById/{userId}")
	  public User findUserById(
	     @PathParam("userId")  Integer id) {
	    // add user
		  if(dao.findOne(id)!=null) {
			  return dao.findOne(id); 
		  }
		  
		  else return null;
	  }
	  
	  @PUT  
	  @Consumes("application/json")
	  @Path("/update")
	  public Response updateUser(
			  @Parameter(description = "User object that needs to be added to the store", required = true)User user) {
		     //@PathParam("userId")  Integer id)  {
	         // apdate user
		  User userExist = dao.findOne(user.getUserId());
		  if(userExist  !=null) {
			  dao.update(user);
			  return Response.ok().entity(user).build();
			  
		  }
		  
		  else {
			  return Response.ok().entity("Erreur lors de l'execution!!!").build();
		  }
	  }
	  
	  @PUT  
	  @Consumes("application/json")
	  @Path("/update-password/{userId}")
	  public Response updatePassword(@PathParam("userId") int userId, UserDTO userDTO) {
		  EntityManager entityManager ;
		     //@PathParam("userId")  Integer id)  {
	         // Verifier si le user existe
		  User userExist = dao.findOne(userId);
		  if (userExist != null) {
			  int numUpdated = dao.updateUserPassword(userId, userDTO.getPassword());
			  if (numUpdated == 0) {
			        // Si aucun utilisateur n'a été mis à jour, renvoyer une réponse NOT FOUND
			        return Response.status(Response.Status.NOT_FOUND).build();
			    }else {
			    	// Sinon, renvoyer une réponse OK avec un objet UserDTO mis à jour
			    	// Retourner une réponse de réussite avec un objet UserDTO mis à jour
				       UserDTO updatedUserDTO = new UserDTO(userExist.getUserId(), userExist.getUserName(),userExist.getEmail(),userExist.getUserAddress(),userExist.getPassword());
				       return Response.ok(updatedUserDTO).build();
			    }
			// Mettre à jour le mot de passe de l'utilisateur
			 
		

		       
			  
	           
	        }else return Response.status(Response.Status.NOT_FOUND).build();
	  }

	    @GET
	    @Path("/by-email/{email}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response findUserByEmail(@PathParam("email") String email) {
	        User user = dao.findUserByEmail(email);
	        if(user != null) {
	            return Response.ok(user).build();
	        } else {
	            return Response.status(Response.Status.NOT_FOUND).build();
	        }
	    }
	
	  
	  
	  /*@POST
		@Path("users/login")
		@Consumes("application/json")
	    public Response login(@Parameter(description = "User object that needs to be added to the store", required = true)User user) {
	        // Vérifier les informations d'identification de l'utilisateur
			 User f =dao.findUserByUsernameAndPassword(user.getUserName(), user.getPassword());
			 //System.out.print(f);
	        if (f!=null) {
	            // Si les informations d'identification sont valides, générer un jeton d'accès
	            String accessToken = generateAccessToken(user.getUserName());
	            // Retourner une réponse avec le jeton d'accès
	            return Response.ok().entity(f.getUserName()).build();
	            //System.out.println("sdd");
	            //System.out.println(new AuthResponse(accessToken));
	           return Response.ok().entity(new AuthResponse(accessToken)).build();
	        } else {
	            // Si les informations d'identification sont invalides, retourner une réponse d'erreur
	            return Response.status(Response.Status.UNAUTHORIZED).entity("Identifiants invalides").build();
	        }
	    }*/


}
