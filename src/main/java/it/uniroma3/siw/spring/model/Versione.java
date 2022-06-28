package it.uniroma3.siw.spring.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
	private List<Equipaggiamento> equipaggiamenti;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Modello modello;
	

	public Modello getModello() {
		return modello;
	}


	public void setModello(Modello modello) {
		this.modello = modello;
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

	public String getDescrizione() {
		return descrizione;
	}


	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}


	public List<Equipaggiamento> getEquipaggiamenti() {
		return equipaggiamenti;
	}


	public void setEquipaggiamenti(List<Equipaggiamento> equipaggiamenti) {
		this.equipaggiamenti = equipaggiamenti;
	}
	
	public void addEquipaggiamento(Equipaggiamento equipaggiamento) {
		this.equipaggiamenti.add(equipaggiamento);
	}
	

}
