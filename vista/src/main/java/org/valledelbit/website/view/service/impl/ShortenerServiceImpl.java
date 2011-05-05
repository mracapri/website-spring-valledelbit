package org.valledelbit.website.view.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.valledelbit.website.persistencia.exception.ValleDelBitWebSiteException;
import org.valledelbit.website.view.service.ShortenerService;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
@Service
public class ShortenerServiceImpl implements ShortenerService{
	
	private static final String BLANK_STRING = "";

	protected final Log log = LogFactory.getLog(getClass());
	
	@Value("${API_KEY_BIT_LY}")
	String apiKeyBitLy;

	@Value("${API_URL_BIT_LY_SHORTENER_SERVICE}")
	String apiUrlBitLyShortenerService;
	
	@Value("${BIT_LY_USER_LOGIN}")
	String bitLyUserLogin;	
	
	public String shortenerLongUrl(String longUrl) throws ValleDelBitWebSiteException{
		String shortUrl = null;
		if(longUrl.equals(BLANK_STRING)){
			throw new ValleDelBitWebSiteException("long_url es requerida");
		}else{
			Client client = Client.create();
			try {				
				longUrl = URLEncoder.encode(longUrl, "UTF-8");
				WebResource resource = client.resource(apiUrlBitLyShortenerService)
				.queryParam("login", bitLyUserLogin)
				.queryParam("apiKey", apiKeyBitLy)
				.queryParam("longUrl", longUrl);

				String json = resource.get(String.class);				
				JSONObject jsonObject = (JSONObject) new JSONParser().parse(json);
				String data = jsonObject.get("data").toString();
				jsonObject = (JSONObject) new JSONParser().parse(data);				
				
				shortUrl = jsonObject.get("url").toString();
				
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}	
		return shortUrl;
	}

}