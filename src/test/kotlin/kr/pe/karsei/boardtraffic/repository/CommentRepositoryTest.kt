package kr.pe.karsei.boardtraffic.repository

import kr.pe.karsei.boardtraffic.config.db.QueryDslConfiguration
import kr.pe.karsei.boardtraffic.fixture.PostMock
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(QueryDslConfiguration::class)
class CommentRepositoryTest @Autowired constructor(
    private val commentRepository: CommentRepository,
) {
    @Test
    fun crud() {
        val comment = commentRepository.save(PostMock.createPostComment())
        Assertions.assertThat(comment).isNotNull()
        Assertions.assertThat(comment.id).isEqualTo(1)
        Assertions.assertThat(comment.contents).isNotBlank()

        commentRepository.delete(comment)
        val removed = commentRepository.findById(comment.id!!)
        Assertions.assertThat(removed).isNotNull()
        Assertions.assertThat(removed).isEmpty()
    }
}