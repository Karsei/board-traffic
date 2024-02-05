package kr.pe.karsei.boardtraffic.application.post.exception

import kr.pe.karsei.boardtraffic.core.exception.ApplicationRuntimeException

class NotFoundPostException
    : ApplicationRuntimeException(PostErrorCode.NOT_FOUND_POST.httpCode, PostErrorCode.NOT_FOUND_POST.message)