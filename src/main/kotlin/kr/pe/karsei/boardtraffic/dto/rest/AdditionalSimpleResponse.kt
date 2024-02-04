package kr.pe.karsei.boardtraffic.dto.rest

interface AdditionalSimpleResponse<T>: SimpleResponse<T> {
    fun getMeta(): SimpleMeta
}