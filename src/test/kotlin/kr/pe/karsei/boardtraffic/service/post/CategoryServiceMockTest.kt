package kr.pe.karsei.boardtraffic.service.post

import kr.pe.karsei.boardtraffic.dto.CategoryDto
import kr.pe.karsei.boardtraffic.entity.Category
import kr.pe.karsei.boardtraffic.port.out.CategorySavePort
import org.junit.jupiter.api.Test

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.argumentCaptor

@ExtendWith(MockitoExtension::class)
class CategoryServiceMockTest {
    @Mock
    private lateinit var categorySavePort: CategorySavePort
    @InjectMocks
    private lateinit var categoryService: CategoryService

    @Test
    fun insertCategory() {
        // given
        val argCapture = argumentCaptor<CategoryDto.InsertPostCategory>()
        given(categorySavePort.insertCategory(argCapture.capture())).willReturn(Category(1L, "카테고리"))

        // when
        val category = CategoryDto.InsertPostCategory(1L, "카테고리")
        val result = categoryService.insertCategory(category)

        // then
        assertAll(
            { assertThat(result).isNotNull() },
            { assertThat(result.id).isEqualTo(1L) },
            { assertThat(result.title).isEqualTo("카테고리") },
        )
    }

    @Test
    fun updateCategory() {
        // given
        val argCapture = argumentCaptor<CategoryDto.UpdatePostCategory>()
        given(categorySavePort.updateCategory(argCapture.capture())).willReturn(Category(1L, "카테고리"))

        // when
        val category = CategoryDto.UpdatePostCategory(1L, 1L, "카테고리")
        val result = categoryService.updateCategory(category)

        // then
        assertAll(
            { assertThat(result).isNotNull() },
            { assertThat(result.id).isEqualTo(1L) },
            { assertThat(result.title).isEqualTo("카테고리") },
        )
    }

    @Test
    fun deleteCategory() {
        // given
        val argCapture = argumentCaptor<Long>()
        given(categorySavePort.deleteCategory(argCapture.capture())).willReturn(Category(1L, "카테고리"))

        // when
        val result = categoryService.deleteCategory(1L)

        // then
        assertAll(
            { assertThat(result).isNotNull() },
            { assertThat(result.id).isEqualTo(1L) },
            { assertThat(result.title).isEqualTo("카테고리") },
        )
    }
}