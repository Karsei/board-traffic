package kr.pe.karsei.boardtraffic.application.client.service

import kr.pe.karsei.boardtraffic.application.client.dto.UserDto
import kr.pe.karsei.boardtraffic.application.client.domain.User

class UserMapper {
    companion object {
        fun mapToEntityToDto(entity: User?): UserDto? {
            if (entity == null) return null
            return UserDto(
                    id = entity.id,
                    userId = entity.userId,
                    password = entity.password,
                    nickName = entity.nickName,
                    isAdmin = entity.isAdmin,
                    createdAt = entity.createdAt,
                    isWithDraw = entity.isWithDraw,
                    status = UserDto.Status.valueOf(entity.status.name),
                    updatedAt = entity.updatedAt,
            )
        }
    }
}