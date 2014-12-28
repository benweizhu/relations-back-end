package me.zeph.relations.controller;

import me.zeph.relations.model.LocusCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping(value = "locus", method = GET)
public class LocusController {

	@RequestMapping
	public String view() {
		return "index";
	}

	@RequestMapping(produces = {APPLICATION_JSON_VALUE})
	@ResponseBody
	public LocusCode getLocusCode() {
		LocusCode locusCode = new LocusCode();
		return locusCode;
	}
}
