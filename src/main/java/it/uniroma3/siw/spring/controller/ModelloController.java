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

import it.uniroma3.siw.spring.controller.validator.ModelloValidator;
import it.uniroma3.siw.spring.model.Filiale;
import it.uniroma3.siw.spring.model.Modello;
import it.uniroma3.siw.spring.service.FilialeService;
import it.uniroma3.siw.spring.service.ModelloService;

@Controller
public class ModelloController {
	@Autowired
	private FilialeService filialeService;

	@Autowired
	private ModelloService modelloService;

	@Autowired
	private ModelloValidator modelloValidator;

	@GetMapping("filiale/{id}/modello")
	public String getModello(@PathVariable("id") Long id, Model model)	{
		model.addAttribute("modello", new Modello());
		Filiale filiale = filialeService.findById(id);
		model.addAttribute("filiale",filiale);
		return "modelloForm.html";
	}

	@PostMapping("filiale/modello/{idFiliale}")
	public String addFiliale(@Valid @ModelAttribute("modello") Modello modello,BindingResult bindingResults , @PathVariable("idFiliale") Long id,Model model)	{
		Filiale filiale = filialeService.findById(id);

		if(!bindingResults.hasErrors())	{
			filiale.getModelli().add(modello);
			filialeService.save(filiale);
			model.addAttribute("filiale", filiale);
			model.addAttribute("modelli", filiale.getModelli());
			return "filiale.html";
		}

		model.addAttribute("filiale", filiale);
		model.addAttribute("modello", modello);
		return "modelloForm.html";
	}


	@GetMapping("/modello/{id}")
	public String getFiliale(@PathVariable("id") Long id, Model model)	{
		Modello modello = modelloService.findById(id);
		model.addAttribute("modello",modello);
		model.addAttribute("versioni", modello.getVersioni());
		return "modello.html";
	}

	@GetMapping("/userModello/{id}")
	public String getModelloUser(@PathVariable("id") Long id, Model model)	{
		Modello modello = modelloService.findById(id);
		model.addAttribute("modello",modello);
		model.addAttribute("versioni", modello.getVersioni());
		return "userModello.html";
	}

	@GetMapping("/modello/remove/{idModello}/{idFiliale}")
	public String removeModello(@PathVariable("idModello") Long idB, @PathVariable("idFiliale") Long idC , Model model) {
		System.out.println("\n\n\n\n\n\n\n BBBBBBBBBBBBBBBB");
		Filiale filiale = filialeService.findById(idC);
		Modello modello = modelloService.findById(idB);
		filiale.getModelli().remove(modello);
		filialeService.save(filiale);
		modelloService.deleteById(idB);
		model.addAttribute("filiale", filiale);
		model.addAttribute("modelli", filiale.getModelli());
		return "filiale.html";
	}
	
	@GetMapping("filiale/modifyModello/{idModello}/{idFiliale}")
	public String getModifyModelloForm(@PathVariable("idModello") Long id,@PathVariable("idFiliale") Long idc , Model model)	{
		model.addAttribute("oldModello", modelloService.findById(id));
		model.addAttribute("filiale", filialeService.findById(idc));
		model.addAttribute("modello", new Modello());
		return "modelloModifyForm.html";
	}
	
	@PostMapping("filiale/modifyModello/{idModello}/{idFiliale}")
	public String getModifyModello(@Valid @ModelAttribute("modello") Modello modello,BindingResult bindingResults , @PathVariable("idModello") Long id, @PathVariable("idFiliale") Long idc,Model model)	{
		Modello oldModello = modelloService.findById(id);
		Filiale filiale = filialeService.findById(idc);
		
		if(!bindingResults.hasErrors())	{
			oldModello.setNome(modello.getNome());
			oldModello.setDescrizione(modello.getDescrizione());
			modelloService.save(oldModello);
			filialeService.save(filiale);
			model.addAttribute("filiale", filiale);
			model.addAttribute("modelli", filiale.getModelli());
			return "filiale.html";
		}

		model.addAttribute("filiale", filiale);
		model.addAttribute("modello", oldModello);
		return "modelloModifyForm.html";
	}
	
	@GetMapping("/models")
	public String getBuffets(Model model)	{
		 List<Modello> modelli = modelloService.findAll();
		 model.addAttribute("modelli", modelli);
		 return "userModelli.html";
	}

}
