package kr.pe.karsei.boardtraffic.port.`in`

import kr.pe.karsei.boardtraffic.dto.PostDto
import kr.pe.karsei.boardtraffic.entity.Post
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface PostUseCase {
    fun findPosts(params: PostDto.PostSearchRequest, pageable: Pageable): Page<PostDto>

    fun findMyPosts(userId: Long, params: PostDto.PostSearchRequest, pageable: Pageable): Page<PostDto>

    fun insertPost(userId: Long, params: PostDto.InsertPostRequest)

    fun updatePost(userId: Long, params: PostDto.PostUpdateRequest)

    fun deletePost(userId: Long, postId: Long)
}