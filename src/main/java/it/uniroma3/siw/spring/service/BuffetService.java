package it.uniroma3.siw.spring.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Buffet;
import it.uniroma3.siw.spring.model.Chef;
import it.uniroma3.siw.spring.repository.BuffetRepository;
import it.uniroma3.siw.spring.repository.ChefRepository;

@Service
public class BuffetService {

	@Autowired
	private BuffetRepository pr;
	
	public Buffet findById(Long id)	{
		return pr.findById(id).get();
	}
	
	public void deleteById(Long id)	{
		pr.deleteById(id);
	}

	@Transactional
	public void save(Buffet buffet)	{
		pr.save(buffet);
	}
	
	@Transactional
	public void remove(Buffet buffet)	{
		pr.delete(buffet);
	}
	
}
