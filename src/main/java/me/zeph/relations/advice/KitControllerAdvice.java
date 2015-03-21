package me.zeph.relations.advice;

import me.zeph.relations.controller.KitController;
import me.zeph.relations.exception.KitAlreadyExistException;
import me.zeph.relations.exception.KitNotFoundException;
import me.zeph.relations.model.api.ApiError;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

@ControllerAdvice(assignableTypes = {KitController.class})
@Order(HIGHEST_PRECEDENCE)
public class KitControllerAdvice {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = KitNotFoundException.class)
	@ResponseBody
	public ApiError handleKitNotFoundException(KitNotFoundException e) {
		return new ApiError(e.getMessage());
	}

	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(value = KitAlreadyExistException.class)
	@ResponseBody
	public ApiError handleKitAlreadyExistException(KitAlreadyExistException e) {
		return new ApiError(e.getMessage());
	}

}
