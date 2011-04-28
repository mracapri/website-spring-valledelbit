package org.valledelbit.website.persistencia.service;

import static junit.framework.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.valledelbit.website.persistencia.exception.ValleDelBitWebSiteException;
import org.valledelbit.website.persistencia.test.TestPrincipales;
import org.valledelbit.website.persistencia.vo.Reunion;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/AppCtx-Persistencia-Test.xml", "/AppCtx-Servicios.xml"})
@Transactional
public class TestReunionService implements TestPrincipales{
	@Autowired
	ReunionService reunionService;
	
	Logger log = Logger.getLogger(this.getClass());
	
	@Test
	public void testCreate(){
		// Definiendo los valores de la prueba
		Reunion reunion = new Reunion();
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
			"mapa m&aacutes grande</a></small>";
		reunion.setGeolocalizacion(mapa);
		reunion.setHora("10:00");
		reunion.setLugar("Universidad Tecnologica del Valle del Mezquital");
		reunion.setNombreLink("jquery-web");
		reunion.setPonente("Cesar Ceron Perez");
		reunion.setShortener("http://bit.ly/Er4534");
		reunion.setTags("['jquery','web']");
		reunion.setTema("Como implementar jQuery en nuestros sitios web");
		reunion.setObjetivo("Esta reunion esta dirigida a Universitarios, profesores y publico que le interese conocer el entorno social y el impacto de las redes sociales");
		
		try {
			// Persistiendo la reunion a travez del servicio
			reunionService.setReunion(reunion);
			
			// Obteniendo todas las reuniones
			List<Reunion> reuniones = reunionService.getReuniones();

			// Si la consulta trae mas de un registro 
			boolean result = reuniones.size() > 0;
			
			// La prueba es evaluada
			assertEquals(true, result);			
			
		} catch (ValleDelBitWebSiteException e) {
			assertEquals(true, false);
			log.error(ToStringBuilder.reflectionToString(e));
			e.printStackTrace();
		}
	}

	@Test
	public void testDelete() {
		// Definiendo los valores de la prueba
		Reunion reunion = new Reunion();
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
			"mapa m&aacutes grande</a></small>";
		reunion.setGeolocalizacion(mapa);
		reunion.setHora("10:00");
		reunion.setLugar("Universidad Tecnologica del Valle del Mezquital");
		reunion.setNombreLink("jquery-web");
		reunion.setPonente("Cesar Ceron Perez");
		reunion.setShortener("http://bit.ly/Er4534");
		reunion.setTags("['jquery','web']");
		reunion.setTema("Como implementar jQuery en nuestros sitios web");
		reunion.setObjetivo("Esta reunion esta dirigida a Universitarios, profesores y publico que le interese conocer el entorno social y el impacto de las redes sociales");
		
		try {
			// Persistiendo la reunion a travez del servicio
			reunionService.setReunion(reunion);
			
			// Obteniendo todas las reuniones
			List<Reunion> reuniones = reunionService.getReuniones();

			// Seleccionando la reunion CERO de la lista
			Reunion borrar = reuniones.get(0);
			
			//Eliminando la reunion
			reunionService.removeReunion(borrar);
			
			// Nuevamente obteniendo todas las reuniones
			reuniones = reunionService.getReuniones();			
			
			// Si la consulta trae CERO registros 
			boolean result = reuniones.size() == 0;
			
			// La prueba es evaluada
			assertEquals(true, result);			
			
		} catch (ValleDelBitWebSiteException e) {
			assertEquals(true, false);
			log.error(ToStringBuilder.reflectionToString(e));
			e.printStackTrace();
		}
	}

	@Test
	public void testFindAll() {
		// Definiendo los valores de la prueba
		Reunion reunion = new Reunion();
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
			"mapa m&aacutes grande</a></small>";
		reunion.setGeolocalizacion(mapa);
		reunion.setHora("10:00");
		reunion.setLugar("Universidad Tecnologica del Valle del Mezquital");
		reunion.setNombreLink("jquery-web");
		reunion.setPonente("Cesar Ceron Perez");
		reunion.setShortener("http://bit.ly/Er4534");
		reunion.setTags("['jquery','web']");
		reunion.setTema("Como implementar jQuery en nuestros sitios web");
		reunion.setObjetivo("Esta reunion esta dirigida a Universitarios, profesores y publico que le interese conocer el entorno social y el impacto de las redes sociales");
		
		Reunion reunion2 = reunion;
		
		try {
			// Persistiendo la reunion a travez del servicio
			reunionService.setReunion(reunion);
			
			// Persistiendo la reunion2 a travez del servicio
			reunionService.setReunion(reunion2);
			
			// Obteniendo todas las reuniones
			List<Reunion> reuniones = reunionService.getReuniones();

			// Si la consulta trae DOS registros 
			boolean result = reuniones.size() == 2;
			
			// La prueba es evaluada
			assertEquals(true, result);			
			
		} catch (ValleDelBitWebSiteException e) {
			assertEquals(true, false);
			log.error(ToStringBuilder.reflectionToString(e));
			e.printStackTrace();
		}
	}

	@Test
	public void testRead() {
		// Definiendo los valores de la prueba
		Reunion reunion = new Reunion();
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
			"mapa m&aacutes grande</a></small>";
		reunion.setGeolocalizacion(mapa);
		reunion.setHora("10:00");
		reunion.setLugar("Universidad Tecnologica del Valle del Mezquital");
		reunion.setNombreLink("jquery-web");
		reunion.setPonente("Cesar Ceron Perez");
		reunion.setShortener("http://bit.ly/Er4534");
		reunion.setTags("['jquery','web']");
		reunion.setTema("Como implementar jQuery en nuestros sitios web");
		reunion.setObjetivo("Esta reunion esta dirigida a Universitarios, profesores y publico que le interese conocer el entorno social y el impacto de las redes sociales");
		
		try {
			// Persistiendo la reunion a travez del servicio
			reunionService.setReunion(reunion);
			
			// Obteniendo todas las reuniones
			List<Reunion> reuniones = reunionService.getReuniones();

			// Obteniendo una reunion de la lista
			Reunion paraLeer = reuniones.get(0) ;

			// Obteniendo una reunion a travez del ID
			Reunion leida = reunionService.getReunion(paraLeer.getId());
			
			// Si los IDs son iguales   
			boolean result = paraLeer.getId() == leida.getId();
			
			// La prueba es evaluada
			assertEquals(true, result);			
			
		} catch (ValleDelBitWebSiteException e) {
			assertEquals(true, false);
			log.error(ToStringBuilder.reflectionToString(e));
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdate() {
		// Definiendo los valores de la prueba
		Reunion reunion = new Reunion();
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
			"mapa m&aacutes grande</a></small>";
		reunion.setGeolocalizacion(mapa);
		reunion.setHora("10:00");
		reunion.setLugar("Universidad Tecnologica del Valle del Mezquital");
		reunion.setNombreLink("jquery-web");
		reunion.setPonente("Cesar Ceron Perez");
		reunion.setShortener("http://bit.ly/Er4534");
		reunion.setTags("['jquery','web']");
		reunion.setTema("Como implementar jQuery en nuestros sitios web");
		reunion.setObjetivo("Esta reunion esta dirigida a Universitarios, profesores y publico que le interese conocer el entorno social y el impacto de las redes sociales");
		
		try {
			// Persistiendo la reunion a travez del servicio
			reunionService.setReunion(reunion);
			
			// Obteniendo todas las reuniones
			List<Reunion> reuniones = reunionService.getReuniones();

			// Obteniendo una reunion de la lista para modificar
			Reunion paraModificar = reuniones.get(0) ;
			
			// Modificando valores			
			paraModificar.setNombreLink("other-link");
			paraModificar.setObjetivo("XX yy ZZ");
			paraModificar.setPonente("Mario Rivera Angeles");
			
			// Modificando la reunion a travez del servicio
			reunionService.updateReunion(paraModificar);

			// Obteniendo la reunion modificada
			Reunion modificada = reunionService.getReunion(paraModificar.getId());
			
			// Si los IDs son iguales   
			boolean result = paraModificar.getNombreLink().equals(modificada.getNombreLink());
			result = result && paraModificar.getObjetivo().equals(modificada.getObjetivo());
			result = result && paraModificar.getPonente().equals(modificada.getPonente());
			result = result && paraModificar.getTags().equals(modificada.getTags());
			result = result && paraModificar.getTema().equals(modificada.getTema());
			result = result && paraModificar.getGeolocalizacion().equals(modificada.getGeolocalizacion());
			
			// La prueba es evaluada
			assertEquals(true, result);			
			
		} catch (ValleDelBitWebSiteException e) {
			assertEquals(true, false);
			log.error(ToStringBuilder.reflectionToString(e));
			e.printStackTrace();
		}
	}	
	
	@Test
	public void testReadIdNoExistente() {		
		try {

			// Obteniendo una reunion a travez del ID
			Reunion leida = reunionService.getReunion(1);
			
			// La prueba es evaluada
			assertEquals(true, leida == null);			
			
		} catch (ValleDelBitWebSiteException e) {
			assertEquals(true, false);
			log.error(ToStringBuilder.reflectionToString(e));
			e.printStackTrace();
		}
	}
}