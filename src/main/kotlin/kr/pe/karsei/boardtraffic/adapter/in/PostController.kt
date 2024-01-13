package kr.pe.karsei.boardtraffic.adapter.`in`

import kr.pe.karsei.boardtraffic.aop.LoginCheck
import kr.pe.karsei.boardtraffic.dto.CommentDto
import kr.pe.karsei.boardtraffic.dto.PostDto
import kr.pe.karsei.boardtraffic.port.`in`.PostCommentUseCase
import kr.pe.karsei.boardtraffic.port.`in`.PostUseCase
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
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
    @ResponseStatus(HttpStatus.CREATED)
    fun insertPost(userId: Long,
                   @RequestBody params: PostDto.InsertPostRequest): PostDto {
        params.setMandatory(userId)
        return postUseCase.insertPost(params)
    }

    @PatchMapping("{postId}")
    @LoginCheck(type = LoginCheck.UserType.USER)
    fun updatePost(userId: Long,
                   @PathVariable postId: Long,
                   @RequestBody params: PostDto.UpdatePostRequest): PostDto {
        params.setMandatory(userId, postId)
        return postUseCase.updatePost(params)
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
    @ResponseStatus(HttpStatus.CREATED)
    fun insertComment(userId: Long,
                      @PathVariable postId: Long,
                      @RequestBody params: CommentDto.InsertCommentRequest): CommentDto {
        params.setMandatory(userId, postId)
        return postCommentUseCase.insertComment(params)
    }

    @PatchMapping("{postId}/comments/{commentId}")
    @LoginCheck(type = LoginCheck.UserType.USER)
    fun updateComment(userId: Long,
                      @PathVariable postId: Long,
                      @PathVariable commentId: Long,
                      @RequestBody params: CommentDto.UpdateCommentRequest): CommentDto {
        params.setMandatory(userId, postId, commentId)
        return postCommentUseCase.updateComment(params)
    }

    @DeleteMapping("{postId}/comments/{commentId}")
    @LoginCheck(type = LoginCheck.UserType.USER)
    fun deleteComment(userId: Long,
                      @PathVariable postId: Long,
                      @PathVariable commentId: Long): CommentDto {
        return postCommentUseCase.deleteComment(userId, postId, commentId)
    }
}