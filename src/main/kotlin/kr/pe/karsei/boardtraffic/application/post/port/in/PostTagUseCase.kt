package kr.pe.karsei.boardtraffic.application.post.port.`in`

import kr.pe.karsei.boardtraffic.application.post.dto.TagDto

interface PostTagUseCase {
    fun insertTag(params: TagDto.InsertTagRequest)

    fun updateTag(tagId: Long, params: TagDto.UpdateTagRequest)

    fun deleteTag(tagId: Long)
}