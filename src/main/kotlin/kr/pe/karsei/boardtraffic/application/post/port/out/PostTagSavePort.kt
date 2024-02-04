package kr.pe.karsei.boardtraffic.application.post.port.out

import kr.pe.karsei.boardtraffic.application.post.dto.TagDto

interface PostTagSavePort {
    fun insertTag(params: TagDto.InsertTagRequest)

    fun updateTag(tagId: Long, params: TagDto.UpdateTagRequest)

    fun deleteTag(tagId: Long)
}