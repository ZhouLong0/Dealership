package it.uniroma3.siw.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Chef;
import it.uniroma3.siw.spring.repository.ChefRepository;

@Service
public class ChefService {
	@Autowired
	private ChefRepository pr;
	
	@Transactional
	public void save(Chef persona)	{
		pr.save(persona);
	}
	
	public Chef findById(Long id)	{
		return pr.findById(id).get();
	}
	
	public List<Chef> findAll()	{
		List<Chef> persone = new ArrayList<Chef>();
		for(Chef p : pr.findAll())
			persone.add(p);
		return persone;
	}
	
	public boolean alreadyExists(Chef persona)	{
		return pr.existsByNomeAndCognomeAndEta(persona.getNome(), persona.getCognome(), persona.getEta());
	}

}
