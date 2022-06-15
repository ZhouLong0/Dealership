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

import it.uniroma3.siw.spring.model.Buffet;
import it.uniroma3.siw.spring.model.Chef;
import it.uniroma3.siw.spring.service.BuffetService;
import it.uniroma3.siw.spring.service.ChefService;

@Controller
public class BuffetController {
	@Autowired
	private ChefService ps;
	
	@Autowired
	private BuffetService bs;

	@GetMapping("persona/{id}/buffet")
	public String getBuffet(@PathVariable("id") Long id, Model model)	{
		model.addAttribute("buffet", new Buffet());
		Chef persona = ps.findById(id);
		model.addAttribute("persona",persona);
		return "buffetForm.html";
	}
	
	@PostMapping("persona/buffet/{idPersona}")
	public String addPersona(@ModelAttribute("buffet") Buffet buffet, @PathVariable("idPersona") Long id, Model model)	{
		Chef persona1 = ps.findById(id);
		persona1.getBuffets().add(buffet);
		ps.save(persona1);
		model.addAttribute("persona", persona1);
		model.addAttribute("buffets", persona1.getBuffets());
		return "chef.html";
	}
	
	@GetMapping("/buffet/{id}")
	public String getPersona(@PathVariable("id") Long id, Model model)	{
		Buffet buffet = bs.findById(id);
		model.addAttribute("buffet",buffet);
		model.addAttribute("piatti", buffet.getPiatti());
		return "buffet.html";
	}
	
	@GetMapping("/userBuffet/{id}")
	public String getBuffetUser(@PathVariable("id") Long id, Model model)	{
		Buffet buffet = bs.findById(id);
		model.addAttribute("buffet",buffet);
		model.addAttribute("piatti", buffet.getPiatti());
		return "userBuffet.html";
	}
	
	@GetMapping("/buffet/remove/{idBuffet}/{idChef}")
	public String removeBuffet(@PathVariable("idBuffet") Long idB, @PathVariable("idChef") Long idC , Model model) {
		System.out.println("\n\n\n\n\n\n\n BBBBBBBBBBBBBBBB");
		Chef chef = ps.findById(idC);
		Buffet buffet = bs.findById(idB);
		chef.getBuffets().remove(buffet);
		ps.save(chef);
		bs.deleteById(idB);
		model.addAttribute("persona", chef);
		model.addAttribute("buffets", chef.getBuffets());
		return "chef.html";
	}

}
