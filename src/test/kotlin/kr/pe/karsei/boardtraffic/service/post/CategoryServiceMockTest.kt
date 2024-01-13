package kr.pe.karsei.boardtraffic.service.post

import kr.pe.karsei.boardtraffic.port.out.CategorySavePort
import org.junit.jupiter.api.Test

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class CategoryServiceMockTest {
    @Mock
    private lateinit var categorySavePort: CategorySavePort
    @InjectMocks
    private lateinit var categoryService: CategoryService

    @Test
    fun insertCategory() {
        // given

        // when

        // then
    }

    @Test
    fun updateCategory() {
        // given

        // when

        // then
    }

    @Test
    fun deleteCategory() {
        // given

        // when

        // then
    }
}