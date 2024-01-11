package kr.pe.karsei.boardtraffic.repository

import kr.pe.karsei.boardtraffic.entity.File
import org.springframework.data.jpa.repository.JpaRepository

interface FileRepository : JpaRepository<File, Long> {
}