package it.uniroma3.siw.spring.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Filiale {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String cognome;
	
	@NotNull
	@Min(0)
	@Max(120)
	private Integer eta;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Modello> modellos;
	
	public Filiale()	{
		this.modellos = new ArrayList<Modello>();
	}
	
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

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Integer getEta() {
		return eta;
	}

	public void setEta(Integer eta) {
		this.eta = eta;
	}

	public List<Modello> getBuffets() {
		return modellos;
	}
//
//	public void setBuffets(List<Buffet> buffets) {
//		this.buffets = buffets;
//	}
//	
//	public void addBuffet(Buffet buffet)	{
//		this.buffets.add(buffet);
//	}

	
	
	
}
