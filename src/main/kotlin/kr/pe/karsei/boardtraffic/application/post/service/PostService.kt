package kr.pe.karsei.boardtraffic.application.post.service

import kr.pe.karsei.boardtraffic.application.client.exception.NotFoundClientException
import kr.pe.karsei.boardtraffic.application.post.dto.PostDto
import kr.pe.karsei.boardtraffic.application.post.port.`in`.PostUseCase
import kr.pe.karsei.boardtraffic.application.post.port.out.PostLoadPort
import kr.pe.karsei.boardtraffic.application.post.port.out.PostSavePort
import kr.pe.karsei.boardtraffic.application.client.port.out.UserLoadPort
import kr.pe.karsei.boardtraffic.application.post.service.PostMapper.Companion.mapToEntityToPostDto
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostService(
    private val postLoadPort: PostLoadPort,
    private val postSavePort: PostSavePort,
    private val userLoadPort: UserLoadPort,
): PostUseCase {
    @Async
    @Cacheable(value = ["findPosts"], key = "'findPosts' + #params.title + #params.categoryId")
    @Transactional(readOnly = true)
    override fun findPosts(params: PostDto.PostSearchRequest, pageable: Pageable): Page<PostDto> {
        return postLoadPort.findPosts(null, params, pageable)
    }

    @Transactional(readOnly = true)
    override fun findMyPosts(userId: Long, params: PostDto.PostSearchRequest, pageable: Pageable): Page<PostDto> {
        return postLoadPort.findPosts(userId, params, pageable)
    }

    @CacheEvict(value = ["findPosts"], allEntries = true)
    @Transactional(readOnly = true)
    override fun insertPost(params: PostDto.InsertPostRequest): PostDto {
        val user = userLoadPort.getUserInfo(params.userId!!)
                ?: throw NotFoundClientException()
        val post = postSavePort.insertPost(user, params)
        return mapToEntityToPostDto(post)!!
    }

    @Transactional
    override fun updatePost(params: PostDto.UpdatePostRequest): PostDto {
        userLoadPort.getUserInfo(params.userId!!)
                ?: throw NotFoundClientException()
        val post = postSavePort.updatePost(params)
        return mapToEntityToPostDto(post)!!
    }

    @Transactional
    override fun deletePost(userId: Long, postId: Long): PostDto {
        userLoadPort.getUserInfo(userId)
                ?: throw NotFoundClientException()
        val post = postSavePort.deletePost(postId)
        return mapToEntityToPostDto(post)!!
    }
}