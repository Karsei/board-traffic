package kr.pe.karsei.boardtraffic.application.post.domain

import jakarta.persistence.*

@Entity
open class Category(
    @Id
    @GeneratedValue
    open val id: Long? = null,

    @Column
    open var title: String? = null,
) {
    fun update(title: String) {
        this.title = title
    }
}