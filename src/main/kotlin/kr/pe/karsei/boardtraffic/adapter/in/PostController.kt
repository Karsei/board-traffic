package kr.pe.karsei.boardtraffic.adapter.`in`

import kr.pe.karsei.boardtraffic.aop.LoginCheck
import kr.pe.karsei.boardtraffic.dto.CategoryDto
import kr.pe.karsei.boardtraffic.dto.PostDto
import kr.pe.karsei.boardtraffic.port.`in`.CategoryUseCase
import kr.pe.karsei.boardtraffic.port.`in`.PostUseCase
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/posts")
class PostController(
    private val postUseCase: PostUseCase,
    private val categoryUseCase: CategoryUseCase,
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
                   @RequestBody request: PostDto.InsertPostRequest): PostDto {
        return postUseCase.insertPost(userId, request)
    }

    @PatchMapping("{postId}")
    @LoginCheck(type = LoginCheck.UserType.USER)
    fun updatePost(userId: Long,
                   @PathVariable postId: Long,
                   @RequestBody request: PostDto.PostUpdateRequest): PostDto {
        return postUseCase.updatePost(userId, request)
    }

    @DeleteMapping("{postId}")
    @LoginCheck(type = LoginCheck.UserType.USER)
    fun deletePost(userId: Long,
                   @PathVariable postId: Long): PostDto {
        return postUseCase.deletePost(userId, postId)
    }

    // ------------------------- CATEGORY
    @PostMapping
    @LoginCheck(type = LoginCheck.UserType.ADMIN)
    fun insertCategory(userId: Long,
                       @RequestBody request: CategoryDto.InsertPostCategory): CategoryDto {
        return categoryUseCase.insertCategory(request)
    }

    @PatchMapping("{categoryId}")
    @LoginCheck(type = LoginCheck.UserType.ADMIN)
    fun updateCategory(userId: Long,
                       @PathVariable categoryId: Long,
                       @RequestBody request: CategoryDto.UpdatePostCategory): CategoryDto {
        return categoryUseCase.updateCategory(categoryId, request)
    }

    @DeleteMapping("{categoryId}")
    @LoginCheck(type = LoginCheck.UserType.ADMIN)
    fun deleteCategory(userId: Long,
                       @PathVariable categoryId: Long): CategoryDto {
        return categoryUseCase.deleteCategory(categoryId)
    }
}