package com.ipartek.formacion.controller;

import java.util.List;

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

import com.ipartek.formacion.dao.persistence.Usuario;
import com.ipartek.formacion.service.interfaces.UsuarioService;

@Controller
@RequestMapping(value = "/usuarios")
public class UsuarioController extends MultiActionController {
	// @Resource(name = "usuarioServiceImp")
	@Autowired
	private UsuarioService as = null;
	private ModelAndView mav = null;

	@InitBinder
	private void initBinder(WebDataBinder binder) {

	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getAll() {
		mav = new ModelAndView("/usuarios/listado");
		List<Usuario> usuarios = as.getAll();
		System.out.println(usuarios.get(0).toString());
		mav.addObject("listado_usuarios", usuarios);
		return mav;
	}

	@RequestMapping(value = "/addusuario")
	public String addUsuario(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "usuarios/usuario";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView getById(@PathVariable("id") int id) {
		mav = new ModelAndView("/usuarios/usuario");
		Usuario usuario = as.getById(id);
		System.out.println(id);
		mav.addObject("usuario", usuario);

		return mav;
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.POST, RequestMethod.DELETE })
	public ModelAndView delete(@PathVariable("id") int id) {
		mav = new ModelAndView("/libros/listado");
		as.delete(id);
		return mav;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveAlumno(@ModelAttribute("usuario") Usuario usuario, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			logger.info("El libro tiene errores");
			return "usuarios/usuario";
		}
		if (usuario.getId() > 0) {
			System.out.println(usuario.toString());
			as.update(usuario);
		} else {
			as.create(usuario);
		}
		return "redirect:/usuarios";
	}

}
