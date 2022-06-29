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

import it.uniroma3.siw.spring.controller.validator.FilialeValidator;
import it.uniroma3.siw.spring.model.Filiale;
import it.uniroma3.siw.spring.service.FilialeService;

@Controller
public class FilialeController {
	@Autowired
	private FilialeService filialeService;
	@Autowired
	private FilialeValidator filialeValidator;
	
	//convenzione: get per operazioni di lettura, post per operazioni di scrittura
	//binding dei dati
	
	@PostMapping("/filiale")
	public String addFiliale(@Valid @ModelAttribute("filiale") Filiale filiale, BindingResult bindingResults, Model model)	{
		filialeValidator.validate(filiale, bindingResults);
		
		if(!bindingResults.hasErrors())	{
			
			filialeService.save(filiale);
			model.addAttribute("filiale", filiale);
			model.addAttribute("modelli", filiale.getModelli());
			return "filiale.html";
		}
		return "filialeForm.html";
	}
	
	
	@GetMapping("/filiale")
	public String getFiliali(Model model)	{
		 List<Filiale> filiali = filialeService.findAll();
		 model.addAttribute("filiali", filiali);
		 return "filiale.html";
	}
	
	@GetMapping("/userFiliali")
	public String getFilialiUser(Model model)	{
		 List<Filiale> filiali = filialeService.findAll();
		 model.addAttribute("filiali", filiali);
		 return "userFiliali.html";
	}
	
	@GetMapping("/filiale/{id}")
	public String getFiliale(@PathVariable("id") Long id, Model model)	{
		Filiale filiale = filialeService.findById(id);
		model.addAttribute("filiale",filiale);
		model.addAttribute("modelli", filiale.getModelli());
		return "filiale.html";
	}
	
	@GetMapping("/userFiliale/{id}")
	public String getChef(@PathVariable("id") Long id, Model model)	{
		Filiale filiale = filialeService.findById(id);
		model.addAttribute("filiale",filiale);
		model.addAttribute("modelli", filiale.getModelli());
		return "userFiliale.html";
	}
	
	@GetMapping("/filialeForm")
	public String getFiliale(Model model)	{
		model.addAttribute("filiale", new Filiale());
		return "filialeForm.html";
	}
	
	@RequestMapping(value="/admin/filiali", method = RequestMethod.GET)
    public String addProdotto(Model model) {
		List<Filiale> filiali = filialeService.findAll();
		model.addAttribute("filiali", filiali);
		long count = filialeService.count();
		model.addAttribute("count", count);
        return "filiali.html";
    }
}
