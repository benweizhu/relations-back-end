package me.zeph.relations.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import me.zeph.relations.model.api.Location;
import me.zeph.relations.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@Api(value = "Locations", position = 2)
@RequestMapping(value = "/locations")
public class LocationController {

	@Autowired
	private LocationService locationService;

	@RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get Locations")
	@ResponseStatus(value = OK)
	public List<Location> getLocations() {
		List<Location> locations = locationService.getLocations();
		for (Location location : locations) {
			location.add(selfLink(location.getLocationId()));
		}
		return locations;
	}

	@RequestMapping(value = "/{locationId}", method = GET, produces = APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get Locations By Id")
	@ResponseStatus(value = OK)
	public Location getLocation(@PathVariable long locationId) {
		Location location = locationService.getLocation(locationId);
		location.add(selfLink(locationId));
		return location;
	}

	private Link selfLink(long locationId) {
		return linkTo(methodOn(LocationController.class).getLocation(locationId)).withSelfRel();
	}
}
