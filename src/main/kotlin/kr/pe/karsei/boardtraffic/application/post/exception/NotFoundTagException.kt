package kr.pe.karsei.boardtraffic.application.post.exception

import kr.pe.karsei.boardtraffic.core.exception.ApplicationRuntimeException

class NotFoundTagException
    : ApplicationRuntimeException(PostErrorCode.NOT_FOUND_TAG.httpCode, PostErrorCode.NOT_FOUND_TAG.message)