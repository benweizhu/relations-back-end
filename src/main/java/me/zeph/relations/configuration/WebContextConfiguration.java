package me.zeph.relations.configuration;

import org.apache.commons.jexl2.JexlEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class WebContextConfiguration {

	private static final String AGCU_EX_22 = "AGCU_EX22";
	private static final String AGCU_211 = "AGCU211";
	private static final String KITS = "Kits";
	private static final String EXPRESSION = "EXPRESSION";

	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
		resourceBundleMessageSource.setBasenames(AGCU_EX_22, AGCU_211, KITS, EXPRESSION);
		return resourceBundleMessageSource;
	}

	@Bean
	public JexlEngine calculateEngine() {
		return new JexlEngine();
	}
}
