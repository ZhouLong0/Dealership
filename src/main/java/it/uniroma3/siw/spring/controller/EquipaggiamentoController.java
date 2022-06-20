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

	@GetMapping("piatto/ingrediente/{idPiatto}")
	public String getBuffet(@PathVariable("idPiatto") Long id, Model model)	{
		model.addAttribute("ingrediente", new Equipaggiamento());
		Versione versione = pis.findById(id);
		model.addAttribute("piatto", versione);
		return "ingredienteForm.html";
	}
	
	@PostMapping("piatto/ingrediente/{idPiatto}")
	public String addPiatto(@ModelAttribute("ingrediente") Equipaggiamento equipaggiamento, @PathVariable("idPiatto") Long id, Model model)	{
		Versione piatto1 = pis.findById(id);
		piatto1.addIngredienti(equipaggiamento);
		pis.save(piatto1);
		model.addAttribute("piatto", piatto1);
		model.addAttribute("ingredienti", piatto1.getIngredienti());
		return "piatto.html";
	}
}
