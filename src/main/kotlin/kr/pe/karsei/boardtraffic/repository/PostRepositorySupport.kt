package kr.pe.karsei.boardtraffic.repository

import kr.pe.karsei.boardtraffic.dto.PostDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface PostRepositorySupport {
    fun findPosts(request: PostDto.Search.PostSearchRequest, pageable: Pageable): Page<PostDto>
}