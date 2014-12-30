package me.zeph.relations.controller;

import me.zeph.relations.model.OneParentReqParam;
import me.zeph.relations.model.PIValue;
import me.zeph.relations.model.ParentsReqParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping(value = "/getpi", method = POST)
public class PiController {

	@RequestMapping(value = "/parents", produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	public PIValue getParentsPi(@RequestBody ParentsReqParam reqParam) {
		return new PIValue();
	}

	@RequestMapping(value = "/oneparent", produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	public PIValue getOneParentPi(@RequestBody OneParentReqParam reqParam) {
		return new PIValue();
	}
}
