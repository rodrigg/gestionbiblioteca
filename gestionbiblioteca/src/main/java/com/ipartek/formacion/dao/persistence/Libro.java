package com.ipartek.formacion.dao.persistence;

import javax.validation.constraints.Size;

public class Libro {
	private int id;
	@Size(min = 3)
	private String titulo;
	private String autor;
	private String isbn;

	/**
	* 
	*/
	public Libro() {
		super();
		setId(-1);
		setTitulo("");
		setAutor("");
		setIsbn("");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Override
	public String toString() {
		return "Libro [id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", isbn=" + isbn + "]";
	}

}
