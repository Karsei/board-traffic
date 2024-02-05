package kr.pe.karsei.boardtraffic.application.post.exception

import kr.pe.karsei.boardtraffic.core.exception.ApplicationRuntimeException

class NotFoundCategoryException
    : ApplicationRuntimeException(PostErrorCode.NOT_FOUND_CATEGORY.httpCode, PostErrorCode.NOT_FOUND_CATEGORY.message)