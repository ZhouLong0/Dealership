package it.uniroma3.siw.spring.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.spring.model.Modello;
import it.uniroma3.siw.spring.model.Filiale;
import it.uniroma3.siw.spring.model.Versione;
import it.uniroma3.siw.spring.service.ModelloService;
import it.uniroma3.siw.spring.service.FilialeService;
import it.uniroma3.siw.spring.service.VersioneService;

@Controller
public class VersioneController {
	
	@Autowired
	private ModelloService bs;
	
	@Autowired
	private VersioneService pis;

	@GetMapping("buffet/versione/{idBuffet}")
	public String getBuffet(@PathVariable("idBuffet") Long id, Model model)	{
		model.addAttribute("versione", new Versione());
		Modello modello = bs.findById(id);
		model.addAttribute("buffet", modello);
		return "versioneForm.html";
	}
	
	@PostMapping("buffet/versione/{idBuffet}")
	public String addVersione(@ModelAttribute("versione") Versione versione, @PathVariable("idBuffet") Long id, Model model)	{
		Modello buffet1 = bs.findById(id);
		buffet1.addVersione(versione);
		bs.save(buffet1);
		model.addAttribute("buffet", buffet1);
		model.addAttribute("versioni", buffet1.getVersioni());
		return "buffet.html";
	}
	
	
	@GetMapping("/versione/{id}")
	public String getVersione(@PathVariable("id") Long id, Model model)	{
		Versione versione = pis.findById(id);
		model.addAttribute("versione",versione);
		model.addAttribute("ingredienti", versione.getIngredienti());
		return "versione.html";
	}
	
	@GetMapping("/userVersione/{id}")
	public String getPiattoUser(@PathVariable("id") Long id, Model model)	{
		Versione versione = pis.findById(id);
		model.addAttribute("versione",versione);
		model.addAttribute("ingredienti", versione.getIngredienti());
		return "userVersione.html";
	}
	

}
