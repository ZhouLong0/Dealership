package it.uniroma3.siw.spring.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Modello;
import it.uniroma3.siw.spring.model.Filiale;
import it.uniroma3.siw.spring.repository.ModelloRepository;
import it.uniroma3.siw.spring.repository.FilialeRepository;

@Service
public class ModelloService {

	@Autowired
	private ModelloRepository pr;
	
	public Modello findById(Long id)	{
		return pr.findById(id).get();
	}
	
	public void deleteById(Long id)	{
		pr.deleteById(id);
	}

	@Transactional
	public void save(Modello modello)	{
		pr.save(modello);
	}
	
	@Transactional
	public void remove(Modello modello)	{
		pr.delete(modello);
	}
	
}