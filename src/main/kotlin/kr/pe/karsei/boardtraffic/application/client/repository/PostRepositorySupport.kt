package kr.pe.karsei.boardtraffic.application.client.repository

import kr.pe.karsei.boardtraffic.application.post.dto.PostDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface PostRepositorySupport {
    fun findPosts(userId: Long?, request: PostDto.PostSearchRequest, pageable: Pageable): Page<PostDto>
}