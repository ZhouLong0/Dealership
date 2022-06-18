package it.uniroma3.siw.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Filiale;
import it.uniroma3.siw.spring.repository.ChefRepository;

@Service
public class ChefService {
	@Autowired
	private ChefRepository pr;
	
	@Transactional
	public void save(Filiale persona)	{
		pr.save(persona);
	}
	
	public Filiale findById(Long id)	{
		return pr.findById(id).get();
	}
	
	public List<Filiale> findAll()	{
		List<Filiale> persone = new ArrayList<Filiale>();
		for(Filiale p : pr.findAll())
			persone.add(p);
		return persone;
	}
	
	public boolean alreadyExists(Filiale persona)	{
		return pr.existsByNomeAndCognomeAndEta(persona.getNome(), persona.getCognome(), persona.getEta());
	}

}
