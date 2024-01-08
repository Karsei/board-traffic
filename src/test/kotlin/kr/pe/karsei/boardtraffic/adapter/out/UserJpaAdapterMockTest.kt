package kr.pe.karsei.boardtraffic.adapter.out

import kr.pe.karsei.boardtraffic.dto.UserDto
import kr.pe.karsei.boardtraffic.entity.User
import kr.pe.karsei.boardtraffic.repository.UserRepository
import kr.pe.karsei.boardtraffic.util.SHA256Util
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyString
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
class UserJpaAdapterMockTest {
    @Mock
    private lateinit var userRepository: UserRepository
    @InjectMocks
    private lateinit var userJpaAdapter: UserJpaAdapter

    private val mockUserForRead = User(
        id = 1L,
        userId = "testId",
        password = "03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4",
        nickName = "TEST",
        createdAt = LocalDateTime.of(2024, 1, 2, 3, 4, 5),
        updatedAt = LocalDateTime.of(2024, 1, 2, 3, 4, 5),
    )

    @Test
    fun testGetUserInfo() {
        // given
        given(userRepository.findByUserId(anyString())).willReturn(mockUserForRead)

        // when
        val userId = "testId"
        val password = SHA256Util.encryptSHA256("1234")
        val result = userJpaAdapter.getUserInfo(userId)

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
            { assertThat(result?.status?.name).isEqualTo(User.Status.DEFAULT.name) },
            { assertThat(result?.updatedAt).isNotNull() },
        )
    }

    @Test
    fun testGetUserInfoWithPassword() {
        // given
        given(userRepository.findByUserIdAndPassword(anyString(), anyString())).willReturn(mockUserForRead)

        // when
        val userId = "testId"
        val password = SHA256Util.encryptSHA256("1234")
        val result = userJpaAdapter.getUserInfo(userId, password)

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
            { assertThat(result?.status?.name).isEqualTo(User.Status.DEFAULT.name) },
            { assertThat(result?.updatedAt).isNotNull() },
        )
    }

    @Test
    fun registerIfUserIdIsFound() {
        // given
        given(userRepository.findByUserId(anyString())).willReturn(mockUserForRead)

        // when & then
        val userId = "testId"
        val password = "1234"
        val nickname = "TEST"
        val exception = assertThrows(ResponseStatusException::class.java) {
            userJpaAdapter.register(
                UserDto.Register.UserRegisterRequest(
                    userId,
                    password,
                    nickname
                )
            )
        }
        assertThat(exception).isNotNull()
        assertThat(exception.statusCode).isEqualTo(HttpStatus.CONFLICT)
        assertThat(exception.reason).isEqualTo("중복된 아이디입니다.")
    }

    @Test
    fun registerSuccessful() {
        // given
        given(userRepository.findByUserId(anyString())).willReturn(null)
        given(userRepository.save(any(User::class.java))).willReturn(mockUserForRead)

        // when
        val userId = "testId"
        val password = "1234"
        val nickname = "TEST"
        val result = userJpaAdapter.register(
            UserDto.Register.UserRegisterRequest(
                userId,
                password,
                nickname
            )
        )

        // then
        assertAll(
            { assertThat(result).isNotNull() },
            { assertThat(result.id).isGreaterThan(0) },
            { assertThat(result.userId).isEqualTo(userId) },
            { assertThat(result.password).isEqualTo(SHA256Util.encryptSHA256(password)) },
            { assertThat(result.nickName).isEqualTo("TEST") },
            { assertThat(result.isAdmin).isFalse() },
            { assertThat(result.createdAt).isNotNull() },
            { assertThat(result.isWithDraw).isEqualTo(false) },
            { assertThat(result.status?.name).isEqualTo(User.Status.DEFAULT.name) },
            { assertThat(result.updatedAt).isNotNull() },
        )
    }

    @Test
    fun updatePasswordIfUserIdIsNotFound() {
        // given
        given(userRepository.findByUserIdAndPassword(anyString(), anyString())).willReturn(null)

        // when & then
        val userId = "testId"
        val beforePassword = "1234"
        val afterPassword = "1111"
        val exception = assertThrows(ResponseStatusException::class.java) {
            userJpaAdapter.updatePassword(
                userId,
                beforePassword,
                afterPassword
            )
        }
        assertThat(exception).isNotNull()
        assertThat(exception.statusCode).isEqualTo(HttpStatus.NOT_FOUND)
        assertThat(exception.reason).isEqualTo("올바르지 않는 아이디이거나 비밀번호입니다.")
    }

    @Test
    fun updatePasswordSuccessful() {
        // given
        given(userRepository.findByUserIdAndPassword(anyString(), anyString())).willReturn(mockUserForRead)

        // when & then
        val userId = "testId"
        val beforePassword = "1234"
        val afterPassword = "1111"
        assertDoesNotThrow { userJpaAdapter.updatePassword(userId, beforePassword, afterPassword) }
    }

    @Test
    fun deleteUserIfUserIdIsNotFound() {
        // given
        given(userRepository.findByUserIdAndPassword(anyString(), anyString())).willReturn(null)

        // when & then
        val userId = "testId"
        val password = "1234"
        val exception = assertThrows(ResponseStatusException::class.java) {
            userJpaAdapter.deleteUser(
                userId,
                password,
            )
        }
        assertThat(exception).isNotNull()
        assertThat(exception.statusCode).isEqualTo(HttpStatus.NOT_FOUND)
        assertThat(exception.reason).isEqualTo("올바르지 않는 아이디이거나 비밀번호입니다.")
    }

    @Test
    fun deleteUserSuccessful() {
        // given
        given(userRepository.findByUserIdAndPassword(anyString(), anyString())).willReturn(mockUserForRead)

        // when & then
        val userId = "testId"
        val password = "1234"
        assertDoesNotThrow { userJpaAdapter.deleteUser(userId, password) }
    }
}