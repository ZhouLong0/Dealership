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

import it.uniroma3.siw.spring.controller.validator.ModelloValidator;
import it.uniroma3.siw.spring.model.Filiale;
import it.uniroma3.siw.spring.model.Modello;
import it.uniroma3.siw.spring.service.FilialeService;
import it.uniroma3.siw.spring.service.ModelloService;

@Controller
public class ModelloController {
	@Autowired
	private FilialeService ps;

	@Autowired
	private ModelloService bs;

	@Autowired
	private ModelloValidator bv;

	@GetMapping("filiale/{id}/modello")
	public String getModello(@PathVariable("id") Long id, Model model)	{
		model.addAttribute("modello", new Modello());
		Filiale filiale = ps.findById(id);
		model.addAttribute("filiale",filiale);
		return "modelloForm.html";
	}

	@PostMapping("filiale/modello/{idFiliale}")
	public String addFiliale(@Valid @ModelAttribute("modello") Modello modello,BindingResult bindingResults , @PathVariable("idFiliale") Long id,Model model)	{
		Filiale filiale = ps.findById(id);

		if(!bindingResults.hasErrors())	{
			filiale.getModelli().add(modello);
			ps.save(filiale);
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
		Modello modello = bs.findById(id);
		model.addAttribute("modello",modello);
		model.addAttribute("versioni", modello.getVersioni());
		return "modello.html";
	}

	@GetMapping("/userModello/{id}")
	public String getModelloUser(@PathVariable("id") Long id, Model model)	{
		Modello modello = bs.findById(id);
		model.addAttribute("modello",modello);
		model.addAttribute("versioni", modello.getVersioni());
		return "userModello.html";
	}

	@GetMapping("/modello/remove/{idModello}/{idFiliale}")
	public String removeModello(@PathVariable("idModello") Long idB, @PathVariable("idFiliale") Long idC , Model model) {
		System.out.println("\n\n\n\n\n\n\n BBBBBBBBBBBBBBBB");
		Filiale filiale = ps.findById(idC);
		Modello modello = bs.findById(idB);
		filiale.getModelli().remove(modello);
		ps.save(filiale);
		bs.deleteById(idB);
		model.addAttribute("filiale", filiale);
		model.addAttribute("modelli", filiale.getModelli());
		return "filiale.html";
	}
	
	@GetMapping("filiale/modifyModello/{idModello}/{idFiliale}")
	public String getModifyModelloForm(@PathVariable("idModello") Long id,@PathVariable("idFiliale") Long idc , Model model)	{
		model.addAttribute("oldModello", bs.findById(id));
		model.addAttribute("filiale", ps.findById(idc));
		model.addAttribute("modello", new Modello());
		return "modelloModifyForm.html";
	}
	
	@PostMapping("filiale/modifyModello/{idModello}/{idFiliale}")
	public String getModifyModello(@Valid @ModelAttribute("modello") Modello modello,BindingResult bindingResults , @PathVariable("idModello") Long id, @PathVariable("idFiliale") Long idc,Model model)	{
		Modello oldModello = bs.findById(id);
		Filiale filiale = ps.findById(idc);
		
		if(!bindingResults.hasErrors())	{
			oldModello.setNome(modello.getNome());
			oldModello.setDescrizione(modello.getDescrizione());
			bs.save(oldModello);
			ps.save(filiale);
			model.addAttribute("filiale", filiale);
			model.addAttribute("modelli", filiale.getModelli());
			return "filiale.html";
		}

		model.addAttribute("filiale", filiale);
		model.addAttribute("modello", oldModello);
		return "modelloModifyForm.html";
	}

}
