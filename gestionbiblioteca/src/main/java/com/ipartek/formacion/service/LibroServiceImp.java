package com.ipartek.formacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.dao.interfaces.UsuarioDAO;
import com.ipartek.formacion.dao.interfaces.LibroDAO;

import com.ipartek.formacion.dao.persistence.Libro;

import com.ipartek.formacion.service.interfaces.LibroService;
@Service
public class LibroServiceImp implements LibroService {
	@Autowired
	LibroDAO libroDAO;
	@Override
	public List<Libro> getAll() {
		List<Libro> libros =null;
		libros=libroDAO.getAll();
		return libros;
	}

	@Override
	public Libro getById(int id) {
	
		return libroDAO.getById(id);
	}

	@Override
	public Libro update(Libro libro) {
		
		return libroDAO.update(libro);
	}

	@Override
	public void delete(int id) {
		libroDAO.delete(id);
		
	}

	@Override
	public Libro create(Libro libro) {
		
		return libroDAO.create(libro);
	}

	
}
