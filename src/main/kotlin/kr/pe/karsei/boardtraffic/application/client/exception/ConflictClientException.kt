package kr.pe.karsei.boardtraffic.application.client.exception

import kr.pe.karsei.boardtraffic.core.exception.ApplicationRuntimeException

class ConflictClientException
    : ApplicationRuntimeException(ClientErrorCode.CONFLICT.httpCode, ClientErrorCode.CONFLICT.message)