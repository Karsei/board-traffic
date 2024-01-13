package kr.pe.karsei.boardtraffic.service.post

import kr.pe.karsei.boardtraffic.port.out.PostCommentSavePort
import kr.pe.karsei.boardtraffic.port.out.UserLoadPort
import org.junit.jupiter.api.Test

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class PostCommentServiceMockTest {
    @Mock
    private lateinit var postCommentSavePort: PostCommentSavePort
    @Mock
    private lateinit var userLoadPort: UserLoadPort
    @InjectMocks
    private lateinit var postCommentService: PostCommentService

    @Test
    fun insertComment() {
        // given

        // when

        // then
    }

    @Test
    fun updateComment() {
        // given

        // when

        // then
    }

    @Test
    fun deleteComment() {
        // given

        // when

        // then
    }
}