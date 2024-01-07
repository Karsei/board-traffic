package kr.pe.karsei.boardtraffic.dto.response

import kr.pe.karsei.boardtraffic.dto.UserDto

class LoginResponse(
        private val result: LoginStatus,
        private val userDto: UserDto? = null,
) {
    companion object {
        private val FAIL = LoginResponse(LoginStatus.FAIL, null)

        fun success(userDTO: UserDto): LoginResponse {
            return LoginResponse(LoginStatus.SUCCESS, userDTO)
        }
    }

    enum class LoginStatus {
        SUCCESS,
        FAIL,
        DELETED
    }
}