package kr.pe.karsei.boardtraffic.repository

import kr.pe.karsei.boardtraffic.entity.Post
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository: JpaRepository<Post, Long>, PostRepositorySupport {
    fun findByUserId(userId: Long, pageable: Pageable): Page<Post>
}