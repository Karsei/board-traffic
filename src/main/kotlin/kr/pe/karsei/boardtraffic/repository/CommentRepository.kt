package kr.pe.karsei.boardtraffic.repository

import kr.pe.karsei.boardtraffic.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository: JpaRepository<Comment, Long> {
}