package kr.pe.karsei.boardtraffic.application.post.domain

import jakarta.persistence.*
import kr.pe.karsei.boardtraffic.application.client.domain.User
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@EntityListeners(AuditingEntityListener::class)
open class Post(
    @Id
    @GeneratedValue
    open val id: Long? = null,

    @Column
    open var title: String,

    @Column
    open var isAdmin: Boolean = false,

    @Column
    open var contents: String,

    @Column
    open var views: Int = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    open var category: Category,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    open var user: User,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    open var file: File? = null,

    @CreatedDate
    @Column(name = "CREATED_AT")
    open var createdAt: LocalDateTime? = null,

    @LastModifiedDate
    @Column(name = "UPDATED_AT")
    open var updatedAt: LocalDateTime? = null,
) {
    companion object {
        fun create(user: User, category: Category, title: String, contents: String): Post {
            return Post(
                title = title,
                contents = contents,
                category = category,
                user = user
                )
        }
    }

    fun update(title: String, contents: String, category: Category?, file: File?) {
        this.title = title
        this.contents = contents
        if (category != null) this.category = category
        if (file != null) this.file = file
    }
}