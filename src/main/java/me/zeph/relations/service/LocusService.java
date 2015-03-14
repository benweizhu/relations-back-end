package me.zeph.relations.service;

import me.zeph.relations.exception.KitNotExistException;
import me.zeph.relations.model.api.LocusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Service;

import static java.lang.String.format;
import static java.util.Locale.getDefault;
import static org.springframework.util.StringUtils.commaDelimitedListToStringArray;

@Service
public class LocusService {

	private ApplicationContext context;
	private LocusCode locusCode;

	@Autowired
	public LocusService(ApplicationContext context) {
		this.context = context;
		locusCode = new LocusCode();
	}

	public LocusCode getLocusCode(String kit) throws KitNotExistException {
		try {
			locusCode.addCodes(translateLocus(context.getMessage(kit, null, getDefault())));
		} catch (NoSuchMessageException ex) {
			throw new KitNotExistException(format("Kit does not exist: %s", kit));
		}
		return locusCode;
	}

	private String[] translateLocus(String loci) {
		return commaDelimitedListToStringArray(loci);
	}

}
