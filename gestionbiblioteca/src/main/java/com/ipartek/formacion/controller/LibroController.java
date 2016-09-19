package com.ipartek.formacion.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.ipartek.formacion.dao.persistence.Libro;
import com.ipartek.formacion.service.interfaces.LibroService;


@Controller
@RequestMapping(value = "/libros")
public class LibroController extends MultiActionController {
	
	// @Resource(name = "libroServiceImp")
	@Autowired
	private LibroService as = null;
	private ModelAndView mav = null;
	@InitBinder
	private void initBinder(WebDataBinder binder){
		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getAll() {
		mav = new ModelAndView("/libros/listado");
		List<Libro> libros = as.getAll();
		System.out.println(libros.get(0).toString());
		mav.addObject("listado_libros", libros);
		return mav;
	}
	@RequestMapping(value="/addlibro")
	public String addAlumno(Model model) {
		model.addAttribute("libro", new Libro());
		return "libros/libro";
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView getById(@PathVariable("id") int id) {
		mav = new ModelAndView("/libros/libro");
		Libro libro = as.getById(id);
		System.out.println(id);
		mav.addObject("libro", libro);

		return mav;
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.POST,
			RequestMethod.DELETE })
	public ModelAndView delete(@PathVariable("id") int id) {
		mav = new ModelAndView("/libros/listado");
		as.delete(id);
		return mav;
	}

	
	@RequestMapping(value="/save",method = RequestMethod.POST)
	public String saveAlumno(@ModelAttribute("libro")  Libro libro,BindingResult bindingResult,Model model){
		if(bindingResult.hasErrors()){
			logger.info("El libro tiene errores");
			return "libros/libro";
		}
		if(libro.getId()>0){
			as.update(libro);
		}
		else{
	
		}
		return "redirect:/libros";
	}

}

