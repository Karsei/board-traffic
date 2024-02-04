package kr.pe.karsei.boardtraffic.application.post.port.`in`

import kr.pe.karsei.boardtraffic.application.post.dto.CategoryDto

interface CategoryUseCase {
    fun insertCategory(params: CategoryDto.InsertPostCategory): CategoryDto

    fun updateCategory(params: CategoryDto.UpdatePostCategory): CategoryDto

    fun deleteCategory(categoryId: Long): CategoryDto
}