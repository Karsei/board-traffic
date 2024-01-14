package kr.pe.karsei.boardtraffic.repository

import kr.pe.karsei.boardtraffic.config.db.QueryDslConfiguration
import kr.pe.karsei.boardtraffic.fixture.PostMock
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(QueryDslConfiguration::class)
class CategoryRepositoryTest @Autowired constructor(
    private val categoryRepository: CategoryRepository
) {
    @Test
    fun crud() {
        val category = categoryRepository.save(PostMock.createCategory())
        assertThat(category).isNotNull()
        assertThat(category.id).isEqualTo(1)
        assertThat(category.title).isNotBlank()

        categoryRepository.delete(category)
        val removed = categoryRepository.findById(category.id!!)
        assertThat(removed).isNotNull()
        assertThat(removed).isEmpty()
    }
}