package kr.pe.karsei.boardtraffic.core.dto.rest

interface SimpleResponse<T> {
    fun getData(): T
}