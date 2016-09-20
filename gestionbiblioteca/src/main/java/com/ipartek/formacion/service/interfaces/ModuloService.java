package com.ipartek.formacion.service.interfaces;

import java.util.List;

import com.ipartek.formacion.dao.persistence.Ejemplar;

public interface ModuloService {
	public List<Ejemplar> getAll();

	public Ejemplar getById(int id);

	public Ejemplar update(Ejemplar modulo);

	public void delete(int id);
	
	public Ejemplar create(Ejemplar modulo);
}
