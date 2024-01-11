package kr.pe.karsei.boardtraffic.port.out

import kr.pe.karsei.boardtraffic.dto.PostDto

interface PostSavePort {
    fun updatePost(request: PostDto.PostUpdateRequest)

    fun deletePost(postId: Long)
}