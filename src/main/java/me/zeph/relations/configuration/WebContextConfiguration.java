package me.zeph.relations.configuration;

import org.apache.commons.jexl2.JexlEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class WebContextConfiguration {

	private static final String EXPRESSION = "EXPRESSION";

	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
		resourceBundleMessageSource.setBasenames(EXPRESSION);
		return resourceBundleMessageSource;
	}

	@Bean
	public JexlEngine calculateEngine() {
		return new JexlEngine();
	}
}
