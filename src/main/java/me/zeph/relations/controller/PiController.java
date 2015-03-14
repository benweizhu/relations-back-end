package me.zeph.relations.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import me.zeph.relations.model.*;
import me.zeph.relations.service.PiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@Api(value = "Pi", position = 2)
@RequestMapping(value = "/pi", method = POST)
public class PiController {

	@Autowired
	private PiService piService;

	@ApiOperation(value = "Get Parents Pi")
	@RequestMapping(value = "/parents", produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(OK)
	public PIValue getParentsPi(@RequestBody ParentsReqParam reqParam) {
		return new PIValue(piService.calculateParentsPi(reqParam));
	}

	@ApiOperation(value = "Get One Parent Pi")
	@RequestMapping(value = "/oneparent", produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(OK)
	public PIValue getOneParentPi(@RequestBody OneParentReqParam reqParam) {
		return new PIValue(piService.calculateOneParentPi(reqParam));
	}

	@ApiOperation(value = "Get Cpi")
	@RequestMapping(value = "/cpi", produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(OK)
	public CPIValue getCPI(@RequestBody CpiParam cpiParam) {
		return piService.calculateCPI(cpiParam);
	}

	@ApiOperation(value = "Get Rcp")
	@RequestMapping(value = "/rcp", produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(OK)
	public RCPValue getRCP(@RequestBody RcpParam rcpParam) {
		return piService.calculateRCP(rcpParam);
	}

}
