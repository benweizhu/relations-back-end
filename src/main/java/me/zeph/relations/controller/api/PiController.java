package me.zeph.relations.controller.api;

import me.zeph.relations.model.*;
import me.zeph.relations.service.PiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/pi", method = POST)
public class PiController {

	@Autowired
	private PiService piService;

	@RequestMapping(value = "/parents", produces = APPLICATION_JSON_VALUE)
	public PIValue getParentsPi(@RequestBody ParentsReqParam reqParam) {
		return new PIValue(piService.calculateParentsPi(reqParam));
	}

	@RequestMapping(value = "/oneparent", produces = APPLICATION_JSON_VALUE)
	public PIValue getOneParentPi(@RequestBody OneParentReqParam reqParam) {
		return new PIValue(piService.calculateOneParentPi(reqParam));
	}

	@RequestMapping(value = "/cpi", produces = APPLICATION_JSON_VALUE)
	public CPIValue getCPI(@RequestBody CpiParam cpiParam) {
		return piService.calculateCPI(cpiParam);
	}

	@RequestMapping(value = "/rcp", produces = APPLICATION_JSON_VALUE)
	public RCPValue getRCP(@RequestBody RcpParam rcpParam) {
		return piService.calculateRCP(rcpParam);
	}

}
