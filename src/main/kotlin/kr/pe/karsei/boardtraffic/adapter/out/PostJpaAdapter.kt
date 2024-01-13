package kr.pe.karsei.boardtraffic.adapter.out

import kr.pe.karsei.boardtraffic.dto.CategoryDto
import kr.pe.karsei.boardtraffic.dto.CommentDto
import kr.pe.karsei.boardtraffic.dto.PostDto
import kr.pe.karsei.boardtraffic.dto.TagDto
import kr.pe.karsei.boardtraffic.entity.*
import kr.pe.karsei.boardtraffic.port.out.*
import kr.pe.karsei.boardtraffic.repository.*
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
        return postRepository.save(Post(
                title = params.title,
                contents = params.contents,
                user = user,
                category = category
        ))
    }

    override fun updatePost(params: PostDto.PostUpdateRequest): Post {
        val post = postRepository.findById(params.id)
            .orElseThrow { throw ResponseStatusException(HttpStatus.NOT_FOUND, "포스트가 존재하지 않습니다.") }
        val category = if (params.categoryId != null) {
            categoryRepository.findById(params.categoryId).orElse(null)
        } else { null }
        val file = if (params.fileId != null) {
            fileRepository.findById(params.fileId).orElse(null)
        } else { null }
        post.update(params.title, params.contents, category, file)
        return postRepository.save(post)
    }

    override fun deletePost(postId: Long): Post {
        val post = postRepository.findById(postId)
            .orElseThrow { throw ResponseStatusException(HttpStatus.NOT_FOUND, "포스트가 존재하지 않습니다.") }
        postRepository.delete(post)
        return post
    }

    // ************************************* CATEGORY
    override fun insertCategory(params: CategoryDto.InsertPostCategory): Category {
        return categoryRepository.save(Category(id = null, title = params.title))
    }

    override fun updateCategory(categoryId: Long, params: CategoryDto.UpdatePostCategory): Category {
        val category = categoryRepository.findById(categoryId)
            .orElseThrow { throw ResponseStatusException(HttpStatus.NOT_FOUND, "카테고리가 존재하지 않습니다.") }
        category.update(title = params.title)
        return categoryRepository.save(category)
    }

    override fun deleteCategory(categoryId: Long): Category {
        val category = categoryRepository.findById(categoryId)
            .orElseThrow { throw ResponseStatusException(HttpStatus.NOT_FOUND, "카테고리가 존재하지 않습니다.") }
        categoryRepository.delete(category)
        return category
    }

    // ************************************* COMMENT
    override fun insertComment(params: CommentDto.InsertCommentRequest) {
        commentRepository.save(Comment(id = null, contents = params.contents))
    }

    override fun updateComment(commentId: Long, params: CommentDto.UpdateCommentRequest) {
        val comment = commentRepository.findById(commentId)
            .orElseThrow { throw ResponseStatusException(HttpStatus.NOT_FOUND, "댓글이 존재하지 않습니다.") }
        comment.update(contents = params.contents)
        commentRepository.save(comment)
    }

    override fun deleteComment(commentId: Long) {
        val comment = commentRepository.findById(commentId)
            .orElseThrow { throw ResponseStatusException(HttpStatus.NOT_FOUND, "댓글이 존재하지 않습니다.") }
        commentRepository.delete(comment)
    }

    // ************************************* TAG
    override fun insertTag(params: TagDto.InsertTagRequest) {
        tagRepository.save(Tag(id = null, name = params.name, url = params.url))
    }

    override fun updateTag(tagId: Long, params: TagDto.UpdateTagRequest) {
        val tag = tagRepository.findById(tagId)
            .orElseThrow { throw ResponseStatusException(HttpStatus.NOT_FOUND, "태그가 존재하지 않습니다.") }
        tag.update(name = params.name, url = params.url)
        tagRepository.save(tag)
    }

    override fun deleteTag(tagId: Long) {
        val tag = tagRepository.findById(tagId)
            .orElseThrow { throw ResponseStatusException(HttpStatus.NOT_FOUND, "태그가 존재하지 않습니다.") }
        tagRepository.delete(tag)
    }
}