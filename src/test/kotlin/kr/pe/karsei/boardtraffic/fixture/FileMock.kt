package kr.pe.karsei.boardtraffic.fixture

import kr.pe.karsei.boardtraffic.entity.File

class FileMock {
    companion object {
        fun createFile(): File {
            return File(id = 1L,
                path = "/tmp",
                name = "some_image",
                extension = "png")
        }
    }
}