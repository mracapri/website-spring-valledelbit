package org.valledelbit.website.view.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.valledelbit.website.persistencia.exception.ValleDelBitWebSiteException;
import org.valledelbit.website.persistencia.service.ReunionService;
import org.valledelbit.website.persistencia.vo.Reunion;

@Controller
@RequestMapping("/reunion")
public class ReunionController{
	
	protected final Log log = LogFactory.getLog(getClass());	
	
	@Autowired
	ReunionService reunionService;
	
	@Value("${DATE_PATTERN}")
	String[] patronFecha;

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public String getReunion(
			@RequestParam(value="id", required=true, defaultValue="-1") int id, 
			Model model){
		
		if(id == -1){
			model.addAttribute("error", "id es requerido");
		}else{
			try {			
				Reunion reunion = reunionService.getReunion(id);
				if(reunion == null){
					model.addAttribute("result", "la reunion no existe en el catalogo");
				}else{
					model.addAttribute("reunion",reunion);	
				}
			} catch (ValleDelBitWebSiteException e) {
				model.addAttribute("error", e.getMessage());
			}
		}
		return "jsonView";
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String getReuniones(Model model){
		try {			
			List<Reunion> reuniones = reunionService.getReuniones();
			if(reuniones.size() == 0){
				model.addAttribute("result", "no existen reuniones registradas en el catalogo");
			}else{
				model.addAttribute("reuniones",reuniones);	
			}
		} catch (ValleDelBitWebSiteException e) {
			model.addAttribute("error", e.getMessage());
		}
		return "jsonView";
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.DELETE)
	public String removeReunion(
			@RequestParam(value="id", required=true, defaultValue="-1") int id, 
			Model model){
		
		if(id == -1){
			model.addAttribute("error", "id es requerido");
		}else{
			try {			
				Reunion reunion = new Reunion();
				reunion.setId(id);
				reunionService.removeReunion(reunion);
				model.addAttribute("result", "la reunion ha sido removida");
			} catch (ValleDelBitWebSiteException e) {
				model.addAttribute("error", e.getMessage());
			}
		}
		return "jsonView";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public String updateReunion(
			@RequestParam(value="id", required=true, defaultValue="-1") int id,
			@RequestParam(value="geolocalizacion", required=true, defaultValue="undefined") String geolocalizacion,
			@RequestParam(value="fecha", required=true, defaultValue="undefined") String fecha,
			@RequestParam(value="hora", required=true, defaultValue="undefined") String hora,
			@RequestParam(value="lugar", required=true, defaultValue="undefined") String lugar,
			@RequestParam(value="nombre_link", required=true, defaultValue="undefined") String nombreLink,
			@RequestParam(value="ponente", required=true, defaultValue="undefined") String ponente,
			@RequestParam(value="shortener", required=true, defaultValue="undefined") String shortener,
			@RequestParam(value="tags", required=true, defaultValue="undefined") String tags,
			@RequestParam(value="tema", required=true, defaultValue="undefined") String tema,
			@RequestParam(value="objetivo", required=true, defaultValue="undefined") String objetivo,
			Model model){
		
		if(id == -1){
			model.addAttribute("error", "id es requerido ó geolocalizacion, hora, lugar, nombre_link, ponente, shortener, tags, tema ó objetivo");
		}else{
			try {			
				Reunion reunion = new Reunion();
				reunion.setId(id);
				Date parseDate = DateUtils.parseDate(fecha, patronFecha);
				reunion.setFecha(parseDate);
				reunion.setGeolocalizacion(geolocalizacion);
				reunion.setHora(hora);
				reunion.setLugar(lugar);
				reunion.setNombreLink(nombreLink);
				reunion.setPonente(ponente);
				reunion.setShortener(shortener);
				reunion.setTags(tags);
				reunion.setTema(tema);
				reunion.setObjetivo(objetivo);
				
				reunionService.updateReunion(reunion);
				model.addAttribute("result", "la reunion ha sido modificada");
			} catch (ValleDelBitWebSiteException e) {
				model.addAttribute("error", e.getMessage());
			} catch (ParseException e) {
				model.addAttribute("error", "formato de fecha incorrecto, prueba con  YYYY-MM-DD");
			}
		}
		return "jsonView";
	}
	
	@RequestMapping(value = "/set", method = RequestMethod.POST)
	public String setReunion(
			@RequestParam(value="geolocalizacion", required=true, defaultValue="undefined") String geolocalizacion,
			@RequestParam(value="fecha", required=true, defaultValue="undefined") String fecha,
			@RequestParam(value="hora", required=true, defaultValue="undefined") String hora,
			@RequestParam(value="lugar", required=true, defaultValue="undefined") String lugar,
			@RequestParam(value="nombre_link", required=true, defaultValue="undefined") String nombreLink,
			@RequestParam(value="ponente", required=true, defaultValue="undefined") String ponente,
			@RequestParam(value="shortener", required=true, defaultValue="undefined") String shortener,
			@RequestParam(value="tags", required=true, defaultValue="undefined") String tags,
			@RequestParam(value="tema", required=true, defaultValue="undefined") String tema,
			@RequestParam(value="objetivo", required=true, defaultValue="undefined") String objetivo,
			Model model){				
				
		if(geolocalizacion.equals("undefined") && 
				hora.equals("undefined") && 
				lugar.equals("undefined") && 
				nombreLink.equals("undefined") &&
				ponente.equals("undefined") && 
				shortener.equals("undefined") && 
				tags.equals("undefined") && 
				tema.equals("undefined") && 
				objetivo.equals("undefined")){
			model.addAttribute("error", "geolocalizacion, fecha, hora, lugar, nombre_ink, ponente, shortener, tags, tema y objetivo, son requeridos");
		}else{
			try {
				Reunion reunion = new Reunion();
				reunion = new Reunion();
				Date parseDate = DateUtils.parseDate(fecha, patronFecha);
				reunion.setFecha(parseDate);
				reunion.setGeolocalizacion(geolocalizacion);
				reunion.setHora(hora);
				reunion.setLugar(lugar);
				reunion.setNombreLink(nombreLink);
				reunion.setPonente(ponente);
				reunion.setShortener(shortener);
				reunion.setTags(tags);
				reunion.setTema(tema);
				reunion.setObjetivo(objetivo);
				
				reunionService.setReunion(reunion);
				
				model.addAttribute("result", "la reunion ha sido registrada");
			} catch (ValleDelBitWebSiteException e) {
				model.addAttribute("error", e.getMessage());
			} catch (ParseException e) {
				model.addAttribute("error", "formato de fecha incorrecto, prueba con  YYYY-MM-DD");
			}
		}
		return "jsonView";
	}

}