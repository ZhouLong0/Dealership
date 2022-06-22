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
import it.uniroma3.siw.spring.model.Filiale;
import it.uniroma3.siw.spring.service.FilialeService;

@Controller
public class FilialeController {
	@Autowired
	private FilialeService ps;
	@Autowired
	private ChefValidator validator;
	
	//convenzione: get per operazioni di lettura, post per operazioni di scrittura
	//binding dei dati
	
	@PostMapping("/persona")
	public String addPersona(@Valid @ModelAttribute("persona") Filiale persona, BindingResult bindingResults, Model model)	{
		validator.validate(persona, bindingResults);
		
		if(!bindingResults.hasErrors())	{
			
			ps.save(persona);
			model.addAttribute("persona", persona);
			model.addAttribute("buffets", persona.getModelli());
			return "chef.html";
		}
		return "chefForm.html";
	}
	
	//richiede tutte le persone
	@GetMapping("/persona")
	public String getPersone(Model model)	{
		 List<Filiale> persone = ps.findAll();
		 model.addAttribute("persone", persone);
		 return "persone.html";
	}
	
	@GetMapping("/userChefs")
	public String getPersoneUser(Model model)	{
		 List<Filiale> persone = ps.findAll();
		 model.addAttribute("persone", persone);
		 return "userChefs.html";
	}
	
	@GetMapping("/persona/{id}")
	public String getPersona(@PathVariable("id") Long id, Model model)	{
		Filiale persona = ps.findById(id);
		model.addAttribute("persona",persona);
		model.addAttribute("buffets", persona.getModelli());
		return "chef.html";
	}
	
	@GetMapping("/userChef/{id}")
	public String getChef(@PathVariable("id") Long id, Model model)	{
		Filiale persona = ps.findById(id);
		model.addAttribute("persona",persona);
		model.addAttribute("buffets", persona.getModelli());
		return "userChef.html";
	}
	
	@GetMapping("/personaForm")
	public String getPersona(Model model)	{
		model.addAttribute("persona", new Filiale());
		return "chefForm.html";
	}
	
	@RequestMapping(value="/admin/chefs", method = RequestMethod.GET)
    public String addProdotto(Model model) {
		List<Filiale> persone = ps.findAll();
		 model.addAttribute("persone", persone);
        return "chefs.html";
    }
}
