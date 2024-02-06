package kr.pe.karsei.boardtraffic.application.client.exception

import kr.pe.karsei.boardtraffic.core.exception.ApplicationRuntimeException

class NotFoundOrInvalidPasswordClientException
    : ApplicationRuntimeException(ClientErrorCode.NOT_FOUND_OR_INVALID_PASSWORD.httpCode, ClientErrorCode.NOT_FOUND_OR_INVALID_PASSWORD.message)