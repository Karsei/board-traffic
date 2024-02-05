package kr.pe.karsei.boardtraffic.core.exception

import org.springframework.http.HttpStatus

abstract class ApplicationRuntimeException(
    val httpStatus: HttpStatus,
    override val message: String,
): RuntimeException(message) {
}