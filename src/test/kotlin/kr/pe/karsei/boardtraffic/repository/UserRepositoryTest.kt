package kr.pe.karsei.boardtraffic.repository

import kr.pe.karsei.boardtraffic.entity.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest @Autowired constructor(
    private val userRepository: UserRepository,
) {
    @BeforeEach
    fun setup() {
        val user = User(null, "testId", "1234", "TEST")
        userRepository.save(user)
    }

    @Test
    fun testFindingByUserId() {
        // given
        val userId = "testId"

        // when
        val result = userRepository.findByUserId(userId)

        // then
        assertAll(
            { assertThat(result).isNotNull() },
            { assertThat(result?.id).isGreaterThan(0) },
            { assertThat(result?.userId).isEqualTo(userId) },
            { assertThat(result?.password).isEqualTo("1234") },
            { assertThat(result?.nickName).isEqualTo("TEST") },
            { assertThat(result?.isAdmin).isFalse() },
            { assertThat(result?.createdAt).isNotNull() },
            { assertThat(result?.isWithDraw).isEqualTo(false) },
            { assertThat(result?.status).isEqualTo(User.Status.DEFAULT) },
            { assertThat(result?.updatedAt).isNotNull() },
        )
    }

    @Test
    fun testFindingByUserIdAndPassword() {
        // given
        val userId = "testId"
        val password = "1234"

        // when
        val result = userRepository.findByIdAndPassword(userId, password)

        // then
        assertAll(
            { assertThat(result).isNotNull() },
            { assertThat(result?.id).isGreaterThan(0) },
            { assertThat(result?.userId).isEqualTo(userId) },
            { assertThat(result?.password).isEqualTo(password) },
            { assertThat(result?.nickName).isEqualTo("TEST") },
            { assertThat(result?.isAdmin).isFalse() },
            { assertThat(result?.createdAt).isNotNull() },
            { assertThat(result?.isWithDraw).isEqualTo(false) },
            { assertThat(result?.status).isEqualTo(User.Status.DEFAULT) },
            { assertThat(result?.updatedAt).isNotNull() },
        )
    }
}