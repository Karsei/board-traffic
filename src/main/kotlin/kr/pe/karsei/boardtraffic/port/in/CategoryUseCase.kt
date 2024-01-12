package kr.pe.karsei.boardtraffic.port.`in`

import kr.pe.karsei.boardtraffic.dto.CategoryDto

interface CategoryUseCase {
    fun insertCategory(params: CategoryDto.InsertPostCategory)

    fun updateCategory(categoryId: Long, params: CategoryDto.UpdatePostCategory)

    fun deleteCategory(categoryId: Long)
}