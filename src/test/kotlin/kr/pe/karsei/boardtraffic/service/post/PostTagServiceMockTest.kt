package kr.pe.karsei.boardtraffic.service.post

import kr.pe.karsei.boardtraffic.port.out.PostTagSavePort
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class PostTagServiceMockTest {
    @Mock
    private lateinit var postTagSavePort: PostTagSavePort
    @InjectMocks
    private lateinit var postTagService: PostTagService

    @Test
    fun insertTag() {
        // given

        // when

        // then
    }

    @Test
    fun updateTag() {
        // given

        // when

        // then
    }

    @Test
    fun deleteTag() {
        // given

        // when

        // then
    }
}