package kr.pe.karsei.boardtraffic.application.post.port.out

import kr.pe.karsei.boardtraffic.application.post.dto.PostDto
import kr.pe.karsei.boardtraffic.application.post.domain.Post
import kr.pe.karsei.boardtraffic.application.client.domain.User

interface PostSavePort {
    fun insertPost(user: User, params: PostDto.InsertPostRequest): Post

    fun updatePost(params: PostDto.UpdatePostRequest): Post

    fun deletePost(postId: Long): Post
}