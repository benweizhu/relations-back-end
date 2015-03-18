package me.zeph.relations.service;

import me.zeph.relations.exception.KitNotFoundException;
import me.zeph.relations.model.Kit;
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

	public List<Kit> getKits() {
		return translateKitList(kitRepository.findAll());
	}

	public Kit getKit(long kitId) {
		KitEntity kitEntity = kitRepository.findOne(kitId);
		if (kitEntity != null) {
			return translateKit(kitEntity);
		} else {
			throw new KitNotFoundException("Kit " + kitId + " not exist");
		}
	}

	private List<Kit> translateKitList(List<KitEntity> kitEntities) {
		List<Kit> kits = newArrayList();
		for (KitEntity kitEntity : kitEntities) {
			kits.add(translateKit(kitEntity));
		}
		return kits;
	}

	private Kit translateKit(KitEntity kitEntity) {
		Kit kit = new Kit();
		kit.setKitId(kitEntity.getId());
		kit.setName(kitEntity.getName());
		return kit;
	}
}
