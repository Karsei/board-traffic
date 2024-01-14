package kr.pe.karsei.boardtraffic.service.post

import kr.pe.karsei.boardtraffic.dto.PostDto
import kr.pe.karsei.boardtraffic.entity.User
import kr.pe.karsei.boardtraffic.fixture.PostMock
import kr.pe.karsei.boardtraffic.fixture.UserMock
import kr.pe.karsei.boardtraffic.port.out.PostLoadPort
import kr.pe.karsei.boardtraffic.port.out.PostSavePort
import kr.pe.karsei.boardtraffic.port.out.UserLoadPort
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.argumentCaptor
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

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
        val pageable = PageRequest.of(0, 10)

        val argsId = argumentCaptor<Long>()
        val argsParam = argumentCaptor<PostDto.PostSearchRequest>()
        val argsPageable = argumentCaptor<Pageable>()
        val list = listOf(PostMapper.mapToEntityToPostDto(PostMock.createPost()))
        val pageImpl: Page<PostDto> = PageImpl(list, pageable, list.size.toLong())
        given(postLoadPort.findPosts(argsId.capture(), argsParam.capture(), argsPageable.capture())).willReturn(pageImpl)

        // when
        val request = PostDto.PostSearchRequest()
        val result = postService.findPosts(request, pageable)

        // then
        assertAll(
            { assertThat(result).isNotNull() },
            { assertThat(result.content).isNotEmpty() },
            { assertThat(result.content[0].id).isEqualTo(list[0]?.id) },
            { assertThat(result.content[0].userId).isEqualTo(list[0]?.userId) },
            { assertThat(result.content[0].contents).isEqualTo(list[0]?.contents) },
            { assertThat(result.content[0].categoryId).isEqualTo(list[0]?.categoryId) },
            { assertThat(result.content[0].fileId).isEqualTo(list[0]?.fileId) },
            { assertThat(result.content[0].createdAt).isEqualTo(list[0]?.createdAt) },
            { assertThat(result.content[0].isAdmin).isEqualTo(list[0]?.isAdmin) },
            { assertThat(result.content[0].title).isEqualTo(list[0]?.title) },
            { assertThat(result.content[0].updatedAt).isEqualTo(list[0]?.updatedAt) },
            { assertThat(result.content[0].views).isEqualTo(list[0]?.views) },
        )
    }

    @Test
    fun findMyPosts() {
        // given
        val pageable = PageRequest.of(0, 10)

        val argsId = argumentCaptor<Long>()
        val argsParam = argumentCaptor<PostDto.PostSearchRequest>()
        val argsPageable = argumentCaptor<Pageable>()
        val list = listOf(PostMapper.mapToEntityToPostDto(PostMock.createPost()))
        val pageImpl: Page<PostDto> = PageImpl(list, pageable, list.size.toLong())
        given(postLoadPort.findPosts(argsId.capture(), argsParam.capture(), argsPageable.capture())).willReturn(pageImpl)

        // when
        val userId = 1L
        val request = PostDto.PostSearchRequest()
        val result = postService.findMyPosts(userId, request, pageable)

        // then
        assertAll(
            { assertThat(result).isNotNull() },
            { assertThat(result.content).isNotEmpty() },
            { assertThat(result.content[0].id).isEqualTo(list[0]?.id) },
            { assertThat(result.content[0].userId).isEqualTo(list[0]?.userId) },
            { assertThat(result.content[0].contents).isEqualTo(list[0]?.contents) },
            { assertThat(result.content[0].categoryId).isEqualTo(list[0]?.categoryId) },
            { assertThat(result.content[0].fileId).isEqualTo(list[0]?.fileId) },
            { assertThat(result.content[0].createdAt).isEqualTo(list[0]?.createdAt) },
            { assertThat(result.content[0].isAdmin).isEqualTo(list[0]?.isAdmin) },
            { assertThat(result.content[0].title).isEqualTo(list[0]?.title) },
            { assertThat(result.content[0].updatedAt).isEqualTo(list[0]?.updatedAt) },
            { assertThat(result.content[0].views).isEqualTo(list[0]?.views) },
        )
    }

    @Test
    fun insertPost() {
        // given
        given(userLoadPort.getUserInfo(ArgumentMatchers.anyLong())).willReturn(UserMock.createUser())

        val argsId = argumentCaptor<User>()
        val argsParam = argumentCaptor<PostDto.InsertPostRequest>()
        val post = PostMock.createPost()
        given(postSavePort.insertPost(argsId.capture(), argsParam.capture())).willReturn(post)

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
            { assertThat(result).isNotNull() },
            { assertThat(result.id).isEqualTo(post.id) },
            { assertThat(result.userId).isEqualTo(post.user.id) },
            { assertThat(result.contents).isEqualTo(post.contents) },
            { assertThat(result.categoryId).isEqualTo(post.category.id) },
            { assertThat(result.fileId).isEqualTo(post.file?.id) },
            { assertThat(result.createdAt).isEqualTo(post.createdAt) },
            { assertThat(result.isAdmin).isEqualTo(post.isAdmin) },
            { assertThat(result.title).isEqualTo(post.title) },
            { assertThat(result.updatedAt).isEqualTo(post.updatedAt) },
            { assertThat(result.views).isEqualTo(post.views) },
        )
    }

    @Test
    fun insertPostWithUserNotFound() {
        // given
        given(userLoadPort.getUserInfo(ArgumentMatchers.anyLong())).willReturn(null)

        // when & then
        val request = PostDto.InsertPostRequest(
            userId = 1L,
            title = "타이틀~",
            contents = "내애애용",
            categoryId = 10L,
            fileId = 11L
        )
        val result = assertThrows(ResponseStatusException::class.java) { postService.insertPost(request) }
        assertAll(
            { assertThat(result).isNotNull() },
            { assertThat(result.reason).isEqualTo("유저가 존재하지 않습니다.") },
            { assertThat(result.statusCode).isEqualTo(HttpStatus.NOT_FOUND) },
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
    fun updatePostWithUserNotFound() {
        // given
        given(userLoadPort.getUserInfo(ArgumentMatchers.anyLong())).willReturn(null)

        // when & then
        val request = PostDto.UpdatePostRequest(
            id = 1L,
            postId = 1L,
            userId = 1L,
            title = "타이틀~",
            contents = "내애애용",
            views = 1,
            categoryId = 10L,
            fileId = 11L
        )
        val result = assertThrows(ResponseStatusException::class.java) { postService.updatePost(request) }
        assertAll(
            { assertThat(result).isNotNull() },
            { assertThat(result.reason).isEqualTo("유저가 존재하지 않습니다.") },
            { assertThat(result.statusCode).isEqualTo(HttpStatus.NOT_FOUND) },
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

    @Test
    fun deletePostWithUserNotFound() {
        // given
        given(userLoadPort.getUserInfo(ArgumentMatchers.anyLong())).willReturn(null)

        // when & then
        val result = assertThrows(ResponseStatusException::class.java) { postService.deletePost(1L, 1L) }
        assertAll(
            { assertThat(result).isNotNull() },
            { assertThat(result.reason).isEqualTo("유저가 존재하지 않습니다.") },
            { assertThat(result.statusCode).isEqualTo(HttpStatus.NOT_FOUND) },
        )
    }
}