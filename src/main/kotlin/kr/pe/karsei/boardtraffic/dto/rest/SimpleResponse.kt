package kr.pe.karsei.boardtraffic.dto.rest

interface SimpleResponse<T> {
    fun getData(): T
}