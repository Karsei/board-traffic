package kr.pe.karsei.boardtraffic.service

import kr.pe.karsei.boardtraffic.dto.PostDto
import kr.pe.karsei.boardtraffic.entity.Post
import kr.pe.karsei.boardtraffic.port.`in`.PostUseCase
import kr.pe.karsei.boardtraffic.port.out.PostLoadPort
import kr.pe.karsei.boardtraffic.port.out.PostSavePort
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostService(
    private val postLoadPort: PostLoadPort,
    private val postSavePort: PostSavePort,
): PostUseCase {
    @Transactional(readOnly = true)
    override fun findPosts(postDto: PostDto.PostSearchRequest, pageable: Pageable): Page<PostDto> {
        return postLoadPort.findPosts(postDto, pageable)
    }

    @Transactional(readOnly = true)
    override fun findMyPosts(userId: Long, pageable: Pageable): Page<Post> {
        return postLoadPort.findPostsByUser(userId, pageable)
    }

    @Transactional
    override fun updatePost(params: PostDto.PostUpdateRequest) {
        postSavePort.updatePost(params)
    }

    @Transactional
    override fun deletePost(postId: Long) {
        postSavePort.deletePost(postId)
    }
}