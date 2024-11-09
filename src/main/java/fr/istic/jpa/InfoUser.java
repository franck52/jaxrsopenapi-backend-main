package fr.istic.jpa;

public class InfoUser {
	private String token, userName, userAdress, email;
	private int id;
	

	public InfoUser(String token, String userName, String userAdress, int id) {
		super();
		this.token = token;
		this.userName = userName;
		this.userAdress = userAdress;
	}

	public InfoUser() {
		// TODO Auto-generated constructor stub
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAdress() {
		return userAdress;
	}

	public void setUserAdress(String userAdress) {
		this.userAdress = userAdress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

}
