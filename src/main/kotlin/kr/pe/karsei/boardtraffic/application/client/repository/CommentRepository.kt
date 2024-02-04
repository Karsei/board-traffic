package kr.pe.karsei.boardtraffic.application.client.repository

import kr.pe.karsei.boardtraffic.application.post.domain.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository: JpaRepository<Comment, Long> {
}