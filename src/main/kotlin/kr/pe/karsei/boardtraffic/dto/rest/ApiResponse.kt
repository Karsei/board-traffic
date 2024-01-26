package kr.pe.karsei.boardtraffic.dto.rest

class ApiResponse<T>(
        private val data: T,
): SimpleResponse<T> {
    override fun getData(): T {
        return this.data
    }
}