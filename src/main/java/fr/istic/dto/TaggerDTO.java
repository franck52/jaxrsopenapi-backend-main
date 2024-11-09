package fr.istic.dto;

public class TaggerDTO {
	 
   private Long id;

	   
	private String nom;

	// Constructeurs, getters et setters

	public TaggerDTO() {
		// TODO Auto-generated constructor stub
	}

	public TaggerDTO(Long id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}
