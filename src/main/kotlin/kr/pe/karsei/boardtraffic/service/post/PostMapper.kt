package kr.pe.karsei.boardtraffic.service.post

import kr.pe.karsei.boardtraffic.dto.PostDto
import kr.pe.karsei.boardtraffic.entity.Post

class PostMapper {
    companion object {
        fun mapToEntityToDto(entity: Post?): PostDto? {
            if (entity == null) return null
            return PostDto(
                    id = entity.id,
                    title = entity.title,
                    isAdmin = entity.isAdmin,
                    contents = entity.contents,
                    views = entity.views,
                    categoryId = entity.category.id,
                    userId = entity.user.id,
                    fileId = entity.file?.id,
                    createdAt = entity.createdAt,
                    updatedAt = entity.updatedAt,
            )
        }
    }
}