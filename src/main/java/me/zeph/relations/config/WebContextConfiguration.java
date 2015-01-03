package me.zeph.relations.config;

import me.zeph.relations.service.pi.formula.OneParentFormula;
import me.zeph.relations.service.pi.formula.oneparent.OneInFourQOneParentFormula;
import me.zeph.relations.service.pi.formula.oneparent.OneInQOneParentFormula;
import me.zeph.relations.service.pi.formula.oneparent.OneInTwoQOneParentFormula;
import me.zeph.relations.service.pi.formula.oneparent.PPlusQInFourPQOneParentFormula;
import org.apache.commons.jexl2.JexlEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.accept.ContentNegotiationManagerFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.springframework.http.MediaType.TEXT_HTML;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "me.zeph.relations")
public class WebContextConfiguration {
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
		resourceBundleMessageSource.setBasenames("AGCU_EX22", "AGCU211", "Kits");
		return resourceBundleMessageSource;
	}

	@Bean
	public List<OneParentFormula> oneParentFormulas() {
		return newArrayList(oneInQFormula(), oneInTwoQFormula(),
				pPlusQInFourPQFormula(), oneInFourQFormula());
	}

	@Bean
	public OneParentFormula oneInQFormula() {
		return new OneInQOneParentFormula();
	}

	@Bean
	public OneParentFormula oneInTwoQFormula() {
		return new OneInTwoQOneParentFormula();
	}

	@Bean
	public OneParentFormula pPlusQInFourPQFormula() {
		return new PPlusQInFourPQOneParentFormula();
	}

	@Bean
	public OneParentFormula oneInFourQFormula() {
		return new OneInFourQOneParentFormula();
	}

	@Bean
	public JexlEngine calculateEngine() {
		return new JexlEngine();
	}
}
