package me.zeph.relations.configuration;

import org.apache.commons.jexl2.JexlEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.accept.ContentNegotiationManagerFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import static org.springframework.http.MediaType.TEXT_HTML;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "me.zeph.relations")
public class WebContextConfiguration {

	private static final String AGCU_EX_22 = "AGCU_EX22";
	private static final String AGCU_211 = "AGCU211";
	private static final String KITS = "Kits";
	private static final String EXPRESSION = "EXPRESSION";

	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
		internalResourceViewResolver.setViewClass(JstlView.class);
		internalResourceViewResolver.setPrefix("/WEB-INF/view/");
		internalResourceViewResolver.setSuffix(".jsp");
		return internalResourceViewResolver;
	}

	@Bean
	public ContentNegotiationManagerFactoryBean contentNegotiationManagerFactoryBean() {
		ContentNegotiationManagerFactoryBean contentNegotiation = new ContentNegotiationManagerFactoryBean();
		contentNegotiation.setFavorPathExtension(false);
		contentNegotiation.setFavorParameter(false);
		contentNegotiation.setDefaultContentType(TEXT_HTML);
		return contentNegotiation;
	}

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
