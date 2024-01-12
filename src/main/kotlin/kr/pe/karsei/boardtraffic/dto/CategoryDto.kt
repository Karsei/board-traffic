package kr.pe.karsei.boardtraffic.dto

class CategoryDto {
    data class InsertPostCategory(
        val title: String,
    )

    data class UpdatePostCategory(
        val title: String,
    )
}