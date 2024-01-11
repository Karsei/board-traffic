package kr.pe.karsei.boardtraffic.adapter.out

import kr.pe.karsei.boardtraffic.dto.PostDto
import kr.pe.karsei.boardtraffic.entity.Post
import kr.pe.karsei.boardtraffic.port.out.PostLoadPort
import kr.pe.karsei.boardtraffic.port.out.PostSavePort
import kr.pe.karsei.boardtraffic.repository.CategoryRepository
import kr.pe.karsei.boardtraffic.repository.FileRepository
import kr.pe.karsei.boardtraffic.repository.PostRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class PostJpaAdapter(
    private val postRepository: PostRepository,
    private val categoryRepository: CategoryRepository,
    private val fileRepository: FileRepository,
) : PostLoadPort, PostSavePort {
    override fun findPostsByUser(userId: Long, pageable: Pageable): Page<Post> {
        return postRepository.findByUserId(userId, pageable)
    }

    override fun findPosts(request: PostDto.Search.PostSearchRequest, pageable: Pageable): Page<PostDto> {
        return postRepository.findPosts(request, pageable)
    }

    override fun updatePost(request: PostDto.PostUpdateRequest) {
        val post = postRepository.findById(request.id)
            .orElseThrow { throw ResponseStatusException(HttpStatus.NOT_FOUND, "포스트가 존재하지 않습니다.") }
        val category = categoryRepository.findById(request.categoryId).orElse(null)
        val file = fileRepository.findById(request.fileId).orElse(null)
        post.update(request.title, request.contents, category, file)
        postRepository.save(post)
    }

    override fun deletePost(postId: Long) {
        postRepository.deleteById(postId)
    }
}