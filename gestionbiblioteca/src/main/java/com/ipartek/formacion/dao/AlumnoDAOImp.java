package com.ipartek.formacion.dao;

import java.sql.Date;
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

import com.ipartek.formacion.dao.interfaces.AlumnoDAO;
import com.ipartek.formacion.dao.mapas.AlumnoMapper;
import com.ipartek.formacion.dao.persistence.Alumno;

@Repository("alumnoDAOImp")
public class AlumnoDAOImp implements AlumnoDAO {

	private static final Logger logger = LoggerFactory
			.getLogger(AlumnoDAOImp.class);
	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbctemplate;
	private SimpleJdbcCall jdbcCall;

	@Override
	public List<Alumno> getAll() {
		List<Alumno> alumnos = new ArrayList<Alumno>();
		final String SQL = "SELECT codAlumno, nombre, apellidos, fNacimiento, email FROM alumno;";
		try {
			alumnos = jdbctemplate.query(SQL, new AlumnoMapper());
		} catch (EmptyResultDataAccessException e) {
			alumnos = new ArrayList<Alumno>();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return alumnos;
	}

	@Override
	public Alumno getById(int id) {
		Alumno alumno = null;
		final String SQL = "SELECT codAlumno, nombre, apellidos, fNacimiento,email FROM alumno WHERE codAlumno = ?;";
		try {
			alumno = jdbctemplate.queryForObject(SQL, new Object[] { id },
					new AlumnoMapper());
		} catch (EmptyResultDataAccessException e) {
			alumno = new Alumno();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return alumno;
	}

	@Override
	public Alumno create(Alumno alumno) {

		/*
		 * insertAlumno --> Nombre del procedimiento almacenado
		 */
		jdbcCall.withProcedureName("insertAlumno");
		/*
		 * SqlParameterSource (tipo Map) guarda los paramentros necesarios para
		 * el procedimiento
		 */
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("nombre", alumno.getNombre())
				.addValue("apellidos", alumno.getApellido())
				.addValue("dni", "s").addValue("fecha", new Date(alumno.getfNacimiento().getTime()))
				.addValue("email",alumno.getEmail()).addValue("telefono", "1")
				.addValue("codGenero", 1);

		Map<String, Object> out = jdbcCall.execute(in);
		/*
		 * Recogemos el parametro OUT del procedimiento
		 */

		alumno.setCodigo((Integer) out.get("codalumno"));
		return alumno;

	}

	@Override
	public Alumno update(Alumno alumno) {
		final String SQL = "UPDATE alumno SET nombre = ?, apellidos = ?, fNacimiento = ?, email = ?  WHERE codAlumno = ?;";
		jdbctemplate.update(
				SQL,
				new Object[] { alumno.getNombre(), alumno.getApellido(),new Date(alumno.getfNacimiento().getTime()), alumno.getEmail(),
						alumno.getCodigo() });
		return alumno;
	}

	@Override
	public void delete(int id) {
		final String SQL = "DELETE FROM alumno WHERE codAlumno = ?;";
		jdbctemplate.update(SQL, new Object[] { id });

	}

	@Autowired
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbctemplate = new JdbcTemplate(dataSource);
		this.jdbcCall = new SimpleJdbcCall(dataSource);
	}

}
