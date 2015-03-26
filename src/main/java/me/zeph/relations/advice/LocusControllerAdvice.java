package me.zeph.relations.advice;

import me.zeph.relations.controller.LocusController;
import me.zeph.relations.exception.KitNotFoundException;
import me.zeph.relations.exception.LocusAlreadyExistException;
import me.zeph.relations.exception.LocusNotFoundException;
import me.zeph.relations.model.api.ApiError;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

@ControllerAdvice(assignableTypes = {LocusController.class})
@Order(HIGHEST_PRECEDENCE)
public class LocusControllerAdvice {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = LocusNotFoundException.class)
	@ResponseBody
	public ApiError handleLocusNotFoundException(LocusNotFoundException e) {
		return new ApiError(e.getMessage());
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = KitNotFoundException.class)
	@ResponseBody
	public ApiError handleKitNotFoundException(KitNotFoundException e) {
		return new ApiError(e.getMessage());
	}

	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(value = LocusAlreadyExistException.class)
	@ResponseBody
	public ApiError handleLocusAlreadyExistException(LocusAlreadyExistException e) {
		return new ApiError(e.getMessage());
	}

}
