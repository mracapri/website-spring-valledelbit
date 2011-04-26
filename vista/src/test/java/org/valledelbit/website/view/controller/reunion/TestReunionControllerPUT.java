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
	public void testPasandoTodosLosValores() throws Exception{

		// Pasando valores al request
		
		request.addParameter("geolocalizacion", reunion.getGeolocalizacion());		
		request.addParameter("hora", reunion.getHora());
		request.addParameter("lugar", reunion.getLugar());
		request.addParameter("nombreLink", reunion.getNombreLink());
		request.addParameter("ponente", reunion.getPonente());
		request.addParameter("shortener", reunion.getShortener());
		request.addParameter("tags", reunion.getTags());
		request.addParameter("tema", reunion.getTema());
		request.addParameter("objetivo", reunion.getTags());
				
		// Estableciendo URI
		request.setMethod("PUT");
		request.setRequestURI("/reunion/set");
		
		final ModelAndView modelAndView = handlerAdapter.handle(request, response, reunionController);
		
		Assert.assertEquals(true, reunionService.getReuniones().size() > 0);
		Assert.assertNotNull(modelAndView.getModel().get("result"));
		
	}
	
	@Test
	public void testNingunValor() throws Exception{		
				
		// Estableciendo URI
		request.setMethod("PUT");
		request.setRequestURI("/reunion/set");		
		
		final ModelAndView modelAndView = handlerAdapter.handle(request, response, reunionController);
		List<String> errores = (List<String>) modelAndView.getModel().get("error"); 
		Assert.assertTrue(errores.size() > 0);
		
	}
	
	@Test
	public void testFaltaGeolocalizacion() throws Exception{

		// Pasando valores al request
		//request.addParameter("geolocalizacion", reunion.getGeolocalizacion());
		request.addParameter("hora", reunion.getHora());
		request.addParameter("lugar", reunion.getLugar());
		request.addParameter("nombreLink", reunion.getNombreLink());
		request.addParameter("ponente", reunion.getPonente());
		request.addParameter("shortener", reunion.getShortener());
		request.addParameter("tags", reunion.getTags());
		request.addParameter("tema", reunion.getTema());
		request.addParameter("objetivo", reunion.getTags());
				
		// Estableciendo URI
		request.setMethod("PUT");
		request.setRequestURI("/reunion/set");		
		
		final ModelAndView modelAndView = handlerAdapter.handle(request, response, reunionController);
		String error = modelAndView.getModel().get("error").toString();
		
		Assert.assertTrue(error.equals("[Establezca el valor {geolocalizacion}]"));
		
	}
	
	@Test
	public void testFaltaHora() throws Exception{

		// Pasando valores al request
		request.addParameter("geolocalizacion", reunion.getGeolocalizacion());		
		//request.addParameter("hora", reunion.getHora());
		request.addParameter("lugar", reunion.getLugar());
		request.addParameter("nombreLink", reunion.getNombreLink());
		request.addParameter("ponente", reunion.getPonente());
		request.addParameter("shortener", reunion.getShortener());
		request.addParameter("tags", reunion.getTags());
		request.addParameter("tema", reunion.getTema());
		request.addParameter("objetivo", reunion.getTags());
				
		// Estableciendo URI
		request.setMethod("PUT");
		request.setRequestURI("/reunion/set");		
		
		final ModelAndView modelAndView = handlerAdapter.handle(request, response, reunionController);
		String error = modelAndView.getModel().get("error").toString();
		
		Assert.assertTrue(error.equals("[Establezca el valor {hora}]"));
		
	}
	
	@Test
	public void testFaltaLugar() throws Exception{

		// Pasando valores al request
		request.addParameter("geolocalizacion", reunion.getGeolocalizacion());		
		request.addParameter("hora", reunion.getHora());
		//request.addParameter("lugar", reunion.getLugar());
		request.addParameter("nombreLink", reunion.getNombreLink());
		request.addParameter("ponente", reunion.getPonente());
		request.addParameter("shortener", reunion.getShortener());
		request.addParameter("tags", reunion.getTags());
		request.addParameter("tema", reunion.getTema());
		request.addParameter("objetivo", reunion.getTags());
				
		// Estableciendo URI
		request.setMethod("PUT");
		request.setRequestURI("/reunion/set");		
		
		final ModelAndView modelAndView = handlerAdapter.handle(request, response, reunionController);
		String error = modelAndView.getModel().get("error").toString();
		
		Assert.assertTrue(error.equals("[Establezca el valor {lugar}]"));
		
	}
	
	@Test
	public void testFaltaNombreLink() throws Exception{

		// Pasando valores al request
		request.addParameter("geolocalizacion", reunion.getGeolocalizacion());		
		request.addParameter("hora", reunion.getHora());
		request.addParameter("lugar", reunion.getLugar());
		//request.addParameter("nombreLink", reunion.getNombreLink());
		request.addParameter("ponente", reunion.getPonente());
		request.addParameter("shortener", reunion.getShortener());
		request.addParameter("tags", reunion.getTags());
		request.addParameter("tema", reunion.getTema());
		request.addParameter("objetivo", reunion.getTags());
				
		// Estableciendo URI
		request.setMethod("PUT");
		request.setRequestURI("/reunion/set");		
		
		final ModelAndView modelAndView = handlerAdapter.handle(request, response, reunionController);
		String error = modelAndView.getModel().get("error").toString();
		
		Assert.assertTrue(error.equals("[Establezca el valor {nombreLink}]"));
		
	}
	
	@Test
	public void testFaltaPonente() throws Exception{

		// Pasando valores al request
		request.addParameter("geolocalizacion", reunion.getGeolocalizacion());		
		request.addParameter("hora", reunion.getHora());
		request.addParameter("lugar", reunion.getLugar());
		request.addParameter("nombreLink", reunion.getNombreLink());
		//request.addParameter("ponente", reunion.getPonente());
		request.addParameter("shortener", reunion.getShortener());
		request.addParameter("tags", reunion.getTags());
		request.addParameter("tema", reunion.getTema());
		request.addParameter("objetivo", reunion.getTags());
				
		// Estableciendo URI
		request.setMethod("PUT");
		request.setRequestURI("/reunion/set");		
		
		final ModelAndView modelAndView = handlerAdapter.handle(request, response, reunionController);
		String error = modelAndView.getModel().get("error").toString();
		
		Assert.assertTrue(error.equals("[Establezca el valor {ponente}]"));
		
	}
	
	@Test
	public void testFaltaShortener() throws Exception{

		// Pasando valores al request
		request.addParameter("geolocalizacion", reunion.getGeolocalizacion());		
		request.addParameter("hora", reunion.getHora());
		request.addParameter("lugar", reunion.getLugar());
		request.addParameter("nombreLink", reunion.getNombreLink());
		request.addParameter("ponente", reunion.getPonente());
		//request.addParameter("shortener", reunion.getShortener());
		request.addParameter("tags", reunion.getTags());
		request.addParameter("tema", reunion.getTema());
		request.addParameter("objetivo", reunion.getTags());
				
		// Estableciendo URI
		request.setMethod("PUT");
		request.setRequestURI("/reunion/set");		
		
		final ModelAndView modelAndView = handlerAdapter.handle(request, response, reunionController);
		String error = modelAndView.getModel().get("error").toString();
		
		Assert.assertTrue(error.equals("[Establezca el valor {shortener}]"));
		
	}
	
	@Test
	public void testFaltaTags() throws Exception{

		// Pasando valores al request
		request.addParameter("geolocalizacion", reunion.getGeolocalizacion());		
		request.addParameter("hora", reunion.getHora());
		request.addParameter("lugar", reunion.getLugar());
		request.addParameter("nombreLink", reunion.getNombreLink());
		request.addParameter("ponente", reunion.getPonente());
		request.addParameter("shortener", reunion.getShortener());
		//request.addParameter("tags", reunion.getTags());
		request.addParameter("tema", reunion.getTema());
		request.addParameter("objetivo", reunion.getTags());
				
		// Estableciendo URI
		request.setMethod("PUT");
		request.setRequestURI("/reunion/set");		
		
		final ModelAndView modelAndView = handlerAdapter.handle(request, response, reunionController);
		String error = modelAndView.getModel().get("error").toString();
		
		Assert.assertTrue(error.equals("[Establezca el valor {tags}]"));
		
	}
	
	@Test
	public void testFaltaTema() throws Exception{

		// Pasando valores al request
		request.addParameter("geolocalizacion", reunion.getGeolocalizacion());		
		request.addParameter("hora", reunion.getHora());
		request.addParameter("lugar", reunion.getLugar());
		request.addParameter("nombreLink", reunion.getNombreLink());
		request.addParameter("ponente", reunion.getPonente());
		request.addParameter("shortener", reunion.getShortener());
		request.addParameter("tags", reunion.getTags());
		//request.addParameter("tema", reunion.getTema());
		request.addParameter("objetivo", reunion.getTags());
				
		// Estableciendo URI
		request.setMethod("PUT");
		request.setRequestURI("/reunion/set");		
		
		final ModelAndView modelAndView = handlerAdapter.handle(request, response, reunionController);
		String error = modelAndView.getModel().get("error").toString();
		
		Assert.assertTrue(error.equals("[Establezca el valor {tema}]"));
		
	}
	
	@Test
	public void testFaltaObjetivo() throws Exception{

		// Pasando valores al request
		request.addParameter("geolocalizacion", reunion.getGeolocalizacion());		
		request.addParameter("hora", reunion.getHora());
		request.addParameter("lugar", reunion.getLugar());
		request.addParameter("nombreLink", reunion.getNombreLink());
		request.addParameter("ponente", reunion.getPonente());
		request.addParameter("shortener", reunion.getShortener());
		request.addParameter("tags", reunion.getTags());
		request.addParameter("tema", reunion.getTema());
		//request.addParameter("objetivo", reunion.getTags());
				
		// Estableciendo URI
		request.setMethod("PUT");
		request.setRequestURI("/reunion/set");		
		
		final ModelAndView modelAndView = handlerAdapter.handle(request, response, reunionController);
		String error = modelAndView.getModel().get("error").toString();
		
		Assert.assertTrue(error.equals("[Establezca el valor {objetivo}]"));
		
	}

}