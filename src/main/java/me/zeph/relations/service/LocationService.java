package me.zeph.relations.service;

import me.zeph.relations.exception.LocationNotFoundException;
import me.zeph.relations.model.api.Location;
import me.zeph.relations.model.entity.LocationEntity;
import me.zeph.relations.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.String.format;

@Service
public class LocationService {

	@Autowired
	private LocationRepository locationRepository;

	public List<Location> getLocations() {
		return translateToApi(locationRepository.findAll());
	}

	private List<Location> translateToApi(List<LocationEntity> locationEntities) {
		List<Location> locations = newArrayList();
		for (LocationEntity locationEntity : locationEntities) {
			locations.add(translateLocation(locationEntity.getId(), locationEntity.getName()));
		}
		return locations;
	}

	public Location getLocation(long locationId) {
		LocationEntity locationEntity = locationRepository.findOne(locationId);
		if (locationEntity == null) {
			throw new LocationNotFoundException(format("Location %d not found", locationId));
		} else {
			return translateLocation(locationEntity.getId(), locationEntity.getName());
		}
	}

	private Location translateLocation(long id, String name) {
		Location location = new Location();
		location.setLocationId(id);
		location.setName(name);
		return location;
	}
}
