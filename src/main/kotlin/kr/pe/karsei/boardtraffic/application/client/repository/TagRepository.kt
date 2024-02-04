package kr.pe.karsei.boardtraffic.application.client.repository

import kr.pe.karsei.boardtraffic.application.post.domain.Tag
import org.springframework.data.jpa.repository.JpaRepository

interface TagRepository: JpaRepository<Tag, Long> {
}