package kr.pe.karsei.boardtraffic.application.post.service

import kr.pe.karsei.boardtraffic.application.post.dto.CategoryDto
import kr.pe.karsei.boardtraffic.application.post.dto.CommentDto
import kr.pe.karsei.boardtraffic.application.post.dto.PostDto
import kr.pe.karsei.boardtraffic.application.post.domain.Category
import kr.pe.karsei.boardtraffic.application.post.domain.Comment
import kr.pe.karsei.boardtraffic.application.post.domain.Post

class PostMapper {
    companion object {
        fun mapToEntityToPostDto(entity: Post?): PostDto? {
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

        fun mapToEntityToPostCommentDto(entity: Comment?): CommentDto? {
            if (entity == null) return null
            return CommentDto(
                    id = entity.id,
                    contents = entity.contents,
                    subCommentId = entity.subComment?.id,
            )
        }

        fun mapToEntityToCategoryDto(entity: Category?): CategoryDto? {
            if (entity == null) return null
            return CategoryDto(
                    id = entity.id,
                    title = entity.title
            )
        }
    }
}