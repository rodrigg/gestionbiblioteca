package com.ipartek.formacion.dao.mapas;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ipartek.formacion.dao.persistence.Ejemplar;
import com.ipartek.formacion.dao.persistence.Libro;

public class EjemplarMapper implements RowMapper<Ejemplar> {

	@Override
	public Ejemplar mapRow(ResultSet rs, int arg1) throws SQLException {
		Ejemplar ejemplar = new Ejemplar();
		ejemplar.setId(rs.getInt("id"));
		ejemplar.setEditorial(rs.getString("editorial"));
		ejemplar.setNpaginas(rs.getInt("npaginas"));
		Libro libro = null;
		libro = new Libro();
		libro.setId(rs.getInt("libro_id"));
		libro.setTitulo(rs.getString("titulo"));
		libro.setAutor(rs.getString("autor"));
		libro.setIsbn(rs.getString("isbn"));
		ejemplar.setLibro(libro);

		return ejemplar;
	}

}
