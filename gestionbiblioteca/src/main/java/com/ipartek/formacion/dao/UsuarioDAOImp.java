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

import com.ipartek.formacion.dao.interfaces.UsuarioDAO;
import com.ipartek.formacion.dao.mapas.UsuarioMapper;
import com.ipartek.formacion.dao.persistence.Usuario;

public class UsuarioDAOImp implements UsuarioDAO {
	private static final Logger logger = LoggerFactory.getLogger(UsuarioDAOImp.class);
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
	public List<Usuario> getAll() {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		final String SQL = "SELECT u.id as id,nombre,apellidos,password,fnacimiento,email,e.id as ejemplar_id,editorial,npaginas,l.id as id_libro,titulo,autor,isbn FROM usuarios u left join ejemplares e on(u.ejemplar_id=e.id) left join libros l on(l.id=e.libro_id)";

		try {
			usuarios = jdbctemplate.query(SQL, new UsuarioMapper());
		} catch (EmptyResultDataAccessException e) {
			usuarios = new ArrayList<Usuario>();
		} catch (Exception e) {
			logger.error(e.getMessage());
			System.out.println(e.getMessage());
		}
		return usuarios;
	}

	@Override
	public Usuario getById(int id) {
		Usuario usuario = null;
		final String SQL = "SELECT u.id as id,nombre,apellidos,password,fnacimiento,email,e.id as ejemplar_id,editorial,npaginas,l.id as id_libro,titulo,autor,isbn FROM usuarios u left join ejemplares e on(u.ejemplar_id=e.id) left join libros l on(l.id=e.libro_id) WHERE u.id=?";

		try {
			usuario = jdbctemplate.queryForObject(SQL, new Object[] { id }, new UsuarioMapper());
		} catch (EmptyResultDataAccessException e) {
			usuario = new Usuario();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return usuario;
	}

	@Override
	public Usuario create(Usuario usuario) {
		System.out.println(usuario.toString() + "dao");
		jdbcCall.withProcedureName("insertusuario");
		SqlParameterSource in = new MapSqlParameterSource().addValue("nombre", usuario.getNombre())
				.addValue("apellidos", usuario.getApellidos()).addValue("password", usuario.getPassword())
				.addValue("fnacimiento", usuario.getFnacimiento()).addValue("email", usuario.getEmail());

		Map<String, Object> out = jdbcCall.execute(in);
		System.out.println(usuario.toString() + "despues");
		usuario.setId((Integer) out.get("id_usuario"));
		return usuario;
	}

	@Override
	public Usuario update(Usuario usuario) {

		final String SQL = "UPDATE usuarios SET nombre = ?,apellidos=?,password=?,fnacimiento=?,email=? WHERE id = ?;";
		jdbctemplate.update(SQL, new Object[] { usuario.getNombre(), usuario.getApellidos(), usuario.getPassword(),
				usuario.getFnacimiento(), usuario.getEmail(), usuario.getId() });
		return usuario;
	}

	@Override
	public void delete(int id) {
		final String SQL = "DELETE FROM usuarios WHERE id = ?;";
		jdbctemplate.update(SQL, new Object[] { id });

	}

	@Override
	public void devolver(int id) {
		final String SQL = "UPDATE usuarios SET ejemplar_id=0 WHERE id = ?;";
		jdbctemplate.update(SQL, new Object[] { id });
	}

	@Override
	public void alquilar(Usuario usuario) {
		final String SQL = "UPDATE usuarios SET ejemplar_id=? WHERE id = ?;";
		jdbctemplate.update(SQL, new Object[] { usuario.getEjemplar().getId(), usuario.getId() });

	}

}
