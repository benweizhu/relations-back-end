package me.zeph.relations.service;

import me.zeph.relations.model.KitApi;
import me.zeph.relations.model.api.Kit;
import me.zeph.relations.model.entity.KitEntity;
import me.zeph.relations.repository.KitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Locale.getDefault;
import static org.springframework.util.StringUtils.commaDelimitedListToStringArray;

@Service
public class KitService {

	private ApplicationContext context;
	private Kit kit;

	@Autowired
	private KitRepository kitRepository;

	@Autowired
	public KitService(ApplicationContext context) {
		this.context = context;
		kit = new Kit();
	}

	public KitService() {
	}

	public Kit getKits() {
		kit.addKit(translateLocus(context.getMessage("kits", null, getDefault())));
		return kit;
	}

	public List<KitApi> getAllKits() {
		return translateKitList(kitRepository.findAll());
	}

	private List<KitApi> translateKitList(List<KitEntity> kitEntities) {
		List<KitApi> kitApis = newArrayList();
		for (KitEntity kitEntity : kitEntities) {
			kitApis.add(translateKit(kitEntity));
		}
		return kitApis;
	}

	private KitApi translateKit(KitEntity kitEntity) {
		KitApi kitApi = new KitApi();
		kitApi.setId(kitEntity.getId());
		kitApi.setName(kitEntity.getName());
		return kitApi;
	}

	private String[] translateLocus(String locuses) {
		return commaDelimitedListToStringArray(locuses);
	}

}
