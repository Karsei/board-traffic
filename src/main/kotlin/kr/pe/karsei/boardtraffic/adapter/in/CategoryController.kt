package kr.pe.karsei.boardtraffic.adapter.`in`

import kr.pe.karsei.boardtraffic.aop.LoginCheck
import kr.pe.karsei.boardtraffic.dto.CategoryDto
import kr.pe.karsei.boardtraffic.port.`in`.CategoryUseCase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/categories")
class CategoryController(
        private val categoryUseCase: CategoryUseCase,
) {
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