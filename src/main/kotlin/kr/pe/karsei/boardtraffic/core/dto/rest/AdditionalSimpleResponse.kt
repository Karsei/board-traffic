package kr.pe.karsei.boardtraffic.core.dto.rest

interface AdditionalSimpleResponse<T>: SimpleResponse<T> {
    fun getMeta(): SimpleMeta
}