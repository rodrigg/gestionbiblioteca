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

import com.ipartek.formacion.dao.persistence.Ejemplar;
import com.ipartek.formacion.dao.persistence.Usuario;
import com.ipartek.formacion.service.interfaces.EjemplarService;
import com.ipartek.formacion.service.interfaces.UsuarioService;

@Controller
@RequestMapping(value = "/usuarios")
public class UsuarioController extends MultiActionController {
	// @Resource(name = "usuarioServiceImp")
	@Autowired
	private UsuarioService as = null;
	@Autowired
	private EjemplarService eService;
	private ModelAndView mav = null;

	@InitBinder
	private void initBinder(WebDataBinder binder) {

	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getAll() {
		mav = new ModelAndView("/usuarios/listado");
		List<Usuario> usuarios = as.getAll();

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
		List<Ejemplar> ejemplares = eService.getEjemplaresNoAlquilados();
		System.out.println(ejemplares.get(0).toString());
		mav.addObject("ejemplares_noalquilados", ejemplares);
		System.out.println();
		mav.addObject("usuario", usuario);

		return mav;
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.POST, RequestMethod.DELETE })
	public ModelAndView delete(@PathVariable("id") int id) {
		mav = new ModelAndView("/libros/listado");
		as.delete(id);
		return mav;
	}

	@RequestMapping(value = "/{id}/devolver", method = RequestMethod.POST)
	public String devolver(@PathVariable("id") int id) {
		as.devolver(id);
		return "redirect:/usuarios/" + id;
	}

	@RequestMapping(value = "/{id}/alquilar/{id_ejemplar}", method = RequestMethod.POST)
	public String alquilar(@PathVariable("id") int id, @PathVariable("id_ejemplar") int id_ejemplar) {
		Usuario usuario = null;
		usuario = new Usuario();
		usuario.setId(id);
		Ejemplar ejemplar = new Ejemplar();
		ejemplar.setId(id_ejemplar);
		usuario.setEjemplar(ejemplar);
		as.alquilar(usuario);
		return "redirect:/usuarios/" + id;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveUsuario(@ModelAttribute("usuario") Usuario usuario, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			logger.info("El libro tiene errores");
			return "usuarios/usuario";
		}
		if (usuario.getId() > 0) {
			System.out.println(usuario.toString() + "update");
			as.update(usuario);
		} else {
			System.out.println(usuario.toString() + "create");
			as.create(usuario);
		}
		return "redirect:/usuarios";
	}

}
