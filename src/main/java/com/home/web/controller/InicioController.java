package com.home.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.home.web.model.Alumno;
import com.home.web.model.ListaAlumnos;

//@RequestMapping("web")
@Controller
public class InicioController {
	
	
	@Autowired
	List<Alumno> listaAlumnos;
	
	
	@GetMapping({"/","inicio"})
	public ModelAndView inicio() {
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("inicio");
		
		return mav;
	}
	
	@GetMapping("pantalla1")
	public ModelAndView pantalla1() {
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("titulo","PANTALLA 1 CONDEMOR");
		
		
		mav.addObject("listaAlumnos",listaAlumnos);
		
		
		mav.setViewName("pantalla1");
		
		return mav;
	}

}
