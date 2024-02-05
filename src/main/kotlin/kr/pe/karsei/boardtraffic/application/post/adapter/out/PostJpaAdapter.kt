package kr.pe.karsei.boardtraffic.application.post.adapter.out

import kr.pe.karsei.boardtraffic.application.client.domain.User
import kr.pe.karsei.boardtraffic.application.client.repository.*
import kr.pe.karsei.boardtraffic.application.post.domain.Category
import kr.pe.karsei.boardtraffic.application.post.domain.Comment
import kr.pe.karsei.boardtraffic.application.post.domain.Post
import kr.pe.karsei.boardtraffic.application.post.domain.Tag
import kr.pe.karsei.boardtraffic.application.post.dto.CategoryDto
import kr.pe.karsei.boardtraffic.application.post.dto.CommentDto
import kr.pe.karsei.boardtraffic.application.post.dto.PostDto
import kr.pe.karsei.boardtraffic.application.post.dto.TagDto
import kr.pe.karsei.boardtraffic.application.post.exception.NotFoundCategoryException
import kr.pe.karsei.boardtraffic.application.post.exception.NotFoundCommentException
import kr.pe.karsei.boardtraffic.application.post.exception.NotFoundPostException
import kr.pe.karsei.boardtraffic.application.post.exception.NotFoundTagException
import kr.pe.karsei.boardtraffic.application.post.port.out.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class PostJpaAdapter(
    private val postRepository: PostRepository,
    private val categoryRepository: CategoryRepository,
    private val commentRepository: CommentRepository,
    private val fileRepository: FileRepository,
    private val tagRepository: TagRepository,
) : PostLoadPort, PostSavePort, CategorySavePort, PostCommentSavePort, PostTagSavePort {
    // ************************************* POSTS
    override fun findPosts(userId: Long?, request: PostDto.PostSearchRequest, pageable: Pageable): Page<PostDto> {
        return postRepository.findPosts(userId, request, pageable)
    }

    override fun findPostsByUser(userId: Long, pageable: Pageable): Page<Post> {
        return postRepository.findByUserId(userId, pageable)
    }

    override fun insertPost(user: User, params: PostDto.InsertPostRequest): Post {
        val category = categoryRepository.findById(params.categoryId)
                .orElseThrow { throw ResponseStatusException(HttpStatus.NOT_FOUND, "카테고리가 존재하지 않습니다.") }
        return postRepository.save(
            Post(
                title = params.title,
                contents = params.contents,
                user = user,
                category = category
        )
        )
    }

    override fun updatePost(params: PostDto.UpdatePostRequest): Post {
        val post = postRepository.findById(params.id)
            .orElseThrow { throw NotFoundPostException() }
        val category = if (params.categoryId != null) {
            categoryRepository.findById(params.categoryId!!).orElse(null)
        } else { null }
        val file = if (params.fileId != null) {
            fileRepository.findById(params.fileId!!).orElse(null)
        } else { null }
        post.update(params.title, params.contents, category, file)
        return postRepository.save(post)
    }

    override fun deletePost(postId: Long): Post {
        val post = postRepository.findById(postId)
            .orElseThrow { throw NotFoundPostException() }
        postRepository.delete(post)
        return post
    }

    // ************************************* CATEGORY
    override fun insertCategory(params: CategoryDto.InsertPostCategory): Category {
        return categoryRepository.save(Category(id = null, title = params.title))
    }

    override fun updateCategory(params: CategoryDto.UpdatePostCategory): Category {
        val category = categoryRepository.findById(params.categoryId!!)
            .orElseThrow { throw NotFoundCategoryException() }
        category.update(title = params.title)
        return categoryRepository.save(category)
    }

    override fun deleteCategory(categoryId: Long): Category {
        val category = categoryRepository.findById(categoryId)
            .orElseThrow { throw NotFoundCategoryException() }
        categoryRepository.delete(category)
        return category
    }

    // ************************************* COMMENT
    override fun insertComment(params: CommentDto.InsertCommentRequest): Comment {
        return commentRepository.save(Comment(id = null, contents = params.contents))
    }

    override fun updateComment(commentId: Long, params: CommentDto.UpdateCommentRequest): Comment {
        val comment = commentRepository.findById(commentId)
            .orElseThrow { throw NotFoundCommentException() }
        comment.update(contents = params.contents)
        return commentRepository.save(comment)
    }

    override fun deleteComment(commentId: Long): Comment {
        val comment = commentRepository.findById(commentId)
            .orElseThrow { throw NotFoundCommentException() }
        commentRepository.delete(comment)
        return comment
    }

    // ************************************* TAG
    override fun insertTag(params: TagDto.InsertTagRequest) {
        tagRepository.save(Tag(id = null, name = params.name, url = params.url))
    }

    override fun updateTag(tagId: Long, params: TagDto.UpdateTagRequest) {
        val tag = tagRepository.findById(tagId)
            .orElseThrow { throw NotFoundTagException() }
        tag.update(name = params.name, url = params.url)
        tagRepository.save(tag)
    }

    override fun deleteTag(tagId: Long) {
        val tag = tagRepository.findById(tagId)
            .orElseThrow { throw NotFoundTagException() }
        tagRepository.delete(tag)
    }
}