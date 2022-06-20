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

import it.uniroma3.siw.spring.controller.validator.BuffetValidator;
import it.uniroma3.siw.spring.model.Modello;
import it.uniroma3.siw.spring.model.Filiale;
import it.uniroma3.siw.spring.service.ModelloService;
import it.uniroma3.siw.spring.service.FilialeService;

@Controller
public class ModelloController {
	@Autowired
	private FilialeService ps;

	@Autowired
	private ModelloService bs;

	@Autowired
	private BuffetValidator bv;

	@GetMapping("persona/{id}/buffet")
	public String getBuffet(@PathVariable("id") Long id, Model model)	{
		model.addAttribute("buffet", new Modello());
		Filiale persona = ps.findById(id);
		model.addAttribute("persona",persona);
		return "buffetForm.html";
	}

	@PostMapping("persona/buffet/{idPersona}")
	public String addPersona(@Valid @ModelAttribute("buffet") Modello modello,BindingResult bindingResults , @PathVariable("idPersona") Long id,Model model)	{
		Filiale persona1 = ps.findById(id);

		if(!bindingResults.hasErrors())	{
			persona1.getBuffets().add(modello);
			ps.save(persona1);
			model.addAttribute("persona", persona1);
			model.addAttribute("buffets", persona1.getBuffets());
			return "chef.html";
		}

		model.addAttribute("persona", persona1);
		model.addAttribute("buffet", modello);
		return "buffetForm.html";
	}


	@GetMapping("/buffet/{id}")
	public String getPersona(@PathVariable("id") Long id, Model model)	{
		Modello modello = bs.findById(id);
		model.addAttribute("buffet",modello);
		model.addAttribute("piatti", modello.getPiatti());
		return "buffet.html";
	}

	@GetMapping("/userBuffet/{id}")
	public String getBuffetUser(@PathVariable("id") Long id, Model model)	{
		Modello modello = bs.findById(id);
		model.addAttribute("buffet",modello);
		model.addAttribute("piatti", modello.getPiatti());
		return "userBuffet.html";
	}

	@GetMapping("/buffet/remove/{idBuffet}/{idChef}")
	public String removeBuffet(@PathVariable("idBuffet") Long idB, @PathVariable("idChef") Long idC , Model model) {
		System.out.println("\n\n\n\n\n\n\n BBBBBBBBBBBBBBBB");
		Filiale filiale = ps.findById(idC);
		Modello modello = bs.findById(idB);
		filiale.getBuffets().remove(modello);
		ps.save(filiale);
		bs.deleteById(idB);
		model.addAttribute("persona", filiale);
		model.addAttribute("buffets", filiale.getBuffets());
		return "chef.html";
	}

}
