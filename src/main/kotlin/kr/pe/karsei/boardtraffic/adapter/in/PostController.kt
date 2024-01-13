package kr.pe.karsei.boardtraffic.adapter.`in`

import kr.pe.karsei.boardtraffic.aop.LoginCheck
import kr.pe.karsei.boardtraffic.dto.CategoryDto
import kr.pe.karsei.boardtraffic.dto.CommentDto
import kr.pe.karsei.boardtraffic.dto.PostDto
import kr.pe.karsei.boardtraffic.port.`in`.CategoryUseCase
import kr.pe.karsei.boardtraffic.port.`in`.PostCommentUseCase
import kr.pe.karsei.boardtraffic.port.`in`.PostUseCase
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/posts")
class PostController(
    private val postUseCase: PostUseCase,
    private val postCommentUseCase: PostCommentUseCase,
) {
    // ------------------------- POST
    @GetMapping
    fun search(params: PostDto.PostSearchRequest,
               pageable: Pageable): Page<PostDto> {
        return postUseCase.findPosts(params, pageable)
    }

    @GetMapping("my-list")
    @LoginCheck(type = LoginCheck.UserType.USER)
    fun searchMyPosts(userId: Long,
                      params: PostDto.PostSearchRequest,
                      pageable: Pageable): Page<PostDto> {
        return postUseCase.findMyPosts(userId, params, pageable)
    }

    @PostMapping
    @LoginCheck(type = LoginCheck.UserType.USER)
    fun insertPost(userId: Long,
                   @RequestBody params: PostDto.InsertPostRequest): PostDto {
        return postUseCase.insertPost(userId, params)
    }

    @PatchMapping("{postId}")
    @LoginCheck(type = LoginCheck.UserType.USER)
    fun updatePost(userId: Long,
                   @PathVariable postId: Long,
                   @RequestBody params: PostDto.PostUpdateRequest): PostDto {
        return postUseCase.updatePost(userId, params)
    }

    @DeleteMapping("{postId}")
    @LoginCheck(type = LoginCheck.UserType.USER)
    fun deletePost(userId: Long,
                   @PathVariable postId: Long): PostDto {
        return postUseCase.deletePost(userId, postId)
    }

    // ------------------------- COMMENT
    @PostMapping("{postId}/comments")
    @LoginCheck(type = LoginCheck.UserType.USER)
    fun insertComment(userId: Long,
                      @PathVariable postId: Long,
                      @RequestBody params: CommentDto.InsertCommentRequest): CommentDto {
        return postCommentUseCase.insertComment(userId, postId, params)
    }

    @PatchMapping("{postId}/comments/{commentId}")
    @LoginCheck(type = LoginCheck.UserType.USER)
    fun updateComment(userId: Long,
                      @PathVariable postId: Long,
                      @PathVariable commentId: Long,
                      @RequestBody params: CommentDto.UpdateCommentRequest): CommentDto {
        return postCommentUseCase.updateComment(userId, postId, commentId, params)
    }

    @DeleteMapping("{postId}/comments/{commentId}")
    @LoginCheck(type = LoginCheck.UserType.USER)
    fun deleteComment(userId: Long,
                      @PathVariable postId: Long,
                      @PathVariable commentId: Long): CommentDto {
        return postCommentUseCase.deleteComment(userId, postId, commentId)
    }
}