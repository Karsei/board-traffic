package kr.pe.karsei.boardtraffic.service.post

import kr.pe.karsei.boardtraffic.dto.TagDto
import kr.pe.karsei.boardtraffic.port.`in`.PostTagUseCase
import kr.pe.karsei.boardtraffic.port.out.PostTagSavePort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostTagService(
    private val postTagSavePort: PostTagSavePort,
): PostTagUseCase {
    @Transactional
    override fun insertTag(params: TagDto.InsertTagRequest) {
        return postTagSavePort.insertTag(params)
    }

    @Transactional
    override fun updateTag(tagId: Long, params: TagDto.UpdateTagRequest) {
        return postTagSavePort.updateTag(tagId, params)
    }

    @Transactional
    override fun deleteTag(tagId: Long) {
        return postTagSavePort.deleteTag(tagId)
    }
}