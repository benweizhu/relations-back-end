package me.zeph.relations.service;

import me.zeph.relations.model.Kit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import static java.util.Locale.getDefault;
import static org.springframework.util.StringUtils.commaDelimitedListToStringArray;

@Service
public class KitService {

	private ApplicationContext context;
	private Kit kit;

	@Autowired
	public KitService(ApplicationContext context) {
		this.context = context;
		kit = new Kit();
	}

	public Kit getKits() {
		kit.addKit(translateLocus(context.getMessage("kits", null, getDefault())));
		return kit;
	}

	private String[] translateLocus(String locuses) {
		return commaDelimitedListToStringArray(locuses);
	}

}
