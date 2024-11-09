package fr.istic.taa.jaxrs.rest;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class CorsFilter implements ContainerResponseFilter {
	 @Override
	    public void filter(ContainerRequestContext requestContext, 
	      ContainerResponseContext responseContext) throws IOException {
	          responseContext.getHeaders().add(
	            "Access-Control-Allow-Origin", "*");
	          responseContext.getHeaders().add(
	            "Access-Control-Allow-Credentials", "true");
	          responseContext.getHeaders().add(
	           "Access-Control-Allow-Headers",
	           "origin, content-type, accept, authorization");
	          responseContext.getHeaders().add(
	            "Access-Control-Allow-Methods", 
	            "GET, POST, PUT, DELETE,PATCH, OPTIONS, HEAD");
	    }
	 @GET
	 @Path("/")
	 @Produces({MediaType.TEXT_PLAIN})
	 public Response index() {
	     return Response
	       .status(200)
	       .header("Access-Control-Allow-Origin", "*")
	       .header("Access-Control-Allow-Credentials", "true")
	       .header("Access-Control-Allow-Headers",
	         "origin, content-type, accept, authorization")
	       .header("Access-Control-Allow-Methods", 
	         "GET, POST, PUT, DELETE,PATCH, OPTIONS, HEAD")
	       .entity("")
	       .build();
	 }

	
}


