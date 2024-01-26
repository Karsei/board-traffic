package kr.pe.karsei.boardtraffic.dto.rest

interface CommonResponse<T> {
    fun getData(): T
}