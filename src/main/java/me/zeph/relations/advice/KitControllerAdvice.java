package me.zeph.relations.advice;

import me.zeph.relations.controller.KitController;
import me.zeph.relations.exception.KitNotFoundException;
import me.zeph.relations.model.api.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(assignableTypes = {KitController.class})
public class KitControllerAdvice {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = KitNotFoundException.class)
	public ApiError handleKitNotFoundException(KitNotFoundException e) {
		return new ApiError(e.getMessage());
	}
}
