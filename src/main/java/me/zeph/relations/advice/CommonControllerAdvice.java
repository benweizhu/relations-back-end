package me.zeph.relations.advice;

import me.zeph.relations.model.api.ApiError;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.*;

import static org.springframework.core.Ordered.LOWEST_PRECEDENCE;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice(annotations = {RestController.class})
@Order(LOWEST_PRECEDENCE)
public class CommonControllerAdvice {

	@ExceptionHandler(value = TypeMismatchException.class)
	@ResponseStatus(BAD_REQUEST)
	@ResponseBody
	public ApiError handleTypeMismatchException(TypeMismatchException e) {
		return new ApiError(e.getMessage());
	}

	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ApiError handleInternalServerError(Exception e) {
		e.printStackTrace();
		return new ApiError("INTERNAL SERVER ERROR");
	}
}
