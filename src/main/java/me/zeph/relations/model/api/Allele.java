package me.zeph.relations.model.api;

import com.wordnik.swagger.annotations.ApiModel;
import org.springframework.hateoas.ResourceSupport;

@ApiModel
public class Allele extends ResourceSupport {
	private int id;
	private String name;
}
