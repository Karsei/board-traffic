package kr.pe.karsei.boardtraffic.application.post.adapter.`in`

import kr.pe.karsei.boardtraffic.application.client.aop.LoginCheck
import kr.pe.karsei.boardtraffic.application.post.dto.CategoryDto
import kr.pe.karsei.boardtraffic.application.post.port.`in`.CategoryUseCase
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/categories")
class CategoryController(
    private val categoryUseCase: CategoryUseCase,
) {
    @PostMapping
    @LoginCheck(type = LoginCheck.UserType.ADMIN)
    @ResponseStatus(HttpStatus.CREATED)
    fun insertCategory(userId: Long,
                       @RequestBody params: CategoryDto.InsertPostCategory): CategoryDto {
        params.setMandatory(userId)
        return categoryUseCase.insertCategory(params)
    }

    @PatchMapping("{categoryId}")
    @LoginCheck(type = LoginCheck.UserType.ADMIN)
    fun updateCategory(userId: Long,
                       @PathVariable categoryId: Long,
                       @RequestBody params: CategoryDto.UpdatePostCategory): CategoryDto {
        params.setMandatory(userId, categoryId)
        return categoryUseCase.updateCategory(params)
    }

    @DeleteMapping("{categoryId}")
    @LoginCheck(type = LoginCheck.UserType.ADMIN)
    fun deleteCategory(userId: Long,
                       @PathVariable categoryId: Long): CategoryDto {
        return categoryUseCase.deleteCategory(categoryId)
    }
}