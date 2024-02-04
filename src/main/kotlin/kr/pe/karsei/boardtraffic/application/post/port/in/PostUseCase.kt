package kr.pe.karsei.boardtraffic.application.post.port.`in`

import kr.pe.karsei.boardtraffic.application.post.dto.PostDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface PostUseCase {
    fun findPosts(params: PostDto.PostSearchRequest, pageable: Pageable): Page<PostDto>

    fun findMyPosts(userId: Long, params: PostDto.PostSearchRequest, pageable: Pageable): Page<PostDto>

    fun insertPost(params: PostDto.InsertPostRequest): PostDto

    fun updatePost(params: PostDto.UpdatePostRequest): PostDto

    fun deletePost(userId: Long, postId: Long): PostDto
}