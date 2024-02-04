package kr.pe.karsei.boardtraffic.application.post.service

import kr.pe.karsei.boardtraffic.application.post.service.PostMapper
import kr.pe.karsei.boardtraffic.application.fixture.PostMock
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
            { assertThat(result?.id).isEqualTo(entity.id) },
            { assertThat(result?.title).isEqualTo(entity.title) },
            { assertThat(result?.isAdmin).isEqualTo(entity.isAdmin) },
            { assertThat(result?.contents).isEqualTo(entity.contents) },
            { assertThat(result?.views).isEqualTo(entity.views) },
            { assertThat(result?.categoryId).isEqualTo(entity.category.id) },
            { assertThat(result?.userId).isEqualTo(entity.user.id) },
            { assertThat(result?.fileId).isEqualTo(entity.file?.id) },
            { assertThat(result?.createdAt).isEqualTo(entity.createdAt) },
            { assertThat(result?.updatedAt).isEqualTo(entity.updatedAt) },
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
            { assertThat(result?.id).isEqualTo(entity.id) },
            { assertThat(result?.contents).isEqualTo(entity.contents) },
            { assertThat(result?.subCommentId).isEqualTo(entity.subComment?.id) },
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
            { assertThat(result?.id).isEqualTo(entity.id) },
            { assertThat(result?.title).isEqualTo(entity.title) },
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