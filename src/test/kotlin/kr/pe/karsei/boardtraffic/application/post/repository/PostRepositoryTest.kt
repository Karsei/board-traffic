package kr.pe.karsei.boardtraffic.application.post.repository

import kr.pe.karsei.boardtraffic.application.client.repository.CategoryRepository
import kr.pe.karsei.boardtraffic.application.client.repository.PostRepository
import kr.pe.karsei.boardtraffic.core.config.db.QueryDslConfiguration
import kr.pe.karsei.boardtraffic.application.post.dto.PostDto
import kr.pe.karsei.boardtraffic.application.post.repository.UserRepository
import kr.pe.karsei.boardtraffic.application.fixture.PostMock
import kr.pe.karsei.boardtraffic.application.fixture.UserMock
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.data.domain.PageRequest

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(QueryDslConfiguration::class)
class PostRepositoryTest @Autowired constructor(
    private val postRepository: PostRepository,
    private val categoryRepository: CategoryRepository,
    private val userRepository: UserRepository,
) {
    @BeforeEach
    fun setup() {
        val user = UserMock.createUser()
        userRepository.save(user)
        val category = PostMock.createCategory()
        categoryRepository.save(category)
        val post = PostMock.createPost()
        postRepository.save(post)
    }

    @Test
    fun testFindingById() {
        // given
        val userId = 1L
        val pageable = PageRequest.of(0, 10)

        // when
        val result = postRepository.findByUserId(userId, pageable)

        // then
        assertAll(
            { Assertions.assertThat(result).isNotNull() },
            { Assertions.assertThat(result).isNotEmpty() },
            { Assertions.assertThat(result.content).isNotEmpty() },
            { Assertions.assertThat(result.content[0].id).isGreaterThan(0) },
            { Assertions.assertThat(result.content[0].title).isNotBlank() },
            { Assertions.assertThat(result.content[0].isAdmin).isFalse() },
            { Assertions.assertThat(result.content[0].contents).isNotEmpty() },
            { Assertions.assertThat(result.content[0].views).isEqualTo(1) },
            { Assertions.assertThat(result.content[0].category.id).isEqualTo(1) },
            { Assertions.assertThat(result.content[0].user.id).isEqualTo(userId) },
            { Assertions.assertThat(result.content[0].createdAt).isNotNull() },
            { Assertions.assertThat(result.content[0].updatedAt).isNotNull() },
        )
    }

    @Test
    fun testFindingPosts() {
        // given
        val userId = 1L
        val request = PostDto.PostSearchRequest()
        val pageable = PageRequest.of(0, 10)

        // when
        val result = postRepository.findPosts(userId, request, pageable)

        // then
        assertAll(
            { Assertions.assertThat(result).isNotNull() },
            { Assertions.assertThat(result).isNotEmpty() },
            { Assertions.assertThat(result.content).isNotEmpty() },
            { Assertions.assertThat(result.content[0].id).isGreaterThan(0) },
            { Assertions.assertThat(result.content[0].title).isNotBlank() },
            { Assertions.assertThat(result.content[0].isAdmin).isFalse() },
            { Assertions.assertThat(result.content[0].contents).isNotEmpty() },
            { Assertions.assertThat(result.content[0].views).isEqualTo(1) },
            { Assertions.assertThat(result.content[0].categoryId).isEqualTo(1) },
            { Assertions.assertThat(result.content[0].userId).isEqualTo(userId) },
            { Assertions.assertThat(result.content[0].createdAt).isNotNull() },
            { Assertions.assertThat(result.content[0].updatedAt).isNotNull() },
        )
    }
}