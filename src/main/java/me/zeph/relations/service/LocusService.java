package me.zeph.relations.service;

import me.zeph.relations.messages.KeysResourceBundleMessageSource;
import me.zeph.relations.model.LocusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

import static java.util.Locale.getDefault;

@Service
public class LocusService {

	public static final String baseName = "database";

	@Autowired
	private KeysResourceBundleMessageSource messageSource;
	private final LocusCode locusCode;

	public LocusService() {
		locusCode = new LocusCode();
	}

	public LocusService(KeysResourceBundleMessageSource messageSource) {
		this.messageSource = messageSource;
		locusCode = new LocusCode();
	}

	public LocusCode getLocusCode() {
		if (isLocusEmpty()) {
			locusCode.addCodes(messageSource.getKeys(baseName, getDefault()));
		}
		return locusCode;
	}

	private boolean isLocusEmpty() {
		return locusCode.getCodes() == null || locusCode.getCodes().isEmpty();
	}
}
