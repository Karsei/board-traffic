package kr.pe.karsei.boardtraffic.port.out

import kr.pe.karsei.boardtraffic.dto.PostDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface PostLoadPort {
    fun findPostsByUser(userId: Long, pageable: Pageable)

    fun findPosts(request: PostDto.Search.PostSearchRequest, pageable: Pageable): Page<PostDto>
}