package kr.pe.karsei.boardtraffic.service.user

import kr.pe.karsei.boardtraffic.dto.UserDto
import kr.pe.karsei.boardtraffic.fixture.UserMock
import kr.pe.karsei.boardtraffic.port.out.UserLoadPort
import kr.pe.karsei.boardtraffic.port.out.UserSavePort
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class UserServiceMockTest {
    @Mock
    private lateinit var userLoadPort: UserLoadPort
    @Mock
    private lateinit var userSavePort: UserSavePort
    @InjectMocks
    private lateinit var userService: UserService

    @Test
    fun getUserInfo() {
        // given
        val user = UserMock.createUser()
        given(userLoadPort.getUserInfo(user.id!!)).willReturn(user)

        // when
        val result = userService.getUserInfo(user.id!!)

        // then
        assertAll(
            { assertThat(result).isNotNull() },
            { assertThat(result.id).isEqualTo(user.id) },
            { assertThat(result.userId).isEqualTo(user.userId) }
        )
    }

    @Test
    fun login() {
        // given
        val user = UserMock.createUser()
        given(userLoadPort.getUserInfo(anyString(), anyString())).willReturn(user)

        // when
        val accountId = "testId"
        val password = "test1234"
        val result = userService.login(accountId, password)

        // then
        assertAll(
            { assertThat(result).isNotNull() },
            { assertThat(result.id).isEqualTo(user.id) },
            { assertThat(result.userId).isEqualTo(accountId) }
        )
    }

    @Test
    fun register() {
        // given

        // when
        val request = UserDto.Register.UserRegisterRequest(
            userId = "testId",
            password = "test1234",
            nickName = "TEST"
        )
        val result = userService.register(request)

        // then
    }

    @Test
    fun updatePassword() {
        // given

        // when
        val userId = 1L
        val beforePassword = "test1234"
        val afterPassword = "test4321"
        val result = userService.updatePassword(userId, beforePassword, afterPassword)

        // then
    }

    @Test
    fun deleteUser() {
        // given

        // when
        val userId = 1L
        val password = "test1234"
        val result = userService.deleteUser(userId, password)

        // then
    }
}