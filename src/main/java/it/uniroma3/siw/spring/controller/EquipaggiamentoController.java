package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.spring.model.Equipaggiamento;
import it.uniroma3.siw.spring.model.Versione;
import it.uniroma3.siw.spring.service.VersioneService;

@Controller
public class EquipaggiamentoController {

	@Autowired
	private VersioneService versioneService;

	@GetMapping("versione/equipaggiamento/{idVersione}")
	public String getModello(@PathVariable("idVersione") Long id, Model model)	{
		model.addAttribute("equipaggiamento", new Equipaggiamento());
		Versione versione = versioneService.findById(id);
		model.addAttribute("versione", versione);
		return "equipaggiamentoForm.html";
	}
	
	@PostMapping("versione/equipaggiamento/{idVersione}")
	public String addVersione(@ModelAttribute("equipaggiamento") Equipaggiamento equipaggiamento, @PathVariable("idVersione") Long id, Model model)	{
		Versione versione = versioneService.findById(id);
		versione.addEquipaggiamento(equipaggiamento);
		versioneService.save(versione);
		model.addAttribute("versione", versione);
		model.addAttribute("equipaggiamenti", versione.getEquipaggiamenti());
		return "versione.html";
	}
}
