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
        open val id: Long? = null,

        @Column
        open var userId: String,

        @Column
        open var password: String,

        @Column
        open var nickName: String,

        @Column
        open var isAdmin: Boolean = false,

        @CreatedDate
        @Column(name = "CREATED_AT")
        open var createdAt: LocalDateTime? = null,

        @Column
        open var isWithDraw: Boolean = false,

        @Column
        open var status: Status = Status.DEFAULT,

        @LastModifiedDate
        @Column(name = "UPDATED_AT")
        open var updatedAt: LocalDateTime? = null,
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