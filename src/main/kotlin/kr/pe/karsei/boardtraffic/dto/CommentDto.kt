package kr.pe.karsei.boardtraffic.dto

class CommentDto(
        val id: Long?,
        val contents: String?,
        val subCommentId: Long?
) {
    data class InsertCommentRequest(
        var userId: Long?,
        var postId: Long?,
        val contents: String,
        val subCommentId: Long?,
    ) {
        fun setMandatory(userId: Long, postId: Long) {
            this.userId = userId
            this.postId = postId
        }
    }

    data class UpdateCommentRequest(
        var userId: Long?,
        var postId: Long?,
        var commentId: Long?,
        val contents: String,
        val subCommentId: Long?,
    ) {
        fun setMandatory(userId: Long, postId: Long, commentId: Long) {
            this.userId = userId
            this.postId = postId
            this.commentId = commentId
        }
    }
}