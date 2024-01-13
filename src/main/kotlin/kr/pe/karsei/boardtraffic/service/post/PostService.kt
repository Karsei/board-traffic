package kr.pe.karsei.boardtraffic.service.post

import kr.pe.karsei.boardtraffic.dto.PostDto
import kr.pe.karsei.boardtraffic.port.`in`.PostUseCase
import kr.pe.karsei.boardtraffic.port.out.PostLoadPort
import kr.pe.karsei.boardtraffic.port.out.PostSavePort
import kr.pe.karsei.boardtraffic.port.out.UserLoadPort
import kr.pe.karsei.boardtraffic.service.post.PostMapper.Companion.mapToEntityToPostDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException

@Service
class PostService(
        private val postLoadPort: PostLoadPort,
        private val postSavePort: PostSavePort,
        private val userLoadPort: UserLoadPort,
): PostUseCase {
    @Transactional(readOnly = true)
    override fun findPosts(params: PostDto.PostSearchRequest, pageable: Pageable): Page<PostDto> {
        return postLoadPort.findPosts(null, params, pageable)
    }

    @Transactional(readOnly = true)
    override fun findMyPosts(userId: Long, params: PostDto.PostSearchRequest, pageable: Pageable): Page<PostDto> {
        return postLoadPort.findPosts(userId, params, pageable)
    }

    @Transactional(readOnly = true)
    override fun insertPost(userId: Long, params: PostDto.InsertPostRequest): PostDto {
        val user = userLoadPort.getUserInfo(userId)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "유저가 존재하지 않습니다.")
        val post = postSavePort.insertPost(user, params)
        return mapToEntityToPostDto(post)!!
    }

    @Transactional
    override fun updatePost(userId: Long, params: PostDto.PostUpdateRequest): PostDto {
        userLoadPort.getUserInfo(userId)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "유저가 존재하지 않습니다.")
        val post = postSavePort.updatePost(params)
        return mapToEntityToPostDto(post)!!
    }

    @Transactional
    override fun deletePost(userId: Long, postId: Long): PostDto {
        userLoadPort.getUserInfo(userId)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "유저가 존재하지 않습니다.")
        val post = postSavePort.deletePost(postId)
        return mapToEntityToPostDto(post)!!
    }
}