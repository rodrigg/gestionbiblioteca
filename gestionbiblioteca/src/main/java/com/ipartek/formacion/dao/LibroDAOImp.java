package com.ipartek.formacion.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.ipartek.formacion.dao.interfaces.LibroDAO;
import com.ipartek.formacion.dao.mapas.LibroMapper;
import com.ipartek.formacion.dao.persistence.Libro;

@Repository
public class LibroDAOImp implements LibroDAO {
	private static final Logger logger = LoggerFactory.getLogger(LibroDAOImp.class);
	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbctemplate;
	private SimpleJdbcCall jdbcCall;

	@Autowired
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbctemplate = new JdbcTemplate(dataSource);
		this.jdbcCall = new SimpleJdbcCall(dataSource);
	}

	@Override
	public List<Libro> getAll() {
		List<Libro> libros = new ArrayList<Libro>();
		final String SQL = "SELECT id,titulo, autor,isbn FROM libros;";
		try {
			libros = jdbctemplate.query(SQL, new LibroMapper());
		} catch (EmptyResultDataAccessException e) {
			libros = new ArrayList<Libro>();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return libros;
	}

	@Override
	public Libro getById(int id) {
		Libro libro = null;
		final String SQL = "SELECT id,titulo, autor,isbn FROM libros WHERE id = ?;";
		try {
			libro = jdbctemplate.queryForObject(SQL, new Object[] { id }, new LibroMapper());
		} catch (EmptyResultDataAccessException e) {
			libro = new Libro();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return libro;
	}

	@Override
	public Libro create(Libro libro) {
		jdbcCall.withProcedureName("insertlibro");
		SqlParameterSource in = new MapSqlParameterSource().addValue("titulo", libro.getTitulo())
				.addValue("autor", libro.getAutor()).addValue("isbn", libro.getIsbn());
		Map<String, Object> out = jdbcCall.execute(in);
		libro.setId((Integer) out.get("id_libro"));
		return libro;
	}

	@Override
	public Libro update(Libro libro) {
		final String SQL = "UPDATE libros SET titulo = ?,autor=?,isbn=? WHERE id = ?;";
		jdbctemplate.update(SQL, new Object[] { libro.getTitulo(), libro.getAutor(), libro.getIsbn(), libro.getId() });
		return libro;
	}

	@Override
	public void delete(int id) {
		final String SQL = "DELETE FROM libros WHERE id = ?;";
		jdbctemplate.update(SQL, id);
	}

}
