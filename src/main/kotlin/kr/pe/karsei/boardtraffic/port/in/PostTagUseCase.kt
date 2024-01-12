package kr.pe.karsei.boardtraffic.port.`in`

import kr.pe.karsei.boardtraffic.dto.TagDto

interface PostTagUseCase {
    fun insertTag(params: TagDto.InsertTagRequest)

    fun updateTag(tagId: Long, params: TagDto.UpdateTagRequest)

    fun deleteTag(tagId: Long)
}