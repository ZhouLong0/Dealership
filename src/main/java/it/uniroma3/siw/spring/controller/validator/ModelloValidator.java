package it.uniroma3.siw.spring.controller.validator;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


import it.uniroma3.siw.spring.model.Modello;
import it.uniroma3.siw.spring.service.ModelloService;

@Component
public class ModelloValidator implements Validator{

	@Autowired
	private ModelloService modelloService;
	
	@Override
	public boolean supports(Class<?> pClass)	{
		return Modello.class.equals(pClass);
	}
	
	@Override
	public void validate(Object target, Errors errors)	{
		;
	}
	
	
}
