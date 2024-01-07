package kr.pe.karsei.boardtraffic.util

import jakarta.servlet.http.HttpSession

class SessionUtil {
    companion object {
        private val LOGIN_MEMBER_ID = "LOGIN_MEMBER_ID"
        private val LOGIN_ADMIN_ID = "LOGIN_ADMIN_ID"

        fun getLoginMemberId(session: HttpSession): String {
            return session.getAttribute(LOGIN_MEMBER_ID) as String
        }

        fun setLoginMemberId(session: HttpSession, id: String) {
            session.setAttribute(LOGIN_MEMBER_ID, id)
        }

        fun getLoginAdminId(session: HttpSession): String {
            return session.getAttribute(LOGIN_ADMIN_ID) as String
        }

        fun setLoginAdminId(session: HttpSession, id: String) {
            session.setAttribute(LOGIN_ADMIN_ID, id)
        }

        fun clear(session: HttpSession) {
            session.invalidate()
        }
    }
}