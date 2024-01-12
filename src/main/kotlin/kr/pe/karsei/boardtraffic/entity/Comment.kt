package kr.pe.karsei.boardtraffic.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
open class Comment(
    @Id
    @GeneratedValue
    open val id: Long? = null,

    @Column
    open var contents: String,
) {
    fun update(contents: String) {
        this.contents = contents
    }
}