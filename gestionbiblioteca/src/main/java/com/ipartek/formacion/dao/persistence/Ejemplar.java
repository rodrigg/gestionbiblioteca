package com.ipartek.formacion.dao.persistence;

public class Ejemplar {
	private int id;

	private String editorial;

	private int npaginas;
	private int idLibro;
	private Libro libro;

	/**
	* 
	*/
	public Ejemplar() {
		super();
		setId(-1);
		setEditorial("");
		setNpaginas(-1);
		setLibro(new Libro());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(int idLibro) {
		this.idLibro = idLibro;
	}

	@Override
	public String toString() {
		return "Ejemplar [id=" + id + ", editorial=" + editorial + ", npaginas=" + npaginas + ", libro=" + libro + "]";
	}

}
