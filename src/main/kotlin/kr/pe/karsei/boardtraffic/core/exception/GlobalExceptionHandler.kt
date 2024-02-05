package kr.pe.karsei.boardtraffic.core.exception

import kr.pe.karsei.boardtraffic.core.dto.rest.ErrorResponse
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(ApplicationRuntimeException::class)
    fun applicationRuntimeExceptionHandler(exception: ApplicationRuntimeException): ErrorResponse {
        return ErrorResponse(exception.httpStatus.name, exception.message)
    }
}