package it.uniroma3.siw.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Filiale;
import it.uniroma3.siw.spring.repository.FilialeRepository;

@Service
public class FilialeService {
	@Autowired
	private FilialeRepository pr;
	
	@Transactional
	public void save(Filiale filiale)	{
		pr.save(filiale);
	}
	
	public Filiale findById(Long id)	{
		return pr.findById(id).get();
	}
	
	public List<Filiale> findAll()	{
		List<Filiale> filiali = new ArrayList<>();
		for(Filiale f : pr.findAll())
			filiali.add(f);
		return filiali;
	}
	
	public boolean alreadyExists(Filiale filiale)	{
		return pr.existsByNomeAndIndirizzoAndPhone(filiale.getNome(), filiale.getIndirizzo(), filiale.getPhone());
	}

}
