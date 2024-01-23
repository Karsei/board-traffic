package kr.pe.karsei.boardtraffic.repository

import kr.pe.karsei.boardtraffic.config.db.QueryDslConfiguration
import kr.pe.karsei.boardtraffic.fixture.FileMock
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(QueryDslConfiguration::class)
class FileRepositoryTest @Autowired constructor(
    private val fileRepository: FileRepository,
) {
    @BeforeEach
    fun setup() {
        val file = FileMock.createFile()
        fileRepository.save(file)
    }
}