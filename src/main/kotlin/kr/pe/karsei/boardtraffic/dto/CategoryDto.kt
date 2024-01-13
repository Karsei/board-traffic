package kr.pe.karsei.boardtraffic.dto

class CategoryDto(
        val id: Long?,
        val title: String?
) {
    data class InsertPostCategory(
        val title: String,
    )

    data class UpdatePostCategory(
        val title: String,
    )
}