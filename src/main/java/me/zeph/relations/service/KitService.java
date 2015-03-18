package me.zeph.relations.service;

import me.zeph.relations.model.KitApi;
import me.zeph.relations.model.entity.KitEntity;
import me.zeph.relations.repository.KitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Service
public class KitService {

	@Autowired
	private KitRepository kitRepository;

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

}
