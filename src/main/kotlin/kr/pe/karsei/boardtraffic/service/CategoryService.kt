package kr.pe.karsei.boardtraffic.service

import kr.pe.karsei.boardtraffic.dto.CategoryDto
import kr.pe.karsei.boardtraffic.port.`in`.CategoryUseCase
import kr.pe.karsei.boardtraffic.port.out.CategorySavePort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CategoryService(
    private val categorySavePort: CategorySavePort,
): CategoryUseCase {
    @Transactional
    override fun insertCategory(params: CategoryDto.InsertPostCategory) {
        return categorySavePort.insertCategory(params)
    }

    @Transactional
    override fun updateCategory(categoryId: Long, params: CategoryDto.UpdatePostCategory) {
        return categorySavePort.updateCategory(categoryId, params)
    }

    @Transactional
    override fun deleteCategory(categoryId: Long) {
        return categorySavePort.deleteCategory(categoryId)
    }
}