package kr.pe.karsei.boardtraffic.fixture

import kr.pe.karsei.boardtraffic.entity.User
import java.time.LocalDateTime

class UserMock {
    companion object {
        fun createUser(): User {
            return User(
                id = 1,
                userId = "testId",
                password = "test1234",
                nickName = "TEST",
                isAdmin = false,
                createdAt = LocalDateTime.of(2023, 1, 13, 1, 2, 3),
                isWithDraw = false,
                status = User.Status.DEFAULT,
                updatedAt = LocalDateTime.of(2023, 1, 13, 1, 2, 3)
            )
        }

        fun createAdmin(): User {
            return User(
                id = 1,
                userId = "adminId",
                password = "test1234",
                nickName = "ADMIN",
                isAdmin = true,
                createdAt = LocalDateTime.of(2023, 1, 13, 1, 2, 3),
                isWithDraw = false,
                status = User.Status.DEFAULT,
                updatedAt = LocalDateTime.of(2023, 1, 13, 1, 2, 3)
            )
        }
    }
}