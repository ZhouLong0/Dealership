package it.uniroma3.siw.spring.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Modello;
import it.uniroma3.siw.spring.model.Versione;
import it.uniroma3.siw.spring.repository.ModelloRepository;
import it.uniroma3.siw.spring.repository.VersioneRepository;

@Service
public class VersioneService {

	@Autowired
	private VersioneRepository versioneRepository;
	
	public Versione findById(Long id)	{
		return versioneRepository.findById(id).get();
	}

	@Transactional
	public void save(Versione versione)	{
		versioneRepository.save(versione);
	}
	
}
