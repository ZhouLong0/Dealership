package it.uniroma3.siw.spring.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.controller.validator.ChefValidator;
import it.uniroma3.siw.spring.model.Chef;
import it.uniroma3.siw.spring.model.Prodotto;
import it.uniroma3.siw.spring.service.ChefService;

@Controller
public class ChefController {
	@Autowired
	private ChefService ps;
	@Autowired
	private ChefValidator validator;
	
	//convenzione: get per operazioni di lettura, post per operazioni di scrittura
	//binding dei dati
	
	@PostMapping("/persona")
	public String addPersona(@Valid @ModelAttribute("persona") Chef persona, BindingResult bindingResults, Model model)	{
		validator.validate(persona, bindingResults);
		
		if(!bindingResults.hasErrors())	{
			
			ps.save(persona);
			model.addAttribute("persona", persona);
			model.addAttribute("buffets", persona.getBuffets());
			return "chef.html";
		}
		return "personaForm.html";
	}
	
	//richiede tutte le persone
	@GetMapping("/persona")
	public String getPersone(Model model)	{
		 List<Chef> persone = ps.findAll();
		 model.addAttribute("persone", persone);
		 return "persone.html";
	}
	
	@GetMapping("/userChefs")
	public String getPersoneUser(Model model)	{
		 List<Chef> persone = ps.findAll();
		 model.addAttribute("persone", persone);
		 return "userChefs.html";
	}
	
	@GetMapping("/persona/{id}")
	public String getPersona(@PathVariable("id") Long id, Model model)	{
		Chef persona = ps.findById(id);
		model.addAttribute("persona",persona);
		model.addAttribute("buffets", persona.getBuffets());
		return "chef.html";
	}
	
	@GetMapping("/userChef/{id}")
	public String getChef(@PathVariable("id") Long id, Model model)	{
		Chef persona = ps.findById(id);
		model.addAttribute("persona",persona);
		model.addAttribute("buffets", persona.getBuffets());
		return "userChef.html";
	}
	
	@GetMapping("/personaForm")
	public String getPersona(Model model)	{
		model.addAttribute("persona", new Chef());
		return "chefForm.html";
	}
	
	@RequestMapping(value="/admin/chefs", method = RequestMethod.GET)
    public String addProdotto(Model model) {
		List<Chef> persone = ps.findAll();
		 model.addAttribute("persone", persone);
        return "chefs.html";
    }
}
