package it.uniroma3.siw.spring.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Versione {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String descrizione;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Equipaggiamento> ingredienti;
	

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}


	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}


	public List<Equipaggiamento> getIngredienti() {
		return ingredienti;
	}


	public void setIngredienti(List<Equipaggiamento> ingredienti) {
		this.ingredienti = ingredienti;
	}
	
	public void addIngredienti(Equipaggiamento equipaggiamento) {
		this.ingredienti.add(equipaggiamento);
	}
	
	
	
	
	
	
	
	

}
