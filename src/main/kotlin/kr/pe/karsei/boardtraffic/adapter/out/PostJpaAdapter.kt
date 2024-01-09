package kr.pe.karsei.boardtraffic.adapter.out

import kr.pe.karsei.boardtraffic.dto.PostDto
import kr.pe.karsei.boardtraffic.port.out.PostLoadPort
import kr.pe.karsei.boardtraffic.port.out.PostSavePort
import kr.pe.karsei.boardtraffic.repository.PostRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class PostJpaAdapter(
    private val postRepository: PostRepository,
) : PostLoadPort, PostSavePort {
    override fun findPostsByUser(userId: Long, pageable: Pageable) {
        postRepository.findByUserId(userId, pageable)
    }

    override fun findPosts(request: PostDto.Search.PostSearchRequest, pageable: Pageable): Page<PostDto> {
        return postRepository.findPosts(request, pageable)
    }

    fun updatePost() {

    }

    fun deletePost() {

    }
}