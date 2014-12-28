package me.zeph.relations.service;

import me.zeph.relations.constants.DataBase;
import me.zeph.relations.model.LocusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import static java.util.Locale.getDefault;
import static me.zeph.relations.constants.DataBase.AGCU_EX_22;
import static org.springframework.util.StringUtils.commaDelimitedListToStringArray;

@Service
public class LocusService {

	@Autowired
	private ApplicationContext applicationContext;

	private LocusCode locusCode;

	public LocusService() {
		locusCode = new LocusCode();
	}

	public LocusService(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
		locusCode = new LocusCode();
	}

	public LocusCode getLocusCode(String kit) {
		if (isLocusEmpty()) {
			String message = applicationContext.getMessage(kit, null, getDefault());
			locusCode.addCodes(translateLocus(message));
		}
		return locusCode;
	}

	private String[] translateLocus(String locuses) {
		return commaDelimitedListToStringArray(locuses);
	}

	private boolean isLocusEmpty() {
		return locusCode.getCodes() == null || locusCode.getCodes().isEmpty();
	}
}
