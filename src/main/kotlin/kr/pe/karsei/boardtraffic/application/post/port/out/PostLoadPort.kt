package kr.pe.karsei.boardtraffic.application.post.port.out

import kr.pe.karsei.boardtraffic.application.post.dto.PostDto
import kr.pe.karsei.boardtraffic.application.post.domain.Post
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface PostLoadPort {
    fun findPosts(userId: Long?, request: PostDto.PostSearchRequest, pageable: Pageable): Page<PostDto>

    fun findPostsByUser(userId: Long, pageable: Pageable): Page<Post>
}