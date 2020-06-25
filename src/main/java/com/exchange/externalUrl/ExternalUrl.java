package com.exchange.externalUrl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/*This class as URL class , to call external public API */
@Configuration
@PropertySource("classpath:externalurl.properties")
@Component
public class ExternalUrl {

	public static final Logger logger = LoggerFactory.getLogger(ExternalUrl.class);
	@Value("${externalurl.hostname}")
	private String hostname;

	public String getUrl(String urlPattern, List<String> parameterlist) {

		String requestURL = urlPattern;
		int index = 0;
		for (String requestedParameter : parameterlist) {
			index = index+1;
			requestURL = requestURL.replace("parameter" + index, requestedParameter);
		}
		logger.info("external requestURL {} ", requestURL);
		return hostname + requestURL;
	}

}
