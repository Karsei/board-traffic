package kr.pe.karsei.boardtraffic.util

import jakarta.servlet.http.HttpSession

class SessionUtil {
    companion object {
        private val LOGIN_MEMBER_ID = "LOGIN_MEMBER_ID"
        private val LOGIN_ADMIN_ID = "LOGIN_ADMIN_ID"

        fun getLoginMemberId(session: HttpSession): Long? {
            return session.getAttribute(LOGIN_MEMBER_ID) as Long?
        }

        fun setLoginMemberId(session: HttpSession, userId: Long) {
            session.setAttribute(LOGIN_MEMBER_ID, userId)
        }

        fun getLoginAdminId(session: HttpSession): Long? {
            return session.getAttribute(LOGIN_ADMIN_ID) as Long?
        }

        fun setLoginAdminId(session: HttpSession, userId: Long) {
            session.setAttribute(LOGIN_ADMIN_ID, userId)
        }

        fun clear(session: HttpSession) {
            session.invalidate()
        }
    }
}