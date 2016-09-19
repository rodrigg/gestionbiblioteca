package com.ipartek.formacion.dao.mapas;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ipartek.formacion.dao.persistence.Alumno;

public class AlumnoMapper implements RowMapper<Alumno> {

  @Override
  public Alumno mapRow(ResultSet rs, int arg1) throws SQLException {
    Alumno alumno = new Alumno();
    alumno.setCodigo(rs.getInt("codAlumno"));
    alumno.setNombre(rs.getString("nombre"));
    alumno.setApellido(rs.getString("apellidos"));
    alumno.setfNacimiento(rs.getDate("fNacimiento"));
    alumno.setEmail(rs.getString("email"));
    return alumno;
  }

}
