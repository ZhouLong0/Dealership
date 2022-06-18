 package it.uniroma3.siw.spring.controller.validator;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Filiale;
import it.uniroma3.siw.spring.service.ChefService;

@Component
public class ChefValidator implements Validator{

	@Autowired
	private ChefService personaService;
	
	@Override
	public boolean supports(Class<?> pClass)	{
		return Filiale.class.equals(pClass);
	}
	
	@Override
	public void validate(Object target, Errors errors)	{
		if(this.personaService.alreadyExists((Filiale) target))	{
			errors.reject("persona.duplicato");
		}
	}
	
	
}
