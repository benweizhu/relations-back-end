package me.zeph.relations.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import static java.util.Locale.getDefault;

@Component
public class FormulaDao {

	private ApplicationContext context;

	@Autowired
	public FormulaDao(ApplicationContext context) {
		this.context = context;
	}

	public String getFormulaByPatten(String pattern) {
		return context.getMessage(pattern, null, getDefault());
	}
}
