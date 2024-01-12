package kr.pe.karsei.boardtraffic.adapter.out

import kr.pe.karsei.boardtraffic.dto.CategoryDto
import kr.pe.karsei.boardtraffic.dto.CommentDto
import kr.pe.karsei.boardtraffic.dto.PostDto
import kr.pe.karsei.boardtraffic.dto.TagDto
import kr.pe.karsei.boardtraffic.entity.Category
import kr.pe.karsei.boardtraffic.entity.Comment
import kr.pe.karsei.boardtraffic.entity.Post
import kr.pe.karsei.boardtraffic.entity.Tag
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
    override fun findPostsByUser(userId: Long, pageable: Pageable): Page<Post> {
        return postRepository.findByUserId(userId, pageable)
    }

    override fun findPosts(request: PostDto.PostSearchRequest, pageable: Pageable): Page<PostDto> {
        return postRepository.findPosts(request, pageable)
    }

    override fun updatePost(request: PostDto.PostUpdateRequest) {
        val post = postRepository.findById(request.id)
            .orElseThrow { throw ResponseStatusException(HttpStatus.NOT_FOUND, "포스트가 존재하지 않습니다.") }
        val category = if (request.categoryId != null) {
            categoryRepository.findById(request.categoryId).orElse(null)
        } else { null }
        val file = if (request.fileId != null) {
            fileRepository.findById(request.fileId).orElse(null)
        } else { null }
        post.update(request.title, request.contents, category, file)
        postRepository.save(post)
    }

    override fun deletePost(postId: Long) {
        val post = postRepository.findById(postId)
            .orElseThrow { throw ResponseStatusException(HttpStatus.NOT_FOUND, "포스트가 존재하지 않습니다.") }
        postRepository.delete(post)
    }

    // ************************************* CATEGORY
    override fun insertCategory(params: CategoryDto.InsertPostCategory) {
        categoryRepository.save(Category(id = null, title = params.title))
    }

    override fun updateCategory(categoryId: Long, params: CategoryDto.UpdatePostCategory) {
        val category = categoryRepository.findById(categoryId)
            .orElseThrow { throw ResponseStatusException(HttpStatus.NOT_FOUND, "카테고리가 존재하지 않습니다.") }
        category.update(title = params.title)
        categoryRepository.save(category)
    }

    override fun deleteCategory(categoryId: Long) {
        val category = categoryRepository.findById(categoryId)
            .orElseThrow { throw ResponseStatusException(HttpStatus.NOT_FOUND, "카테고리가 존재하지 않습니다.") }
        categoryRepository.delete(category)
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