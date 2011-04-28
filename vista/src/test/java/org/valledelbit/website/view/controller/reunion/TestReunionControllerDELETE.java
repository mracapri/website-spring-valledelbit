package org.valledelbit.website.view.controller.reunion;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.apache.commons.lang.builder.ToStringBuilder;
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
public class TestReunionControllerDELETE {	
	
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
		reunion.setFecha(new Date());
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
				"mapa más grande</a></small>";
		reunion.setGeolocalizacion(mapa);
		reunion.setHora("10:00");
		reunion.setLugar("Universidad Tecnologica del Valle del Mezquital");
		reunion.setNombreLink("jquery-web");
		reunion.setPonente("Cesar Ceron Perez");
		reunion.setShortener("http://bit.ly/Er4534");
		reunion.setTags("['jquery','web']");
		reunion.setTema("Como implementar jQuery en nuestros sitios web");
		reunion.setObjetivo("Esta reunion esta dirigida a Universitarios, profesores y publico que le interese conocer el entorno social y el impacto de las redes sociales");
    }
    
	@Test
	public void testBorrandoReunionPorId() throws Exception{
		String fechaReunion = "2011-05-23";
		
		// Pasando valores al request
		
		request.addParameter("geolocalizacion", reunion.getGeolocalizacion());
		request.addParameter("fecha", fechaReunion);
		request.addParameter("hora", reunion.getHora());
		request.addParameter("lugar", reunion.getLugar());
		request.addParameter("nombre_link", reunion.getNombreLink());
		request.addParameter("ponente", reunion.getPonente());
		request.addParameter("shortener", reunion.getShortener());
		request.addParameter("tags", reunion.getTags());
		request.addParameter("tema", reunion.getTema());
		request.addParameter("objetivo", reunion.getObjetivo());
				
		/*
		 * Persistiendo reunion desde controller
		 * */
		
		// Estableciendo URI
		request.setMethod("POST");
		request.setRequestURI("/reunion/set");
		
		final ModelAndView modelAndView = handlerAdapter.handle(request, response, reunionController);
		
		List<Reunion> reuniones = reunionService.getReuniones();
		Reunion leer = reuniones.get(0);		
		log.debug(leer);		
		
		
		setUp();
		
		/*
		 * Consultando reunion desde controller
		 * */		
		
		// Estableciendo URI con el valor de la reunion
		request.addParameter("id", leer.getId() + "");
		request.setMethod("GET");
		request.setRequestURI("/reunion/get");		
		
		final ModelAndView modelAndView2 = handlerAdapter.handle(request, response, reunionController);
		Assert.assertNotNull(modelAndView2.getModel().get("reunion"));
		log.debug(modelAndView2);
		
		
		setUp();
		
		/*
		 * Borrando
		 * */
		
		// Estableciendo URI con el valor de la reunion
		request.addParameter("id", leer.getId() + "");
		request.setMethod("DELETE");
		request.setRequestURI("/reunion/remove");
		log.debug(ToStringBuilder.reflectionToString(request));
		
		final ModelAndView modelAndView3 = handlerAdapter.handle(request, response, reunionController);
		log.debug(modelAndView3);
		Assert.assertTrue(modelAndView3.getModel().get("result").equals("la reunion ha sido removida"));
		
		
		setUp();
		
		/*
		 * Consultando nuevamente
		 * */
		
		// Estableciendo URI con el valor de la reunion
		request.addParameter("id", leer.getId() + "");
		request.setMethod("GET");
		request.setRequestURI("/reunion/get");		
		
		final ModelAndView modelAndView4 = handlerAdapter.handle(request, response, reunionController);		
		Assert.assertTrue(modelAndView4.getModel().get("result").equals("la reunion no existe en el catalogo"));
		log.debug(modelAndView4);
	}

}