package me.zeph.relations.controller.api;

import me.zeph.relations.model.Kit;
import me.zeph.relations.service.KitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = "/kits", method = GET)
public class KitController {

	@Autowired
	private KitService kitService;

	@RequestMapping(produces = APPLICATION_JSON_VALUE)
	public Kit getKits() {
		return kitService.getKits();
	}
}
