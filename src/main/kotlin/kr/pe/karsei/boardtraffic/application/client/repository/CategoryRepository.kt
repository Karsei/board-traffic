package kr.pe.karsei.boardtraffic.application.client.repository

import kr.pe.karsei.boardtraffic.application.post.domain.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<Category, Long> {
}