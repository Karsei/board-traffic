package kr.pe.karsei.boardtraffic.port.`in`

import kr.pe.karsei.boardtraffic.dto.CategoryDto

interface CategoryUseCase {
    fun insertCategory(params: CategoryDto.InsertPostCategory): CategoryDto

    fun updateCategory(params: CategoryDto.UpdatePostCategory): CategoryDto

    fun deleteCategory(categoryId: Long): CategoryDto
}