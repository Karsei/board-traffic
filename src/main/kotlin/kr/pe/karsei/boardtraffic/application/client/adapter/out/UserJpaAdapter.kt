package kr.pe.karsei.boardtraffic.application.client.adapter.out

import kr.pe.karsei.boardtraffic.application.client.dto.UserDto
import kr.pe.karsei.boardtraffic.application.client.domain.User
import kr.pe.karsei.boardtraffic.application.client.exception.ConflictClientException
import kr.pe.karsei.boardtraffic.application.client.exception.NotFoundOrInvalidPasswordClientException
import kr.pe.karsei.boardtraffic.application.client.port.out.UserLoadPort
import kr.pe.karsei.boardtraffic.application.client.port.out.UserSavePort
import kr.pe.karsei.boardtraffic.application.post.repository.UserRepository
import kr.pe.karsei.boardtraffic.core.util.SHA256Util
import org.springframework.stereotype.Service

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
        if (user != null) throw ConflictClientException()

        userProfile.password = SHA256Util.encryptSHA256(userProfile.password)

        return userRepository.save(
            User.create(userId = userProfile.userId,
                password = userProfile.password,
                nickName = userProfile.nickName))
    }

    override fun updatePassword(userId: Long,
                                beforePassword: String,
                                afterPassword: String): User {
        val encryptPassword = SHA256Util.encryptSHA256(beforePassword)
        val user = userRepository.findByIdAndPassword(userId, encryptPassword)
                ?: throw NotFoundOrInvalidPasswordClientException()
        val password = SHA256Util.encryptSHA256(afterPassword)
        user.updatePassword(password)
        return userRepository.save(user)
    }

    override fun deleteUser(userId: Long,
                            password: String): User {
        val encryptPassword = SHA256Util.encryptSHA256(password)
        val user = userRepository.findByIdAndPassword(userId, encryptPassword)
                ?: throw NotFoundOrInvalidPasswordClientException()
        userRepository.delete(user)
        return user
    }
}