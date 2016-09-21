package com.ipartek.formacion.dao.persistence;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author Curso
 * 
 */

public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Min(value = 0)
	private int id;
	@NotNull
	private String nombre;
	@NotNull
	private String apellidos;
	private String password;
	private Date fnacimiento;
	private String email;

	private Ejemplar ejemplar;

	/**
	 * Constructor de la clase Alumno
	 */
	public Usuario() {
		super();
		setId(-1);
		setNombre("");
		setApellidos("");
		setFnacimiento(new Date());
		setPassword("");
		setEmail("");
	}

	/**
	 * 
	 * @return
	 */

	/**
	 * 
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * 
	 * @return
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * 
	 * @param apellido
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getFnacimiento() {
		return fnacimiento;
	}

	public void setFnacimiento(Date fnacimiento) {
		this.fnacimiento = fnacimiento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Ejemplar getEjemplar() {
		return ejemplar;
	}

	public void setEjemplar(Ejemplar ejemplar) {
		this.ejemplar = ejemplar;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", password=" + password
				+ ", fnacimiento=" + fnacimiento + ", email=" + email + ", ejemplar=" + ejemplar + "]";
	}

}
