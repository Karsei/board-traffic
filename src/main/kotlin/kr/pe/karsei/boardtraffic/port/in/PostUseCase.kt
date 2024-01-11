package kr.pe.karsei.boardtraffic.port.`in`

import kr.pe.karsei.boardtraffic.dto.PostDto
import kr.pe.karsei.boardtraffic.entity.Post
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface PostUseCase {
    fun findMyPosts(userId: Long, pageable: Pageable): Page<Post>

    fun findPosts(postDto: PostDto.PostSearchRequest, pageable: Pageable): Page<PostDto>

    fun updatePost(params: PostDto.PostUpdateRequest)

    fun deletePost(postId: Long)
}