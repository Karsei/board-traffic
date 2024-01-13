package kr.pe.karsei.boardtraffic.adapter.`in`

import kr.pe.karsei.boardtraffic.aop.LoginCheck
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
    // ------------------------- POST
    @GetMapping
    fun search(request: PostDto.PostSearchRequest,
               pageable: Pageable): Page<PostDto> {
        return postUseCase.findPosts(request, pageable)
    }

    @GetMapping("my-list")
    @LoginCheck(type = LoginCheck.UserType.USER)
    fun searchMyPosts(userId: Long,
                      request: PostDto.PostSearchRequest,
                      pageable: Pageable): Page<PostDto> {
        return postUseCase.findMyPosts(userId, request, pageable)
    }

    @PostMapping
    @LoginCheck(type = LoginCheck.UserType.USER)
    fun insertPost(userId: Long,
                   @RequestBody request: PostDto.InsertPostRequest) {
        return postUseCase.insertPost(userId, request)
    }

    @PatchMapping("{postId}")
    @LoginCheck(type = LoginCheck.UserType.USER)
    fun updatePost(userId: Long,
                   @PathVariable postId: Long,
                   request: PostDto.PostUpdateRequest) {
        postUseCase.updatePost(userId, request)
    }

    @DeleteMapping("{postId}")
    @LoginCheck(type = LoginCheck.UserType.USER)
    fun deletePost(userId: Long,
                   @PathVariable postId: Long) {
        postUseCase.deletePost(userId, postId)
    }
}