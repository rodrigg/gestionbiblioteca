package com.ipartek.formacion.dao.mapas;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ipartek.formacion.dao.persistence.Ejemplar;
import com.ipartek.formacion.dao.persistence.Libro;
import com.ipartek.formacion.dao.persistence.Usuario;

public class UsuarioMapper implements RowMapper<Usuario> {
	final String SQL = "SELECT u.id as id,nombre,apellidos,password,fnacimiento,email,e.id as ejemplar_id,editorial,npaginas,l.id as id_libro,titulo,autor,isbn"
			+ "FROM usuarios u inner join ejemplares e on(u.ejemplar_id=e.id)"
			+ " inner join libros l on(l.id=e.libro_id)";

	@Override
	public Usuario mapRow(ResultSet rs, int arg1) throws SQLException {
		Usuario usuario = null;
		usuario = new Usuario();
		usuario.setId(rs.getInt("id"));
		usuario.setNombre(rs.getString("nombre"));
		usuario.setApellidos(rs.getString("apellidos"));
		usuario.setPassword(rs.getString("password"));
		usuario.setFnacimiento(rs.getDate("fnacimiento"));
		usuario.setEmail(rs.getString("email"));
		Ejemplar ejemplar = new Ejemplar();
		ejemplar.setId(rs.getInt("ejemplar_id"));
		ejemplar.setEditorial(rs.getString("editorial"));
		ejemplar.setNpaginas(rs.getInt("npaginas"));

		Libro libro = null;
		libro = new Libro();
		libro.setId(rs.getInt("id_libro"));
		libro.setTitulo(rs.getString("titulo"));
		libro.setAutor(rs.getString("autor"));
		libro.setIsbn(rs.getString("isbn"));
		ejemplar.setLibro(libro);

		usuario.setEjemplar(ejemplar);
		return usuario;
	}

}
