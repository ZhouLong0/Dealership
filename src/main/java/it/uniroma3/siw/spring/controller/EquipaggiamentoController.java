package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.spring.model.Modello;
import it.uniroma3.siw.spring.model.Equipaggiamento;
import it.uniroma3.siw.spring.model.Versione;
import it.uniroma3.siw.spring.service.VersioneService;

@Controller
public class EquipaggiamentoController {

	@Autowired
	private VersioneService pis;

	@GetMapping("versione/ingrediente/{idPiatto}")
	public String getBuffet(@PathVariable("idVersione") Long id, Model model)	{
		model.addAttribute("ingrediente", new Equipaggiamento());
		Versione versione = pis.findById(id);
		model.addAttribute("versione", versione);
		return "ingredienteForm.html";
	}
	
	@PostMapping("versione/ingrediente/{idVersione}")
	public String addVersione(@ModelAttribute("ingrediente") Equipaggiamento equipaggiamento, @PathVariable("idVersione") Long id, Model model)	{
		Versione versione1 = pis.findById(id);
		versione1.addIngredienti(equipaggiamento);
		pis.save(versione1);
		model.addAttribute("versione", versione1);
		model.addAttribute("ingredienti", versione1.getIngredienti());
		return "versione.html";
	}
}
