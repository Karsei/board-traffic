package kr.pe.karsei.boardtraffic.application.post.dto

class CategoryDto(
        val id: Long?,
        val title: String?
) {
    data class InsertPostCategory(
        var userId: Long?,
        val title: String,
    ) {
        fun setMandatory(userId: Long) {
            this.userId = userId
        }
    }

    data class UpdatePostCategory(
        var userId: Long?,
        var categoryId: Long?,
        val title: String,
    ) {
        fun setMandatory(userId: Long, categoryId: Long) {
            this.userId = userId
            this.categoryId = categoryId
        }
    }
}