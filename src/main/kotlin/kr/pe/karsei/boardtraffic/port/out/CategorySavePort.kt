package kr.pe.karsei.boardtraffic.port.out

import kr.pe.karsei.boardtraffic.dto.CategoryDto
import kr.pe.karsei.boardtraffic.entity.Category

interface CategorySavePort {
    fun insertCategory(params: CategoryDto.InsertPostCategory): Category

    fun updateCategory(params: CategoryDto.UpdatePostCategory): Category

    fun deleteCategory(categoryId: Long): Category
}