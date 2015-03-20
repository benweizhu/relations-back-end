package me.zeph.relations.controller;

import me.zeph.relations.model.api.Location;
import me.zeph.relations.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/locations")
public class LocationController {

	@Autowired
	private LocationService locationService;

	@RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<Location> getLocations() {
		return locationService.getLocations();
	}

	@RequestMapping(value = "/{locationId}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Location getLocation(@PathVariable long locationId) {
		return locationService.getLocation(locationId);
	}
}
