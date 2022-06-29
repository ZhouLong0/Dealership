package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.spring.model.Modello;
import it.uniroma3.siw.spring.model.Versione;
import it.uniroma3.siw.spring.service.ModelloService;
import it.uniroma3.siw.spring.service.VersioneService;

@Controller
public class VersioneController {
	
	@Autowired
	private ModelloService modelloService;
	
	@Autowired
	private VersioneService versioneService;

	@GetMapping("modello/versione/{idModello}")
	public String getModello(@PathVariable("idModello") Long id, Model model)	{
		model.addAttribute("versione", new Versione());
		Modello modello = modelloService.findById(id);
		model.addAttribute("modello", modello);
		return "versioneForm.html";
	}
	
	@PostMapping("modello/versione/{idModello}")
	public String addVersione(@ModelAttribute("versione") Versione versione, @PathVariable("idModello") Long id, Model model)	{
		Modello modello = modelloService.findById(id);
		versione.setModello(modello);
		modello.addVersione(versione);
		modelloService.save(modello);
		model.addAttribute("modello", modello);
		model.addAttribute("versioni", modello.getVersioni());
		return "modello.html";
	}
	
	
	@GetMapping("/versione/{id}")
	public String getVersione(@PathVariable("id") Long id, Model model)	{
		Versione versione = versioneService.findById(id);
		model.addAttribute("versione",versione);
		model.addAttribute("equipaggiamenti", versione.getEquipaggiamenti());
		return "versione.html";
	}
	
	@GetMapping("/userVersione/{id}")
	public String getVersioneUser(@PathVariable("id") Long id, Model model)	{
		Versione versione = versioneService.findById(id);
		model.addAttribute("versione",versione);
		model.addAttribute("equipaggiamenti", versione.getEquipaggiamenti());
		return "userVersione.html";
	}
	

}
