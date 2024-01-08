package kr.pe.karsei.boardtraffic

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class BoardTrafficApplication

fun main(args: Array<String>) {
    runApplication<BoardTrafficApplication>(*args)
}
