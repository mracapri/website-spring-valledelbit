package org.valledelbit.website.view.controller.reunion;

import java.util.List;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;
import org.valledelbit.website.persistencia.service.ReunionService;
import org.valledelbit.website.persistencia.vo.Reunion;
import org.valledelbit.website.view.controller.ReunionController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"/AppCtx-SpringMVC.xml",
	"classpath:/AppCtx-Persistencia.xml",
	"classpath:/AppCtx-Servicios.xml",
	"classpath:/AppCtx-Transacciones.xml"})
@Transactional
public class TestReunionControllerPOST {	
	
	@Autowired
	ReunionController reunionController;	
	@Autowired
	HandlerAdapter handlerAdapter;	
	@Autowired
	ReunionService reunionService;
	
    MockHttpServletRequest request;
    MockHttpServletResponse response;
    
    Reunion reunion;
          
	Logger log = Logger.getLogger(this.getClass());
	
    @Before
    public void setUp() {
    	request = new MockHttpServletRequest();
    	response = new MockHttpServletResponse();
       
		// Definiendo los valores de la prueba
		reunion = new Reunion();
		String mapa = "<iframe class='map' width='100%' height='150' " +
			"frameborder='0' scrolling='no' marginheight='0' " +
			"marginwidth='0' src='http://maps.google.com.mx/maps?" +
			"f=q&amp;source=embed&amp;hl=es&amp;geocode=&amp;q=" +
			"Utvm&amp;aq=&amp;sll=23.625269,-102.540613&amp;sspn=" +
			"22.18854,28.256836&amp;ie=UTF8&amp;hq=Utvm&amp;hnear=" +
			"&amp;cid=4499772947294339723&amp;ll=20.495798,-99.184" +
			"184&amp;spn=0.17815,0.220757&amp;output=embed'>" +
			"</iframe><br /><small><a href='http://maps.google.com." +
			"mx/maps?f=q&amp;source=embed&amp;hl=es&amp;geocode=&amp" +
			";q=Utvm&amp;aq=&amp;sll=23.625269,-102.540613&amp;sspn=22" +
			".18854,28.256836&amp;ie=UTF8&amp;hq=Utvm&amp;hnear=&amp;ci" +
			"d=4499772947294339723&amp;ll=20.495798,-99.184184&amp;spn=0" +
			".17815,0.220757' style='color:#0000FF;text-align:left'>Ver " +
			"mapa m&aacutes grande</a></small>";
		reunion.setGeolocalizacion(mapa);
		reunion.setHora("10:00");
		reunion.setLugar("Universidad Tecnologica del Valle del Mezquital");
		reunion.setPonente("Cesar Ceron Perez");
		reunion.setShortener("http://bit.ly/Er4534");
		reunion.setTags("['jquery','web']");
		reunion.setTema("Como implementar jQuery en nuestros sitios web");
		reunion.setObjetivo("Esta reunion esta dirigida a Universitarios, profesores y publico que le interese conocer el entorno social y el impacto de las redes sociales");
    }
    
	@Test
	public void testPasandoTodosLosValores() throws Exception{
		String nombreLink = "testPasandoTodosLosValores";
		String fechaReunion = "2011-05-23";
		// Pasando valores al request
		
		request.addParameter("geolocalizacion", reunion.getGeolocalizacion());
		request.addParameter("fecha", fechaReunion);
		request.addParameter("hora", reunion.getHora());
		request.addParameter("lugar", reunion.getLugar());
		request.addParameter("nombre_link", nombreLink);
		request.addParameter("ponente", reunion.getPonente());
		request.addParameter("shortener", reunion.getShortener());
		request.addParameter("tags", reunion.getTags());
		request.addParameter("tema", reunion.getTema());
		request.addParameter("objetivo", reunion.getObjetivo());
				
		// Estableciendo URI
		request.setMethod("POST");
		request.setRequestURI("/reunion/set");
		
		final ModelAndView modelAndView = handlerAdapter.handle(request, response, reunionController);
		
		Assert.assertEquals(true, reunionService.getReuniones().size() > 0);
		Assert.assertNotNull(modelAndView.getModel().get("result"));
		
		
		List<Reunion> reuniones = reunionService.getReuniones();
		log.debug(reuniones);
		
		// Obteniendo reunion modificada
		int index = 0;
		if(reuniones.size() > 0){
			index = reuniones.size()-1;
		}
		
		Reunion leida = reuniones.get(index);
		
		boolean result =  leida.getGeolocalizacion().equals(reunion.getGeolocalizacion());
		result = result && leida.getFecha().toString().equals(fechaReunion); 
		result = result && leida.getHora().equals(reunion.getHora());
		result = result && leida.getLugar().equals(reunion.getLugar());
		result = result && leida.getNombreLink().equals(nombreLink);
		result = result && leida.getPonente().equals(reunion.getPonente());
		result = result && leida.getTags().equals(reunion.getTags());
		result = result && leida.getTema().equals(reunion.getTema());
		result = result && leida.getObjetivo().equals(reunion.getObjetivo());
		Assert.assertEquals(true, result);
		
	}
	
	@Test
	public void testPasandoTodosLosValoresConFormatoDeFechaIncorrecto() throws Exception{
		String nombreLink = "testPasandoTodosLosValoresConFormatoDeFechaIncorrecto";
		// Fecha con formato incorrecto
		String fechaReunion = "2011/05/23";
		
		// Pasando valores al request
		
		request.addParameter("geolocalizacion", reunion.getGeolocalizacion());
		request.addParameter("fecha", fechaReunion);
		request.addParameter("hora", reunion.getHora());
		request.addParameter("lugar", reunion.getLugar());
		request.addParameter("nombre_link", nombreLink);
		request.addParameter("ponente", reunion.getPonente());
		request.addParameter("shortener", reunion.getShortener());
		request.addParameter("tags", reunion.getTags());
		request.addParameter("tema", reunion.getTema());
		request.addParameter("objetivo", reunion.getObjetivo());
				
		// Estableciendo URI
		request.setMethod("POST");
		request.setRequestURI("/reunion/set");
		
		final ModelAndView modelAndView = handlerAdapter.handle(request, response, reunionController);
		log.debug(modelAndView);
		
		Assert.assertTrue(modelAndView.getModel().get("error").equals("formato de fecha incorrecto, prueba con  YYYY-MM-DD"));
		
	}
	
	@Test
	public void testNingunValor() throws Exception{		
				
		// Estableciendo URI
		request.setMethod("POST");
		request.setRequestURI("/reunion/set");		
		
		final ModelAndView modelAndView = handlerAdapter.handle(request, response, reunionController);
		Assert.assertTrue(modelAndView.getModel().get("error").equals("geolocalizacion, fecha, hora, lugar, nombre_ink, ponente, shortener, tags, tema y objetivo, son requeridos"));
		
	}

}