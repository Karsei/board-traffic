package kr.pe.karsei.boardtraffic.port.out

import kr.pe.karsei.boardtraffic.dto.PostDto
import kr.pe.karsei.boardtraffic.entity.Post
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface PostLoadPort {
    fun findPostsByUser(userId: Long, pageable: Pageable): Page<Post>

    fun findPosts(request: PostDto.Search.PostSearchRequest, pageable: Pageable): Page<PostDto>

    fun updatePost(request: PostDto.PostUpdateRequest)

    fun deletePost(postId: Long)
}