package kr.pe.karsei.boardtraffic.application.client.exception

import kr.pe.karsei.boardtraffic.core.exception.ApplicationRuntimeException

class NotFoundClientException
    : ApplicationRuntimeException(ClientErrorCode.NOT_FOUND.httpCode, ClientErrorCode.NOT_FOUND.message)