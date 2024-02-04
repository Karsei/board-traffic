package kr.pe.karsei.boardtraffic.application.post.port.out

import kr.pe.karsei.boardtraffic.application.post.dto.CategoryDto
import kr.pe.karsei.boardtraffic.application.post.domain.Category

interface CategorySavePort {
    fun insertCategory(params: CategoryDto.InsertPostCategory): Category

    fun updateCategory(params: CategoryDto.UpdatePostCategory): Category

    fun deleteCategory(categoryId: Long): Category
}