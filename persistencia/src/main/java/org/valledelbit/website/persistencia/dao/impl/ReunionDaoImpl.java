package org.valledelbit.website.persistencia.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.valledelbit.website.persistencia.dao.ReunionDao;
import org.valledelbit.website.persistencia.vo.Reunion;

public class ReunionDaoImpl extends JdbcTemplate implements ReunionDao {	

	@Override
	public void create(Reunion reunion) {
		this.update("INSERT INTO REUNION(TEMA,FECHA,HORA,LUGAR,PONENTE,GEOLOCALIZACION,NOMBRE_LINK,SHORTENER,TAGS,OBJETIVO) VALUES(?,?,?,?,?,?,?,?,?,?)",
			new Object[]{
					reunion.getTema(), 
					reunion.getFecha(), 
					reunion.getHora(), 
					reunion.getLugar(), 
					reunion.getPonente(),
					reunion.getGeolocalizacion(),
					reunion.getNombreLink(),
					reunion.getShortener(),
					reunion.getTags(),
					reunion.getObjetivo()
			}
		);	
	}

	@Override
	public void delete(Reunion reunion) {
		this.update("DELETE FROM REUNION WHERE ID = ?",
			new Object[]{reunion.getId()}
		);
	}

	@Override
	public List<Reunion> findAll() {
		String sql = "SELECT * FROM REUNION";
		List<Reunion> resultados = this.query(sql, new RowMapper<Reunion>(){
			@Override
			public Reunion mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Reunion reunion = new Reunion();
				reunion.setId(rs.getInt("ID"));
				reunion.setTema(rs.getString("TEMA"));
				reunion.setFecha(rs.getDate("FECHA"));
				reunion.setHora(rs.getString("HORA"));
				reunion.setLugar(rs.getString("LUGAR"));
				reunion.setPonente(rs.getString("PONENTE"));
				reunion.setGeolocalizacion(rs.getString("GEOLOCALIZACION"));
				reunion.setNombreLink(rs.getString("NOMBRE_LINK"));
				reunion.setShortener(rs.getString("SHORTENER"));
				reunion.setTags(rs.getString("TAGS"));
				reunion.setObjetivo(rs.getString("OBJETIVO"));
				return reunion;
			}
		});
		return resultados;
	}

	@Override
	public Reunion read(Integer clave) {
		String sql = "SELECT * FROM REUNION WHERE ID = ?";
		try {
			Reunion resultado = this.queryForObject(sql, new Object[]{clave},
			new RowMapper<Reunion>(){
				@Override
				public Reunion mapRow(ResultSet rs, int rowNum)
						throws SQLException {
					Reunion reunion = new Reunion();
					reunion.setId(rs.getInt("ID"));
					reunion.setTema(rs.getString("TEMA"));
					reunion.setFecha(rs.getDate("FECHA"));
					reunion.setHora(rs.getString("HORA"));
					reunion.setLugar(rs.getString("LUGAR"));
					reunion.setPonente(rs.getString("PONENTE"));
					reunion.setGeolocalizacion(rs.getString("GEOLOCALIZACION"));
					reunion.setNombreLink(rs.getString("NOMBRE_LINK"));
					reunion.setShortener(rs.getString("SHORTENER"));
					reunion.setTags(rs.getString("TAGS"));
					reunion.setObjetivo(rs.getString("OBJETIVO"));
					return reunion;
				}
			});
			return resultado;
		} catch (EmptyResultDataAccessException accessException) {
			return null;
		}		
	}

	@Override
	public void update(Reunion reunion) {
		this.update("UPDATE REUNION SET TEMA = ?, FECHA = ?, HORA = ?, LUGAR = ?, PONENTE = ?, GEOLOCALIZACION = ?, NOMBRE_LINK = ?, OBJETIVO = ?, TAGS = ? WHERE ID = ?",
			new Object[]{
				reunion.getTema(), 
				reunion.getFecha(), 
				reunion.getHora(), 
				reunion.getLugar(), 
				reunion.getPonente(),
				reunion.getGeolocalizacion(),
				reunion.getNombreLink(),
				reunion.getObjetivo(),
				reunion.getTags(),
				reunion.getId()
			}
		);		
	}
	
	@Override
	public Reunion read(String nombreLink) {
		String sql = "SELECT * FROM REUNION WHERE NOMBRE_LINK = ?";
		try {
			Reunion resultado = this.queryForObject(sql, new Object[]{nombreLink},
			new RowMapper<Reunion>(){
				@Override
				public Reunion mapRow(ResultSet rs, int rowNum)
						throws SQLException {
					Reunion reunion = new Reunion();
					reunion.setId(rs.getInt("ID"));
					reunion.setTema(rs.getString("TEMA"));
					reunion.setFecha(rs.getDate("FECHA"));
					reunion.setHora(rs.getString("HORA"));
					reunion.setLugar(rs.getString("LUGAR"));
					reunion.setPonente(rs.getString("PONENTE"));
					reunion.setGeolocalizacion(rs.getString("GEOLOCALIZACION"));
					reunion.setNombreLink(rs.getString("NOMBRE_LINK"));
					reunion.setShortener(rs.getString("SHORTENER"));
					reunion.setTags(rs.getString("TAGS"));
					reunion.setObjetivo(rs.getString("OBJETIVO"));
					return reunion;
				}
			});
			return resultado;
		} catch (EmptyResultDataAccessException accessException) {
			return null;
		}		
	}
	
	@Override
	public void shutdown() {
		this.execute("SHUTDOWN");		
	}

	@Override
	public void desactivateReferentialIntegrity() {
		this.execute("SET REFERENTIAL_INTEGRITY FALSE");
	}
}