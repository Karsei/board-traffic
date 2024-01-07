package kr.pe.karsei.boardtraffic.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@EntityListeners(AuditingEntityListener::class)
open class User(
        @Id
        @GeneratedValue
        val id: Long? = null,

        @Column
        var userId: String,

        @Column
        var password: String,

        @Column
        var nickName: String,

        @Column
        val isAdmin: Boolean = false,

        @CreatedDate
        @Column(name = "UPDATED_AT")
        var createdAt: LocalDateTime? = null,

        @Column
        var isWithDraw: Boolean = false,

        @Column
        var status: Status = Status.DEFAULT,

        @LastModifiedDate
        @Column(name = "UPDATED_AT")
        var updatedAt: LocalDateTime? = null,
) {
    fun updatePassword(password: String) {
        this.password = password
    }

    companion object {
        fun create(userId: String, password: String, nickName: String): User {
            return User(null, userId, password, nickName)
        }
    }

    enum class Status {
        DEFAULT, ADMIN, DELETED
    }
}