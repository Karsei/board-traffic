package kr.pe.karsei.boardtraffic.service.post

import kr.pe.karsei.boardtraffic.dto.PostDto
import kr.pe.karsei.boardtraffic.port.out.PostLoadPort
import kr.pe.karsei.boardtraffic.port.out.PostSavePort
import kr.pe.karsei.boardtraffic.port.out.UserLoadPort
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.data.domain.PageRequest

@ExtendWith(MockitoExtension::class)
class PostServiceMockTest {
    @Mock
    private lateinit var postLoadPort: PostLoadPort
    @Mock
    private lateinit var postSavePort: PostSavePort
    @Mock
    private lateinit var userLoadPort: UserLoadPort
    @InjectMocks
    private lateinit var postService: PostService

    @Test
    fun findPosts() {
        // given

        // when
        val request = PostDto.PostSearchRequest()
        val pageable = PageRequest.of(0, 10)
        val result = postService.findPosts(request, pageable)

        // then
        assertAll(
            { assertThat(result).isNotNull() }
        )
    }

    @Test
    fun findMyPosts() {
        // given

        // when
        val userId = 1L
        val request = PostDto.PostSearchRequest()
        val pageable = PageRequest.of(0, 10)
        val result = postService.findMyPosts(userId, request, pageable)

        // then
        assertAll(
            { assertThat(result).isNotNull() }
        )
    }

    @Test
    fun insertPost() {
        // given

        // when
        val request = PostDto.InsertPostRequest(
            userId = 1L,
            title = "타이틀~",
            contents = "내애애용",
            categoryId = 10L,
            fileId = 11L
        )
        val result = postService.insertPost(request)

        // then
        assertAll(
            { assertThat(result).isNotNull() }
        )
    }

    @Test
    fun updatePost() {
        // given

        // when
        val request = PostDto.UpdatePostRequest(
            userId = 1L,
            postId = 1L,
            id = 1L,
            title = "타이틀~",
            contents = "내애애용",
            views = 1,
            categoryId = 10L,
            fileId = 11L
        )
        val result = postService.updatePost(request)

        // then
        assertAll(
            { assertThat(result).isNotNull() }
        )
    }

    @Test
    fun deletePost() {
        // given

        // when
        val userId = 1L
        val postId = 1L
        val result = postService.deletePost(userId, postId)

        // then
        assertAll(
            { assertThat(result).isNotNull() }
        )
    }
}