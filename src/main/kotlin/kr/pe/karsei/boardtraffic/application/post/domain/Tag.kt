package kr.pe.karsei.boardtraffic.application.post.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
open class Tag(
    @Id
    @GeneratedValue
    open val id: Long? = null,

    @Column
    open var name: String,

    @Column
    open var url: String,
) {
    fun update(name: String, url: String) {
        this.name = name
        this.url = url
    }
}