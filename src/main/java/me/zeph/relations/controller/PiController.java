package me.zeph.relations.controller;

import me.zeph.relations.model.PI;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping(value = "getpi", method = POST)
public class PiController {

	@RequestMapping(value = "parents", produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	public PI getParentsPi() {
		return new PI();
	}

	@RequestMapping(value = "oneparent", produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	public PI getOneParentPi() {
		return new PI();
	}
}
