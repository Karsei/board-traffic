package kr.pe.karsei.boardtraffic.application.client.exception

import org.springframework.http.HttpStatus

enum class ClientErrorCode(
    val httpCode: HttpStatus,
    val message: String,
) {
    CONFLICT(HttpStatus.CONFLICT, "중복된 아이디입니다."),
    NOT_FOUND_OR_INVALID_PASSWORD(HttpStatus.NOT_FOUND, "올바르지 않는 아이디이거나 비밀번호입니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 계정입니다."),
}