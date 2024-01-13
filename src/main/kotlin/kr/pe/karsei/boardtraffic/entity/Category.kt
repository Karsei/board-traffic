package kr.pe.karsei.boardtraffic.entity

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