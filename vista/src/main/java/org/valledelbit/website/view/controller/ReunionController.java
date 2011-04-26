package org.valledelbit.website.view.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
			@RequestParam(value="id", required=true, defaultValue="undefined") int id, 
			Model model){
		log.debug("getReunion");
		try {			
			Reunion reunion = reunionService.getReunion(id);
			model.addAttribute("reunion",reunion);
		} catch (ValleDelBitWebSiteException e) {
			model.addAttribute("error", e.getMessage());
		}
		return "jsonView";
	}
	
	@RequestMapping(value = "/set", method = RequestMethod.PUT)
	public String setReunion(
			@RequestParam(value="geolocalizacion", required=true, defaultValue="undefined") String geolocalizacion,
			@RequestParam(value="hora", required=true, defaultValue="undefined") String hora,
			@RequestParam(value="lugar", required=true, defaultValue="undefined") String lugar,
			@RequestParam(value="nombreLink", required=true, defaultValue="undefined") String nombreLink,
			@RequestParam(value="ponente", required=true, defaultValue="undefined") String ponente,
			@RequestParam(value="shortener", required=true, defaultValue="undefined") String shortener,
			@RequestParam(value="tags", required=true, defaultValue="undefined") String tags,
			@RequestParam(value="tema", required=true, defaultValue="undefined") String tema,
			@RequestParam(value="objetivo", required=true, defaultValue="undefined") String objetivo,
			Model model){				
		
		List<String> errores = new ArrayList<String>();
		
		if(geolocalizacion.equals("undefined")){
			errores.add("Establezca el valor {geolocalizacion}");
		}
		
		if(hora.equals("undefined")){
			errores.add("Establezca el valor {hora}");
		}
		
		if(lugar.equals("undefined")){
			errores.add("Establezca el valor {lugar}");
		}
		
		if(nombreLink.equals("undefined")){
			errores.add("Establezca el valor {nombreLink}");
		}
		
		if(ponente.equals("undefined")){
			errores.add("Establezca el valor {ponente}");
		}
		
		if(shortener.equals("undefined")){
			errores.add("Establezca el valor {shortener}");
		}
		
		if(tags.equals("undefined")){
			errores.add("Establezca el valor {tags}");
		}
		
		if(tema.equals("undefined")){
			errores.add("Establezca el valor {tema}");
		}
		
		if(objetivo.equals("undefined")){
			errores.add("Establezca el valor {objetivo}");
		}		
		
		
		if(errores.size() > 0){
			model.addAttribute("error", errores);
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
			} catch (Exception e) {
				errores.add(e.getMessage());
			}
			model.addAttribute("result", "La reunion ha sido registrada");
		}
		return "jsonView";
	}

}