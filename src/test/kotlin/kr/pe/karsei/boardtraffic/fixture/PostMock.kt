package kr.pe.karsei.boardtraffic.fixture

import kr.pe.karsei.boardtraffic.entity.Category
import kr.pe.karsei.boardtraffic.entity.Comment
import kr.pe.karsei.boardtraffic.entity.File
import kr.pe.karsei.boardtraffic.entity.Post
import java.time.LocalDateTime

class PostMock {
    companion object {
        fun createPost(): Post {
            return Post(
                id = 1L,
                title = "타이틀",
                isAdmin = false,
                contents = "글 내용",
                views = 1,
                category = createCategory(),
                user = UserMock.createUser(),
                file = null,
                createdAt = LocalDateTime.of(2024, 1, 13, 1, 2, 3),
                updatedAt = LocalDateTime.of(2024, 1, 13, 1, 2, 3)
            )
        }

        fun createPostTotal(): Post {
            return Post(
                id = 1L,
                title = "타이틀",
                isAdmin = false,
                contents = "글 내용",
                views = 1,
                category = createCategory(),
                user = UserMock.createUser(),
                file = createFile(),
                createdAt = LocalDateTime.of(2024, 1, 13, 1, 2, 3),
                updatedAt = LocalDateTime.of(2024, 1, 13, 1, 2, 3)
            )
        }

        fun createPostComment(): Comment {
            return Comment(
                id = 1L,
                contents = "내애애용"
            )
        }

        fun createPostCommentWithSub(): Comment {
            return Comment(
                id = 1L,
                contents = "내애애용",
                subComment = Comment(
                    id = 2L,
                    contents = "대댓글"
                ),
            )
        }

        fun createCategory(): Category {
            return Category(
                id = 1L,
                title = "카테고리"
            )
        }

        fun createFile(): File {
            return File(
                id = 1L,
                path = "/some/path",
                name = "some_image",
                extension = "png"
            )
        }
    }
}