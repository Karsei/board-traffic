package kr.pe.karsei.boardtraffic.application.post.service

import kr.pe.karsei.boardtraffic.application.post.dto.CategoryDto
import kr.pe.karsei.boardtraffic.application.post.port.`in`.CategoryUseCase
import kr.pe.karsei.boardtraffic.application.post.port.out.CategorySavePort
import kr.pe.karsei.boardtraffic.application.post.service.PostMapper.Companion.mapToEntityToCategoryDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CategoryService(
    private val categorySavePort: CategorySavePort,
): CategoryUseCase {
    @Transactional
    override fun insertCategory(params: CategoryDto.InsertPostCategory): CategoryDto {
        return mapToEntityToCategoryDto(categorySavePort.insertCategory(params))!!
    }

    @Transactional
    override fun updateCategory(params: CategoryDto.UpdatePostCategory): CategoryDto {
        return mapToEntityToCategoryDto(categorySavePort.updateCategory(params))!!
    }

    @Transactional
    override fun deleteCategory(categoryId: Long): CategoryDto {
        return mapToEntityToCategoryDto(categorySavePort.deleteCategory(categoryId))!!
    }
}