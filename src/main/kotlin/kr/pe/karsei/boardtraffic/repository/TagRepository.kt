package kr.pe.karsei.boardtraffic.repository

import kr.pe.karsei.boardtraffic.entity.Tag
import org.springframework.data.jpa.repository.JpaRepository

interface TagRepository: JpaRepository<Tag, Long> {
}