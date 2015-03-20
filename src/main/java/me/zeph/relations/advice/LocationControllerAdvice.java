package me.zeph.relations.advice;

import me.zeph.relations.controller.LocationController;
import me.zeph.relations.exception.LocationNotFoundException;
import me.zeph.relations.model.api.ApiError;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

@ControllerAdvice(assignableTypes = {LocationController.class})
@Order(HIGHEST_PRECEDENCE)
public class LocationControllerAdvice {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = LocationNotFoundException.class)
	@ResponseBody
	public ApiError handleLocationNotFoundException(LocationNotFoundException e) {
		return new ApiError(e.getMessage());
	}
}
