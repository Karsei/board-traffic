package kr.pe.karsei.boardtraffic.adapter.out

import kr.pe.karsei.boardtraffic.dto.UserDto
import kr.pe.karsei.boardtraffic.entity.User
import kr.pe.karsei.boardtraffic.port.out.UserLoadPort
import kr.pe.karsei.boardtraffic.port.out.UserSavePort
import kr.pe.karsei.boardtraffic.repository.UserRepository
import kr.pe.karsei.boardtraffic.util.SHA256Util
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UserJpaAdapter(
        private val userRepository: UserRepository,
) : UserLoadPort, UserSavePort {
    override fun getUserInfo(userId: String): UserDto? {
        val user = userRepository.findByUserId(userId)
        return mapToEntityToDto(user)
    }

    override fun getUserInfo(userId: String, password: String): UserDto? {
        val cryptPassword = SHA256Util.encryptSHA256(password)
        val user = userRepository.findByUserIdAndPassword(userId, cryptPassword)
        return mapToEntityToDto(user)
    }

    override fun register(userProfile: UserDto.Register.UserRegisterRequest): UserDto {
        var user = userRepository.findByUserId(userProfile.userId)
        if (user != null) throw ResponseStatusException(HttpStatus.CONFLICT, "중복된 아이디입니다.")

        userProfile.password = SHA256Util.encryptSHA256(userProfile.password)

        user = userRepository.save(User.create(userId = userProfile.userId,
                password = userProfile.password,
                nickName = userProfile.nickName))
        return mapToEntityToDto(user)!!
    }

    override fun updatePassword(userId: String,
                                beforePassword: String,
                                afterPassword: String) {
        val encryptPassword = SHA256Util.encryptSHA256(beforePassword)
        val user = userRepository.findByUserIdAndPassword(userId, encryptPassword)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "올바르지 않는 아이디이거나 비밀번호입니다.")
        val password = SHA256Util.encryptSHA256(afterPassword)
        user.updatePassword(password)
        userRepository.save(user)
    }

    override fun deleteId(userId: String,
                          password: String) {
        val encryptPassword = SHA256Util.encryptSHA256(password)
        val user = userRepository.findByUserIdAndPassword(userId, encryptPassword)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "올바르지 않는 아이디이거나 비밀번호입니다.")
        userRepository.delete(user)
    }

    private fun mapToEntityToDto(entity: User?): UserDto? {
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