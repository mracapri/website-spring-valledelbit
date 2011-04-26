package org.valledelbit.website.view.controller;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public String getReunion(
			@RequestParam(value="id", required=true, defaultValue="0") int id, 
			Model model){
		
		if(id == 0){
			model.addAttribute("id es requerido");
		}else{
			try {			
				Reunion reunion = reunionService.getReunion(id);
				model.addAttribute("reunion",reunion);				
			} catch (ValleDelBitWebSiteException e) {
				model.addAttribute("error", e.getMessage());
			}
		}
		return "jsonView";
	}
	
	@RequestMapping(value = "/set", method = RequestMethod.PUT)
	public String setReunion(
			@RequestParam(value="geolocalizacion", required=true, defaultValue="undefined") String geolocalizacion,
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
			model.addAttribute("error", "geolocalizacion, hora, lugar, nombre_ink, ponente, shortener, tags, tema y objetivo, son requeridos");
		}else{
			try {
				Reunion reunion = new Reunion();
				reunion = new Reunion();
				reunion.setFecha(new Date());
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
			} catch (Exception e) {
				model.addAttribute("error", e.getMessage());
			}
		}
		return "jsonView";
	}

}