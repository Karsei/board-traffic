package kr.pe.karsei.boardtraffic.application.post.exception

import kr.pe.karsei.boardtraffic.core.exception.ApplicationRuntimeException

class NotFoundCommentException
    : ApplicationRuntimeException(PostErrorCode.NOT_FOUND_COMMENT.httpCode, PostErrorCode.NOT_FOUND_COMMENT.message)