package org.valledelbit.website.view.controller;

import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/shortener")
public class ShortenerController{
	
	private static final String JSON_FORMAT = "json";
	private static final String UNDEFINED_VALUE_LONG_URL = "undefined";

	protected final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${API_KEY_BIT_LY}")
	String apiKeyBitLy;

	@Value("${API_URL_BIT_LY_SHORTENER_SERVICE}")
	String apiUrlBitLyShortenerService;
	
	@Value("${BIT_LY_USER_LOGIN}")
	String bitLyUserLogin;	
	
	@RequestMapping(value="/doit", method = RequestMethod.GET)
	public String shortenerLongUrl(
			@RequestParam(value="long_url", required=true, defaultValue="undefined") String longUrl, 
			Model model){
		
		HashMap<String, String> parameters = new HashMap<String, String>();
		parameters.put("login", bitLyUserLogin);
		parameters.put("apiKey", apiKeyBitLy);
		parameters.put("longUrl", longUrl);
		parameters.put("format", JSON_FORMAT);
		
		log.debug("PARAMETERS: " + parameters);
		
		if(longUrl.equals(UNDEFINED_VALUE_LONG_URL)){
			model.addAttribute("error", "long_url es requerida");
		}else{
			Object forObject = restTemplate.getForObject(apiUrlBitLyShortenerService + 
					"?login={login}&apiKey={apiKey}&longUrl={longUrl}&format={format}", Object.class, parameters);
			log.debug(ToStringBuilder.reflectionToString(restTemplate));
			model.addAttribute("result", forObject);
		}
		log.debug(model);
		return "jsonView";
	}

}