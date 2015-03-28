package me.zeph.relations.advice;

import me.zeph.relations.controller.AlleleController;
import me.zeph.relations.exception.AlleleAlreadyExistException;
import me.zeph.relations.exception.AlleleNotFoundException;
import me.zeph.relations.exception.LocusNotFoundException;
import me.zeph.relations.model.api.ApiError;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

@ControllerAdvice(assignableTypes = AlleleController.class)
@Order(HIGHEST_PRECEDENCE)
public class AlleleControllerAdvice {
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = LocusNotFoundException.class)
	@ResponseBody
	public ApiError handleLocusNotFoundException(LocusNotFoundException e) {
		return new ApiError(e.getMessage());
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = AlleleNotFoundException.class)
	@ResponseBody
	public ApiError handleAlleleNotFoundException(AlleleNotFoundException e) {
		return new ApiError(e.getMessage());
	}

	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(value = AlleleAlreadyExistException.class)
	@ResponseBody
	public ApiError handleAlleleAlreadyExistException(AlleleAlreadyExistException e) {
		return new ApiError(e.getMessage());
	}

}
