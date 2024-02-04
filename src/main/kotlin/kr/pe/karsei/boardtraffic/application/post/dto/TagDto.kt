package kr.pe.karsei.boardtraffic.application.post.dto

class TagDto {
    data class InsertTagRequest(
        val name: String,
        val url: String,
    )

    data class UpdateTagRequest(
        val name: String,
        val url: String,
    )
}