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
    override fun getUserInfo(userId: Long): User? {
        return userRepository.findById(userId).orElse(null)
    }

    override fun getUserInfo(accountId: String, password: String): User? {
        val cryptPassword = SHA256Util.encryptSHA256(password)
        return userRepository.findByUserIdAndPassword(accountId, cryptPassword)
    }

    override fun register(userProfile: UserDto.Register.UserRegisterRequest): User {
        val user = userRepository.findByUserId(userProfile.userId)
        if (user != null) throw ResponseStatusException(HttpStatus.CONFLICT, "중복된 아이디입니다.")

        userProfile.password = SHA256Util.encryptSHA256(userProfile.password)

        return userRepository.save(User.create(userId = userProfile.userId,
                password = userProfile.password,
                nickName = userProfile.nickName))
    }

    override fun updatePassword(userId: Long,
                                beforePassword: String,
                                afterPassword: String): User {
        val encryptPassword = SHA256Util.encryptSHA256(beforePassword)
        val user = userRepository.findByIdAndPassword(userId, encryptPassword)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "올바르지 않는 아이디이거나 비밀번호입니다.")
        val password = SHA256Util.encryptSHA256(afterPassword)
        user.updatePassword(password)
        return userRepository.save(user)
    }

    override fun deleteUser(userId: Long,
                            password: String): User {
        val encryptPassword = SHA256Util.encryptSHA256(password)
        val user = userRepository.findByIdAndPassword(userId, encryptPassword)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "올바르지 않는 아이디이거나 비밀번호입니다.")
        userRepository.delete(user)
        return user
    }
}