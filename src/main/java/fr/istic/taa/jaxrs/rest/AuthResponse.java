package fr.istic.taa.jaxrs.rest;

public class AuthResponse {
	   private String accessToken;

	    public AuthResponse(String accessToken) {
	        this.accessToken = accessToken;
	    }

	    public String getAccessToken() {
	        return accessToken;
	    }

	    public void setAccessToken(String accessToken) {
	        this.accessToken = accessToken;
	    }

}
