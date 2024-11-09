package fr.istic.taa.jaxrs.rest;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import org.hibernate.mapping.Array;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import fr.istic.jpa.InfoUser;
import fr.istic.jpa.User;
import fr.istic.taa.jaxrs.dao.generic.UserDAO;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Path("/api")
@Produces({"application/json"})
public class LoginResource {
	private UserDAO u = new UserDAO();
	
	@POST
	@Path("/login")
	@Consumes("application/json")
    public Response login(User user) {
        // Vérifier les informations d'identification de l'utilisateur
		 User f = u.findUserByUsernameAndPassword(user.getUserName(), user.getPassword());
		 //System.out.print(f);
        if (f!=null) {
            // Si les informations d'identification sont valides, générer un jeton d'accès
            //String accessToken = generateAccessToken(user.getUserName());
            
            // Retourner une réponse avec le jeton d'accès
            //return Response.ok().entity(f).build();
            //System.out.println("sdd");
            //System.out.println(new AuthResponse(accessToken));
        	String token= generateAccessToken( f.getUserName());
        	 InfoUser ius = new InfoUser();
        	 ius.setUserName(f.getUserName());
        	 ius.setUserAdress(f.getUserAddress());
        	 ius.setToken(token);
        	 ius.setId(f.getUserId());
        	// user.addAll(token);
        	 //tb[0]=f.getUserId();
           return Response.ok().entity(ius).build();
        } else {
            // Si les informations d'identification sont invalides, retourner une réponse d'erreur
            return Response.status(Response.Status.UNAUTHORIZED).entity("Identifiants invalides").build();
        }
    }
	
	@POST
	@Path("/user/email-verication")
	@Consumes("application/json")
    public Response emailVerication(User user) {
        // Vérifier les informations d'identification de l'utilisateur
		 User f = u.findUserByEmail(user.getEmail());
		 //System.out.print(f);
        if (f!=null) {
           
        	String token= generateAccessToken( f.getUserName());
        	 InfoUser ius = new InfoUser();
        	 ius.setUserName(f.getUserName());
        	 ius.setEmail(f.getEmail());
        	 ius.setUserAdress(f.getUserAddress());
        	 ius.setToken(token);
        	 ius.setId(f.getUserId());
        	
           return Response.ok().entity(ius).build();
        } else {
            // Si les informations d'identification sont invalides, retourner une réponse d'erreur
            return Response.status(Response.Status.UNAUTHORIZED).entity("Cet utilisateur n'existe pas!").build();
        }
    }
    private String generateAccessToken(String username) {
        // Générer un jeton d'accès en utilisant JWT
        Key key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256); // Générer une clé secrète
        String accessToken = Jwts.builder()
                .setSubject(username)
                .setIssuer("mon_service")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 900_000)) // 15 minutes d'expiration
                .signWith(key)
                .compact();
        return accessToken;
    }
}
