package com.ipartek.formacion.dao.interfaces;

import java.util.List;

import com.ipartek.formacion.dao.persistence.Ejemplar;

public interface EjemplarDAO extends DAOSetter {
	public List<Ejemplar> getAll();

	public Ejemplar getById(int id);

	public Ejemplar create(Ejemplar modulo);

	public Ejemplar update(Ejemplar modulo);

	public void delete(int id);

	public List<Ejemplar> getEjemplaresNoAlquilados();
}
