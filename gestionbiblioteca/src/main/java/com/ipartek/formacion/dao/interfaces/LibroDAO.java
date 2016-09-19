package com.ipartek.formacion.dao.interfaces;

import java.util.List;

import com.ipartek.formacion.dao.persistence.Libro;

public interface LibroDAO extends DAOSetter {
  public List<Libro> getAll();

  public Libro getById(int id);

  public Libro create(Libro curso);

  public Libro update(Libro curso);

  public void delete(int id);
}
