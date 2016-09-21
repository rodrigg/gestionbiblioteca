package com.ipartek.formacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.dao.interfaces.UsuarioDAO;
import com.ipartek.formacion.dao.persistence.Usuario;
import com.ipartek.formacion.service.interfaces.UsuarioService;

@Service
public class UsuarioServiceImp implements UsuarioService {
	@Autowired
	UsuarioDAO uDAO;

	@Override
	public List<Usuario> getAll() {
		List<Usuario> usuarios = null;
		usuarios = uDAO.getAll();
		return usuarios;
	}

	@Override
	public Usuario getById(int id) {

		return uDAO.getById(id);
	}

	@Override
	public Usuario update(Usuario usuario) {

		return uDAO.update(usuario);
	}

	@Override
	public void delete(int id) {
		uDAO.delete(id);

	}

	@Override
	public Usuario create(Usuario usuario) {

		return uDAO.create(usuario);
	}

}
