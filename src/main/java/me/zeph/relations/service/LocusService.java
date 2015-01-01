package me.zeph.relations.service;

import me.zeph.relations.model.LocusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import static java.util.Locale.getDefault;
import static org.springframework.util.StringUtils.commaDelimitedListToStringArray;

@Service
public class LocusService {

	@Autowired
	private ApplicationContext context;

	private LocusCode locusCode;

	public LocusService() {
		locusCode = new LocusCode();
	}

	public LocusService(ApplicationContext context) {
		this.context = context;
		locusCode = new LocusCode();
	}

	public LocusCode getLocusCode(String kit) {
		locusCode.addCodes(translateLocus(context.getMessage(kit, null, getDefault())));
		return locusCode;
	}

	private String[] translateLocus(String locuses) {
		return commaDelimitedListToStringArray(locuses);
	}

}
