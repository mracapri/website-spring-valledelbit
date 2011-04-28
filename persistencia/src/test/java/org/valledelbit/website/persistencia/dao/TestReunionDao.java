package org.valledelbit.website.persistencia.dao;

import static junit.framework.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.valledelbit.website.persistencia.test.TestPrincipales;
import org.valledelbit.website.persistencia.vo.Reunion;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/AppCtx-Persistencia-Test.xml")
@Transactional
public class TestReunionDao implements TestPrincipales{
	@Autowired
	ReunionDao reunionDao;
	
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
		
		// Persistiendo la reunion
		reunionDao.create(reunion);

		// Obteniendo todos las reuniones
		List<Reunion> findAll = reunionDao.findAll();
		
		// Si los valores son mayores de CERO, entonces la reunion esta persistida		
		assertEquals(true,findAll.size() > 0);
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
		
		// Persistiendo la reunion
		reunionDao.create(reunion);
		
		//Obteniendo todos las reuniones
		List<Reunion> findAll = reunionDao.findAll();

		//Seleccionando reunion a eliminar
		Reunion borrar = findAll.get(0);
		
		//Eliminando reunion
		reunionDao.delete(borrar);
		
		//Obteniendo nuevamente todas las reuniones
		findAll = reunionDao.findAll();
		
		// Si los valores son iguales a CERO, entonces la reunion fue eliminada
		assertEquals(true,findAll.size() == 0);
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
		
		// Creando reunion2 de reunion
		Reunion reunion2 = reunion;		
		
		// Persistiendo la reunion
		reunionDao.create(reunion);
		
		// Persistiendo la reunion2
		reunionDao.create(reunion2);
		
		//Obteniendo todos las reuniones
		List<Reunion> findAll = reunionDao.findAll();
		
		// Si los valores son iguales a DOS, entonces las reunion fueron seleccionadas
		assertEquals(true,findAll.size() == 2);
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
		
		// Persistiendo la reunion
		reunionDao.create(reunion);
		
		// Obteniendo todos las reuniones
		List<Reunion> findAll = reunionDao.findAll();
		
		//Seleccionando reunion a eliminar
		Reunion seleccionar = findAll.get(0);
		
		//Leyendo reunion por Id
		Reunion result = reunionDao.read(seleccionar.getId());
		
		// Si los IDs son iguales entonces se leyo el dato correctamente
		assertEquals(true,result.getId() == seleccionar.getId());
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
		
		// Persistiendo la reunion
		reunionDao.create(reunion);
		
		// Obteniendo todos las reuniones
		List<Reunion> findAll = reunionDao.findAll();
		
		//Seleccionando reunion a modificar y modificando datos
		Reunion modificar = findAll.get(0);
		modificar.setFecha(new Date());
		reunion.setGeolocalizacion(mapa);
		modificar.setHora("11:00");
		modificar.setLugar("Universidad Tecnologica del Valle del Mezquital XYZ");
		modificar.setNombreLink("jquery-web-xyz");
		modificar.setPonente("Cesar Ceron Perez XYZ");		
		modificar.setTema("Como implementar jQuery en nuestros sitios web XYZ");
		modificar.setObjetivo("Esta reunion esta dirigida a Universitarios, profesores y publico que le interese conocer el entorno social y el impacto de las redes sociales XYZ");
		modificar.setTags("['jquery','web', 'xyz']");
		
		// Actualizando datos la reunion
		reunionDao.update(modificar);
		
		// Nuevamente obteniendo todos las reuniones
		findAll = reunionDao.findAll();
				
		//Reunion modificada
		Reunion modificada = findAll.get(0);
		
		
		//Evalua que los datos actualizados coinsiden con los modificados
		boolean resultado = modificar.getGeolocalizacion().equals(modificada.getGeolocalizacion());
		//resultado = resultado && (modificar.getFecha().equals(modificada.getFecha()));
		resultado = resultado && (modificar.getHora().equals(modificada.getHora()));
		resultado = resultado && (modificar.getLugar().equals(modificada.getLugar()));
		resultado = resultado && (modificar.getNombreLink().equals(modificada.getNombreLink()));		
		resultado = resultado && (modificar.getPonente().equals(modificada.getPonente()));
		resultado = resultado && (modificar.getTema().equals(modificada.getTema()));
		resultado = resultado && (modificar.getObjetivo().equals(modificada.getObjetivo()));
		resultado = resultado && (modificar.getTags().equals(modificada.getTags()));
		
		assertEquals(true,resultado);
	}	
	
}