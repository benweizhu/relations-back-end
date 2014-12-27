package me.zeph.relations.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ParentsController {
	@RequestMapping(value = "parents")
	public String view() {
		return "parents";
	}
}
