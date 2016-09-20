package com.ipartek.formacion.dao.persistence;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Ejemplar {
	private int id;
	@NotNull
	@Size(min = 1)
	private String nombre;
	private String editorial;

	private int npaginas;
	private Libro libro;

	/**
	* 
	*/
	public Ejemplar() {
		super();
		setId(-1);
		setEditorial("");
		setNpaginas(-1);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public int getNpaginas() {
		return npaginas;
	}

	public void setNpaginas(int npaginas) {
		this.npaginas = npaginas;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	@Override
	public String toString() {
		return "Ejemplar [id=" + id + ", nombre=" + nombre + ", editorial=" + editorial + ", npaginas=" + npaginas
				+ ", libro=" + libro + "]";
	}

}
