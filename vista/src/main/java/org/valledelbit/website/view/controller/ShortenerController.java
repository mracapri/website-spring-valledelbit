package org.valledelbit.website.view.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

@Controller
@RequestMapping("/shortener")
public class ShortenerController{
	
	private static final String BLANK_STRING = "";
	private static final String UNDEFINED_VALUE_LONG_URL = "undefined";

	protected final Log log = LogFactory.getLog(getClass());
	
	@Value("${API_KEY_BIT_LY}")
	String apiKeyBitLy;

	@Value("${API_URL_BIT_LY_SHORTENER_SERVICE}")
	String apiUrlBitLyShortenerService;
	
	@Value("${BIT_LY_USER_LOGIN}")
	String bitLyUserLogin;	
	
	@RequestMapping(value="/doit", method = RequestMethod.GET)
	public String shortenerLongUrl(
			@RequestParam(value="long_url", required=true, defaultValue=UNDEFINED_VALUE_LONG_URL) String longUrl, 
			Model model){
		
		if(longUrl.equals(UNDEFINED_VALUE_LONG_URL) || longUrl.equals(BLANK_STRING)){
			model.addAttribute("error", "long_url es requerida");
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
				model.addAttribute("result", jsonObject);
				
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		log.debug(model);
		return "jsonView";
	}

}