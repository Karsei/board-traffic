package kr.pe.karsei.boardtraffic.application.post.exception

import org.springframework.http.HttpStatus

enum class PostErrorCode(
    val httpCode: HttpStatus,
    val message: String,
) {
    NOT_FOUND_CATEGORY(HttpStatus.NOT_FOUND, "카테고리가 존재하지 않습니다."),
    NOT_FOUND_POST(HttpStatus.NOT_FOUND, "포스트가 존재하지 않습니다."),
    NOT_FOUND_COMMENT(HttpStatus.NOT_FOUND, "댓글이 존재하지 않습니다."),
    NOT_FOUND_TAG(HttpStatus.NOT_FOUND, "태그가 존재하지 않습니다."),
}