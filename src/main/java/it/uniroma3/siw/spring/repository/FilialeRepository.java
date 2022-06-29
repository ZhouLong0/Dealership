package it.uniroma3.siw.spring.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Filiale;

public interface FilialeRepository extends CrudRepository<Filiale, Long> {

	public boolean existsByNomeAndIndirizzoAndPhone(String nome, String indirizzo, String phone);
	
	public long count();
}
