package kr.pe.karsei.boardtraffic.service.user

import kr.pe.karsei.boardtraffic.dto.UserDto
import kr.pe.karsei.boardtraffic.fixture.UserMock
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class UserMapperTest {
    @Test
    fun testMappingToEntityToDto() {
        // given
        val entity = UserMock.createUser()

        // when
        val result = UserMapper.mapToEntityToDto(entity)

        // then
        assertThat(result).isNotNull()
        assertThat(result?.id).isEqualTo(1)
        assertThat(result?.userId).isEqualTo("testId")
        assertThat(result?.password).isEqualTo("test1234")
        assertThat(result?.nickName).isEqualTo("TEST")
        assertThat(result?.isAdmin).isEqualTo(false)
        assertThat(result?.createdAt).isEqualTo(LocalDateTime.of(2023, 1, 13, 1, 2, 3))
        assertThat(result?.isWithDraw).isEqualTo(false)
        assertThat(result?.status).isEqualTo(UserDto.Status.DEFAULT)
        assertThat(result?.updatedAt).isEqualTo(LocalDateTime.of(2023, 1, 13, 1, 2, 3))
    }

    @Test
    fun testMappingToEntityToDtoWithNull() {
        // given
        val entity = null

        // when
        val result = UserMapper.mapToEntityToDto(entity)

        // then
        assertThat(result).isNull()
    }
}