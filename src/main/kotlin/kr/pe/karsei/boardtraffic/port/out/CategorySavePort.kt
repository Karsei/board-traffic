package kr.pe.karsei.boardtraffic.port.out

import kr.pe.karsei.boardtraffic.dto.CategoryDto

interface CategorySavePort {
    fun insertCategory(params: CategoryDto.InsertPostCategory)

    fun updateCategory(categoryId: Long, params: CategoryDto.UpdatePostCategory)

    fun deleteCategory(categoryId: Long)
}