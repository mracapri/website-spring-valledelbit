package org.valledelbit.website.view.controller.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.valledelbit.website.persistencia.exception.ValleDelBitWebSiteException;
import org.valledelbit.website.view.service.ShortenerService;

public class ControllerUtils {
	public static final String UNDEFINED = "undefined";
	public static final int INTEGER_UNDEFINED = -1;
	
	protected static final Log log = LogFactory.getLog(ControllerUtils.class);
	
	public static String getShortUrl(ShortenerService service, HttpServletRequest request, String partOfLink) throws ValleDelBitWebSiteException{
		String protocol = request.getProtocol();
		String localName = /*request.getLocalName()*/ "173.255.253.78";
		int localPort = 8080;
		String applicationWebContext = "valledelbit-vista-website";			
		String longUrl = protocol + "://" + localName + ":" + localPort + "/" + applicationWebContext + "/" + partOfLink;
		log.debug("URL LARGA: " + longUrl);
		return service.shortenerLongUrl(longUrl);
	}
	
	public static String getReunionUrlPattern(){
		return "?url=pages/reuniones.html&nombre_link=";
	}
}
