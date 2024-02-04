package kr.pe.karsei.boardtraffic.application.post.adapter.out

import kr.pe.karsei.boardtraffic.application.post.dto.PostDto
import kr.pe.karsei.boardtraffic.application.post.domain.Category
import kr.pe.karsei.boardtraffic.application.post.domain.File
import kr.pe.karsei.boardtraffic.application.post.domain.Post
import kr.pe.karsei.boardtraffic.application.client.domain.User
import kr.pe.karsei.boardtraffic.application.client.repository.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import java.time.LocalDateTime
import java.util.*

@ExtendWith(MockitoExtension::class)
class PostJpaAdapterMockTest {
    @Mock
    private lateinit var postRepository: PostRepository
    @Mock
    private lateinit var categoryRepository: CategoryRepository
    @Mock
    private lateinit var fileRepository: FileRepository
    @Mock
    private lateinit var commentRepository: CommentRepository
    @Mock
    private lateinit var tagRepository: TagRepository
    @InjectMocks
    private lateinit var postJpaAdapter: PostJpaAdapter

    private val post = Post.create(
        user = User(1L, "testId", "1234", "TEST"),
        category = Category(id = 1L, title = "바보"),
        title = "오늘의 저녁은 무엇일까",
        contents = "아헿"
    )

    @Test
    fun findPostsByUser() {
        // given
        val list = listOf(post)
        val pageable = PageRequest.of(0, 10)
        given(postRepository.findByUserId(anyLong(), any(Pageable::class.java))).willReturn(PageImpl(list, pageable, list.size.toLong()))

        // when
        val userId = 1L
        val results = postJpaAdapter.findPostsByUser(userId, pageable)

        // then
        assertAll(
            { assertThat(results).isNotNull() },
            { assertThat(results.content).isNotNull() },
            { assertThat(results.content).isNotEmpty() },
            { assertThat(results.content[0].id).isNotNull() },
            { assertThat(results.content[0].title).isNotBlank() },
            { assertThat(results.content[0].isAdmin).isNotNull() },
            { assertThat(results.content[0].contents).isNotBlank() },
            { assertThat(results.content[0].views).isNotNull() },
            { assertThat(results.content[0].category).isNotNull() },
            { assertThat(results.content[0].user).isNotNull() },
            { assertThat(results.content[0].file).isNotNull() },
            { assertThat(results.content[0].createdAt).isNotNull() },
            { assertThat(results.content[0].updatedAt).isNotNull() },
        )
    }

    @Test
    fun findPosts() {
        // given
        val list = listOf(
            PostDto(
                id = 1L,
                title = "오늘의 저녁은?",
                isAdmin = false,
                contents = "추천추천",
                views = 5,
                categoryId = 1L,
                userId = 2L,
                fileId = 4L,
                createdAt = LocalDateTime.of(2024, 1, 11, 1, 2, 3),
                updatedAt = LocalDateTime.of(2034, 1, 11, 1, 2, 3)
            )
        )
        val pageable = PageRequest.of(0, 10)
        given(postRepository.findPosts(any(), any(PostDto.PostSearchRequest::class.java), any(Pageable::class.java)))
            .willReturn(PageImpl(list, pageable, list.size.toLong()))

        // when
        val request = PostDto.PostSearchRequest()
        val results = postJpaAdapter.findPosts(null, request, pageable)

        // then
        assertAll(
            { assertThat(results).isNotNull() },
            { assertThat(results.content).isNotNull() },
            { assertThat(results.content).isNotEmpty() },
            { assertThat(results.content[0].id).isNotNull() },
            { assertThat(results.content[0].title).isNotBlank() },
            { assertThat(results.content[0].isAdmin).isNotNull() },
            { assertThat(results.content[0].contents).isNotBlank() },
            { assertThat(results.content[0].views).isNotNull() },
            { assertThat(results.content[0].categoryId).isNotNull() },
            { assertThat(results.content[0].userId).isNotNull() },
            { assertThat(results.content[0].fileId).isNotNull() },
            { assertThat(results.content[0].createdAt).isNotNull() },
            { assertThat(results.content[0].updatedAt).isNotNull() },
        )
    }

    @Test
    fun updatePost() {
        // given
        val request = PostDto.UpdatePostRequest(
            postId = 1L,
            userId = 1L,
            id = 1L,
            title = "점심은?",
            contents = "점심먹자~",
            views = 10,
            categoryId = 10L,
            fileId = 5L
        )
        given(postRepository.findById(anyLong())).willReturn(Optional.of(post))
        given(categoryRepository.findById(anyLong())).willReturn(Optional.of(Category(10L, "양식")))
        given(fileRepository.findById(anyLong())).willReturn(Optional.of(File(5L, "/some/path", "image", "png")))

        // when & then
        assertDoesNotThrow { postJpaAdapter.updatePost(request) }
    }

    @Test
    fun deletePost() {
        // given
        val postId = 1L

        // when & then
        assertDoesNotThrow { postJpaAdapter.deletePost(postId) }
    }
}