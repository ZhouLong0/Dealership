package it.uniroma3.siw.spring.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Buffet;
import it.uniroma3.siw.spring.model.Piatto;
import it.uniroma3.siw.spring.repository.BuffetRepository;
import it.uniroma3.siw.spring.repository.PiattoRepository;

@Service
public class PiattoService {

	@Autowired
	private PiattoRepository pr;
	
	public Piatto findById(Long id)	{
		return pr.findById(id).get();
	}

	@Transactional
	public void save(Piatto piatto)	{
		pr.save(piatto);
	}
	
}
