package kr.pe.karsei.boardtraffic.service.post

import kr.pe.karsei.boardtraffic.fixture.PostMock
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PostMapperTest {
    @Test
    fun mapToEntityToPostDto() {
        // given
        val entity = PostMock.createPostTotal()

        // when
        val result = PostMapper.mapToEntityToPostDto(entity)

        // then
        assertAll(
            { assertThat(result).isNotNull() },
        )
    }

    @Test
    fun mapToEntityToPostDtoWithNull() {
        // given
        val entity = null

        // when
        val result = PostMapper.mapToEntityToPostDto(entity)

        // then
        assertAll(
            { assertThat(result).isNull() },
        )
    }

    @Test
    fun mapToEntityToPostCommentDto() {
        // given
        val entity = PostMock.createPostCommentWithSub()

        // when
        val result = PostMapper.mapToEntityToPostCommentDto(entity)

        // then
        assertAll(
            { assertThat(result).isNotNull() },
        )
    }

    @Test
    fun mapToEntityToPostCommentDtoWithNull() {
        // given
        val entity = null

        // when
        val result = PostMapper.mapToEntityToPostCommentDto(entity)

        // then
        assertAll(
            { assertThat(result).isNull() },
        )
    }

    @Test
    fun mapToEntityToCategoryDto() {
        // given
        val entity = PostMock.createCategory()

        // when
        val result = PostMapper.mapToEntityToCategoryDto(entity)

        // then
        assertAll(
            { assertThat(result).isNotNull() },
        )
    }

    @Test
    fun mapToEntityToCategoryDtoWithNull() {
        // given
        val entity = null

        // when
        val result = PostMapper.mapToEntityToCategoryDto(entity)

        // then
        assertAll(
            { assertThat(result).isNull() },
        )
    }
}