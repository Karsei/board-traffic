package kr.pe.karsei.boardtraffic.port.out

import kr.pe.karsei.boardtraffic.dto.PostDto
import kr.pe.karsei.boardtraffic.entity.Post
import kr.pe.karsei.boardtraffic.entity.User

interface PostSavePort {
    fun insertPost(user: User, params: PostDto.InsertPostRequest): Post

    fun updatePost(params: PostDto.UpdatePostRequest): Post

    fun deletePost(postId: Long): Post
}