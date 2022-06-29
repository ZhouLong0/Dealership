package it.uniroma3.siw.spring.service;

import java.util.ArrayList;
import java.util.List;

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
	private ModelloRepository modelloRepository;
	
	public Modello findById(Long id)	{
		return modelloRepository.findById(id).get();
	}
	
	public void deleteById(Long id)	{
		modelloRepository.deleteById(id);
	}

	@Transactional
	public void save(Modello modello)	{
		modelloRepository.save(modello);
	}
	
	@Transactional
	public void remove(Modello modello)	{
		modelloRepository.delete(modello);
	}

	public List<Modello> findAll()	{
		List<Modello> modelli = new ArrayList<Modello>();
		for(Modello m : modelloRepository.findAll())
			modelli.add(m);
		return modelli;
	}
	
}
