package me.zeph.relations.service;

import me.zeph.relations.model.LocusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class LocusService {

	@Autowired
	private ApplicationContext applicationContext;

	public LocusService() {
	}

	public LocusService(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public LocusCode getLocusCode() {
		LocusCode locusCode = new LocusCode();
		return locusCode;
	}
}
