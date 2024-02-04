package kr.pe.karsei.boardtraffic.application.client.repository

import kr.pe.karsei.boardtraffic.application.post.domain.Post
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository: JpaRepository<Post, Long>, PostRepositorySupport {
    fun findByUserId(userId: Long, pageable: Pageable): Page<Post>
}