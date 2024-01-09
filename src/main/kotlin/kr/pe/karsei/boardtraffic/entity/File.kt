package kr.pe.karsei.boardtraffic.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
open class File(
    @Id
    @GeneratedValue
    open val id: Long? = null,

    @Column
    open var path: String,

    @Column
    open var name: String,

    @Column
    open var extension: String,
)