package com.ipartek.formacion.service.interfaces;

import java.util.List;

import com.ipartek.formacion.dao.persistence.Usuario;

public interface UsuarioService {
	public List<Usuario> getAll();

	public Usuario getById(int id);

	public Usuario update(Usuario usuario);

	public void delete(int id);

	public Usuario create(Usuario usuario);

	public void devolver(int id);

	public void alquilar(Usuario usuario);
}
