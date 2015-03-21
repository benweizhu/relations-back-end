package me.zeph.relations.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import me.zeph.relations.model.api.Kit;
import me.zeph.relations.service.KitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@Api(value = "Kit", position = 0)
@RequestMapping(value = "/kits")
public class KitController {

	@Autowired
	private KitService kitService;

	@ApiOperation(value = "Get Kits")
	@RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(value = OK)
	public List<Kit> getKits() {
		List<Kit> kits = kitService.getKits();
		for (Kit kit : kits) {
			kit.add(linkTo(methodOn(KitController.class).getKit(kit.getKitId())).withSelfRel());
		}
		return kits;
	}

	@ApiOperation(value = "Get Kit by Id")
	@RequestMapping(value = "/{kitId}", method = GET, produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(value = OK)
	public Kit getKit(@PathVariable long kitId) {
		Kit kit = kitService.getKit(kitId);
		kit.add(linkTo(methodOn(KitController.class).getKit(kitId)).withSelfRel());
		return kit;
	}

	@ApiOperation(value = "Save Kit by Name")
	@RequestMapping(method = POST)
	@ResponseStatus(value = CREATED)
	public ResponseEntity<?> createCustomer(UriComponentsBuilder uriComponentsBuilder, @RequestBody Kit requestKit) {
		Kit kit = kitService.addKit(requestKit.getName());
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriComponentsBuilder.path("/kits/{kitId}").buildAndExpand(kit.getKitId()).toUri());
		return new ResponseEntity<Void>(headers, CREATED);
	}

}
