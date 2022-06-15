package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.spring.model.Buffet;
import it.uniroma3.siw.spring.model.Ingrediente;
import it.uniroma3.siw.spring.model.Piatto;
import it.uniroma3.siw.spring.service.PiattoService;

@Controller
public class IngredienteController {

	@Autowired
	private PiattoService pis;

	@GetMapping("piatto/ingrediente/{idPiatto}")
	public String getBuffet(@PathVariable("idPiatto") Long id, Model model)	{
		model.addAttribute("ingrediente", new Ingrediente());
		Piatto piatto = pis.findById(id);
		model.addAttribute("piatto", piatto);
		return "ingredienteForm.html";
	}
	
	@PostMapping("piatto/ingrediente/{idPiatto}")
	public String addPiatto(@ModelAttribute("ingrediente") Ingrediente ingrediente, @PathVariable("idPiatto") Long id, Model model)	{
		Piatto piatto1 = pis.findById(id);
		piatto1.addIngredienti(ingrediente);
		pis.save(piatto1);
		model.addAttribute("piatto", piatto1);
		model.addAttribute("ingredienti", piatto1.getIngredienti());
		return "piatto.html";
	}
}
