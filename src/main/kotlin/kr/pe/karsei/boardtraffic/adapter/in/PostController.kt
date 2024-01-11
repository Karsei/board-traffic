package kr.pe.karsei.boardtraffic.adapter.`in`

import kr.pe.karsei.boardtraffic.dto.PostDto
import kr.pe.karsei.boardtraffic.port.`in`.PostUseCase
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/posts")
class PostController(
    private val postUseCase: PostUseCase,
) {
    @GetMapping
    fun search(request: PostDto.PostSearchRequest,
               pageable: Pageable): Page<PostDto> {
        return postUseCase.findPosts(request, pageable)
    }

    @GetMapping("my-list")
    fun searchMyPosts(request: PostDto.PostSearchRequest,
                      pageable: Pageable): Page<PostDto> {
        return postUseCase.findPosts(request, pageable)
    }

    @PatchMapping("{postId}")
    fun updatePost(@PathVariable postId: Long,
                   request: PostDto.PostUpdateRequest) {
        postUseCase.updatePost(request)
    }

    @DeleteMapping("{postId}")
    fun deletePost(@PathVariable postId: Long) {
        postUseCase.deletePost(postId)
    }
}