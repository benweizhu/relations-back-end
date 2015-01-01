package me.zeph.relations.controller.api;

import me.zeph.relations.model.Kit;
import me.zeph.relations.service.KitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@RequestMapping(value = "/kits")
public class KitController {

	private KitService kitService;

	@Autowired
	public KitController(KitService kitService) {
		this.kitService = kitService;
	}

	@RequestMapping(produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	public Kit getKits() {
		return kitService.getKits();
	}
}
