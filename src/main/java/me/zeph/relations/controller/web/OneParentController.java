package me.zeph.relations.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping(value = "/oneparent", method = GET)
public class OneParentController {
	@RequestMapping
	public String view() {
		return "oneParent";
	}
}
