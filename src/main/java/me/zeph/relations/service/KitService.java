package me.zeph.relations.service;

import me.zeph.relations.exception.KitAlreadyExistException;
import me.zeph.relations.exception.KitNotFoundException;
import me.zeph.relations.model.api.Kit;
import me.zeph.relations.model.entity.KitEntity;
import me.zeph.relations.repository.KitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.String.format;
import static me.zeph.relations.exception.ExceptionMessage.KIT_ALREADY_EXIST;
import static me.zeph.relations.exception.ExceptionMessage.KIT_NOT_FOUND;

@Service
public class KitService {

	@Autowired
	private KitRepository kitRepository;

	public List<Kit> getKits() {
		return translateKitList(kitRepository.findAll());
	}

	public Kit getKit(long kitId) {
		KitEntity kitEntity = kitRepository.findOne(kitId);
		if (kitEntity == null) {
			throw new KitNotFoundException(format(KIT_NOT_FOUND, kitId));
		} else {
			return translateKit(kitEntity);
		}
	}

	public Kit addKit(String name) {
		List<KitEntity> kitEntities = kitRepository.findByName(name);
		if (!kitEntities.isEmpty()) {
			throw new KitAlreadyExistException(format(KIT_ALREADY_EXIST, name));
		} else {
			return translateKit(kitRepository.saveAndFlush(new KitEntity(name)));
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
