package com.ipartek.formacion.service.interfaces;

import java.util.List;

import com.ipartek.formacion.dao.persistence.Libro;


/**
 *
 * @author va00
 *
 */
public interface LibroService {
	/**
	 *
	 * @return
	 */
	public List<Libro> getAll();

	public Libro getById(int id);

	public Libro update(Libro libro);

	public void delete(int id);
	public Libro create(Libro libro);
}
