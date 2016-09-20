package com.ipartek.formacion.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.ipartek.formacion.dao.persistence.Ejemplar;
import com.ipartek.formacion.service.interfaces.EjemplarService;
import com.ipartek.formacion.service.interfaces.LibroService;

@Controller
@RequestMapping(value = "/ejemplares")
public class EjemplarController extends MultiActionController {

	// @Resource(name = "ejemplarServiceImp")
	@Autowired
	private EjemplarService as = null;
	@Autowired
	private LibroService lService = null;
	private ModelAndView mav = null;

	@InitBinder
	private void initBinder(WebDataBinder binder) {

	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getAll() {
		mav = new ModelAndView("/ejemplares/listado");
		List<Ejemplar> ejemplares = as.getAll();
		System.out.println(ejemplares.get(0).toString());
		mav.addObject("listado_ejemplares", ejemplares);
		return mav;
	}

	@RequestMapping(value = "/addejemplar")
	public String addAlumno(Model model) {
		model.addAttribute("ejemplar", new Ejemplar());
		return "ejemplares/ejemplar";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView getById(@PathVariable("id") int id) {
		mav = new ModelAndView("/ejemplares/ejemplar");
		Ejemplar ejemplar = as.getById(id);
		System.out.println(id);
		mav.addObject("ejemplar", ejemplar);

		return mav;
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.POST, RequestMethod.DELETE })
	public ModelAndView delete(@PathVariable("id") int id) {
		mav = new ModelAndView("/ejemplars/listado");
		as.delete(id);
		return mav;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveAlumno(@ModelAttribute("ejemplar") @Valid Ejemplar ejemplar, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			logger.info("El ejemplar tiene errores");
			return "ejemplares/ejemplar";
		}
		if (ejemplar.getId() > 0) {
			as.update(ejemplar);
		} else {
			as.create(ejemplar);
		}
		return "redirect:/ejemplares";
	}

}
