package com.ipartek.formacion.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.ipartek.formacion.dao.interfaces.EjemplarDAO;
import com.ipartek.formacion.dao.mapas.EjemplarMapper;
import com.ipartek.formacion.dao.persistence.Ejemplar;

public class EjemplarDAOImp implements EjemplarDAO {
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
	public List<Ejemplar> getAll() {
		List<Ejemplar> ejemplares = new ArrayList<Ejemplar>();
		String sql = "SELECT e.id as id,editorial,npaginas,titulo,autor,isbn,libro_id FROM ejemplares e inner join libros l on (e.libro_id=l.id)";
		try {
			ejemplares = jdbctemplate.query(sql, new EjemplarMapper());
		} catch (EmptyResultDataAccessException e) {
			ejemplares = new ArrayList<Ejemplar>();
		} catch (Exception e) {

		}
		return ejemplares;
	}

	@Override
	public List<Ejemplar> getEjemplaresNoAlquilados() {
		List<Ejemplar> ejemplares = new ArrayList<Ejemplar>();
		String SQL = "SELECT e.id as id,editorial,npaginas,titulo,autor,isbn,libro_id FROM ejemplares e INNER JOIN libros l on e.libro_id=l.id WHERE e.id not in(SELECT ejemplar_id FROM usuarios WHERE ejemplar_id!=0)";
		try {
			ejemplares = jdbctemplate.query(SQL, new EjemplarMapper());
		} catch (EmptyResultDataAccessException e) {
			ejemplares = new ArrayList<Ejemplar>();
		} catch (Exception e) {

		}
		return ejemplares;
	}

	@Override
	public Ejemplar getById(int id) {
		Ejemplar ejemplar = null;
		final String SQL = "SELECT e.id as id,editorial,npaginas,titulo,autor,isbn,libro_id FROM ejemplares e inner join libros l on (e.libro_id=l.id) WHERE e.id = ?;";
		try {
			ejemplar = jdbctemplate.queryForObject(SQL, new Object[] { id }, new EjemplarMapper());
		} catch (EmptyResultDataAccessException e) {
			ejemplar = new Ejemplar();
		} catch (Exception e) {

		}
		return ejemplar;
	}

	@Override
	public Ejemplar create(Ejemplar ejemplar) {
		jdbcCall.withProcedureName("insertejemplar");
		SqlParameterSource in = new MapSqlParameterSource().addValue("editorial", ejemplar.getEditorial())
				.addValue("npaginas", ejemplar.getNpaginas()).addValue("libro_id", ejemplar.getIdLibro());
		Map<String, Object> out = jdbcCall.execute(in);
		ejemplar.setId((Integer) out.get("id_ejemplar"));
		return ejemplar;
	}

	@Override
	public Ejemplar update(Ejemplar ejemplar) {
		final String SQL = "UPDATE ejemplares SET editorial = ?,npaginas=?,libro_id=? WHERE id = ?;";
		jdbctemplate.update(SQL, new Object[] { ejemplar.getEditorial(), ejemplar.getNpaginas(), ejemplar.getIdLibro(),
				ejemplar.getId() });
		return ejemplar;
	}

	@Override
	public void delete(int id) {
		final String SQL = "DELETE FROM ejemplares WHERE id = ?;";
		jdbctemplate.update(SQL, id);

	}

}
