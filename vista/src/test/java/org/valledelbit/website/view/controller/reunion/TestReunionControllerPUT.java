package org.valledelbit.website.view.controller.reunion;

import java.util.Date;
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
public class TestReunionControllerPUT {	
	
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
		reunion.setGeolocalizacion("<iframe class='map' width='100%' height='150' frameborder='0' scrolling='no' marginheight='0' marginwidth='0' src='http://maps.google.com.mx/maps?f=q&amp;source=embed&amp;hl=es&amp;geocode=&amp;q=Utvm&amp;aq=&amp;sll=23.625269,-102.540613&amp;sspn=22.18854,28.256836&amp;ie=UTF8&amp;hq=Utvm&amp;hnear=&amp;cid=4499772947294339723&amp;ll=20.495798,-99.184184&amp;spn=0.17815,0.220757&amp;output=embed'></iframe><br /><small><a href='http://maps.google.com.mx/maps?f=q&amp;source=embed&amp;hl=es&amp;geocode=&amp;q=Utvm&amp;aq=&amp;sll=23.625269,-102.540613&amp;sspn=22.18854,28.256836&amp;ie=UTF8&amp;hq=Utvm&amp;hnear=&amp;cid=4499772947294339723&amp;ll=20.495798,-99.184184&amp;spn=0.17815,0.220757' style='color:#0000FF;text-align:left'>Ver mapa más grande</a></small>");
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
	public void testModificandoReunion() throws Exception{
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
				
		// Estableciendo URI
		request.setMethod("POST");
		request.setRequestURI("/reunion/set");
		
		final ModelAndView modelAndView = handlerAdapter.handle(request, response, reunionController);
		
		Assert.assertEquals(true, reunionService.getReuniones().size() > 0);
		Assert.assertNotNull(modelAndView.getModel().get("result"));
		
		// Obteniendo reunion a modificar
		List<Reunion> reuniones = reunionService.getReuniones();
		Reunion paraModificar = reuniones.get(0);
		Assert.assertTrue(modelAndView.getModel().get("result").equals("la reunion ha sido registrada"));
		
		
		setUp();
		
		String nuevaFecha = "2011-05-22";
		
		// Modificando valores de la reunion
		paraModificar.setFecha(new Date());
		paraModificar.setGeolocalizacion("XXXXXX");
		paraModificar.setHora("XX:XX");
		paraModificar.setLugar("XXXXXXXXXXXXXXXXXXXXX");
		paraModificar.setNombreLink("XXX-XXX");
		paraModificar.setPonente("XXX XXX XXXXXX");
		paraModificar.setTags("['XXXX','XXX']");
		paraModificar.setTema("XXXXX XXXXXXXX XXXXXX XXXX");
		paraModificar.setObjetivo("XXXXXXXXXXXXXXXXXX XXXXXXX XXXXX");
		

		// Pasando valores al request
		request.addParameter("id", paraModificar.getId() + "");
		request.addParameter("geolocalizacion", paraModificar.getGeolocalizacion());
		request.addParameter("fecha", nuevaFecha);
		request.addParameter("hora", paraModificar.getHora());
		request.addParameter("lugar", paraModificar.getLugar());
		request.addParameter("nombre_link", paraModificar.getNombreLink());
		request.addParameter("ponente", paraModificar.getPonente());
		request.addParameter("tags", paraModificar.getTags());
		request.addParameter("tema", paraModificar.getTema());
		request.addParameter("objetivo", paraModificar.getObjetivo());
		
		// Estableciendo URI
		request.setMethod("PUT");
		request.setRequestURI("/reunion/update");		
		final ModelAndView modelAndView2 = handlerAdapter.handle(request, response, reunionController);
		log.debug(modelAndView2);
		Assert.assertTrue(modelAndView2.getModel().get("result").equals("la reunion ha sido modificada"));
		
		
		setUp();
		
		// Pasando valores al request
		request.addParameter("id", paraModificar.getId() + "");
		
		// Estableciendo URI
		request.setMethod("GET");
		request.setRequestURI("/reunion/get");
		final ModelAndView modelAndView3 = handlerAdapter.handle(request, response, reunionController);
		log.debug(modelAndView3);
		Assert.assertNotNull(modelAndView3.getModel().get("reunion"));
		
		// Obteniendo reunion modificada
		Reunion modificada = reunionService.getReunion(paraModificar.getId());
		
		boolean result =  modificada.getGeolocalizacion().equals(paraModificar.getGeolocalizacion());
		result = result && modificada.getFecha().toString().equals(nuevaFecha);
		result = result && modificada.getHora().equals(paraModificar.getHora());
		result = result && modificada.getLugar().equals(paraModificar.getLugar());
		result = result && modificada.getNombreLink().equals(paraModificar.getNombreLink());
		result = result && modificada.getPonente().equals(paraModificar.getPonente());
		result = result && modificada.getTags().equals(paraModificar.getTags());
		result = result && modificada.getTema().equals(paraModificar.getTema());
		result = result && modificada.getObjetivo().equals(paraModificar.getObjetivo());
		
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void testModificandoReunionConFormatoDeFechaIncorrecto() throws Exception{
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
				
		// Estableciendo URI
		request.setMethod("POST");
		request.setRequestURI("/reunion/set");
		
		final ModelAndView modelAndView = handlerAdapter.handle(request, response, reunionController);
		
		Assert.assertEquals(true, reunionService.getReuniones().size() > 0);
		Assert.assertNotNull(modelAndView.getModel().get("result"));
		
		// Obteniendo reunion a modificar
		List<Reunion> reuniones = reunionService.getReuniones();
		Reunion paraModificar = reuniones.get(0);
		Assert.assertTrue(modelAndView.getModel().get("result").equals("la reunion ha sido registrada"));
		
		
		setUp();
		
		String nuevaFecha = "2011/05/22";
		
		// Modificando valores de la reunion
		paraModificar.setFecha(new Date());
		paraModificar.setGeolocalizacion("XXXXXX");
		paraModificar.setHora("XX:XX");
		paraModificar.setLugar("XXXXXXXXXXXXXXXXXXXXX");
		paraModificar.setNombreLink("XXX-XXX");
		paraModificar.setPonente("XXX XXX XXXXXX");
		paraModificar.setTags("['XXXX','XXX']");
		paraModificar.setTema("XXXXX XXXXXXXX XXXXXX XXXX");
		paraModificar.setObjetivo("XXXXXXXXXXXXXXXXXX XXXXXXX XXXXX");
		

		// Pasando valores al request
		request.addParameter("id", paraModificar.getId() + "");
		request.addParameter("geolocalizacion", paraModificar.getGeolocalizacion());
		request.addParameter("fecha", nuevaFecha);
		request.addParameter("hora", paraModificar.getHora());
		request.addParameter("lugar", paraModificar.getLugar());
		request.addParameter("nombre_link", paraModificar.getNombreLink());
		request.addParameter("ponente", paraModificar.getPonente());
		request.addParameter("tags", paraModificar.getTags());
		request.addParameter("tema", paraModificar.getTema());
		request.addParameter("objetivo", paraModificar.getObjetivo());
		
		// Estableciendo URI
		request.setMethod("PUT");
		request.setRequestURI("/reunion/update");		
		final ModelAndView modelAndView2 = handlerAdapter.handle(request, response, reunionController);
		log.debug(modelAndView2);
		Assert.assertTrue(modelAndView2.getModel().get("error").equals("formato de fecha incorrecto, prueba con  YYYY-MM-DD"));

	}
}