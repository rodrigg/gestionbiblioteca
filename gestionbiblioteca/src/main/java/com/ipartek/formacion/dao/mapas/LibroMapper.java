package com.ipartek.formacion.dao.mapas;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ipartek.formacion.dao.persistence.Libro;

public class LibroMapper implements RowMapper<Libro> {

	@Override
	public Libro mapRow(ResultSet rs, int arg1) throws SQLException {
		Libro curso = new Libro();
		curso.setId(rs.getInt("id"));
		curso.setTitulo(rs.getString("titulo"));
		curso.setAutor(rs.getString("autor"));
		curso.setIsbn(rs.getString("isbn"));
		return curso;
	}

}
