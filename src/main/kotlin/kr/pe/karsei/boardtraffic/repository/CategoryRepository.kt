package kr.pe.karsei.boardtraffic.repository

import kr.pe.karsei.boardtraffic.entity.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<Category, Long> {
}