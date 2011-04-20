package org.valledelbit.website.persistencia.vo;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Reunion {
	private int id;
	private String tema;
	private Date fecha;
	private String hora;
	private String lugar;
	private String ponente;
	private String geolocalizacion;
	private String nombreLink;
	private String shortener;
	private String tags;
	private String objetivo;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTema() {
		return tema;
	}
	public void setTema(String tema) {
		this.tema = tema;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	public String getPonente() {
		return ponente;
	}
	public void setPonente(String ponente) {
		this.ponente = ponente;
	}
	public String getGeolocalizacion() {
		return geolocalizacion;
	}
	public void setGeolocalizacion(String geolocalizacion) {
		this.geolocalizacion = geolocalizacion;
	}
	public String getNombreLink() {
		return nombreLink;
	}
	public void setNombreLink(String nombreLink) {
		this.nombreLink = nombreLink;
	}
	public String getShortener() {
		return shortener;
	}
	public void setShortener(String shortener) {
		this.shortener = shortener;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getObjetivo() {
		return objetivo;
	}
	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
