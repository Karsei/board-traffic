package kr.pe.karsei.boardtraffic.util

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.mock.web.MockHttpSession

class SessionUtilTest {
    @Test
    fun testMemberIdWithAnonymous() {
        // given
        val session = MockHttpSession()

        // when
        val resultMember = SessionUtil.getLoginMemberId(session)
        val resultAdmin = SessionUtil.getLoginAdminId(session)

        // then
        assertThat(resultMember).isNull()
        assertThat(resultAdmin).isNull()
    }

    @Test
    fun testMemberId() {
        // given
        val session = MockHttpSession()
        val userId = 1L

        // when
        SessionUtil.setLoginMemberId(session, userId)
        val resultMember = SessionUtil.getLoginMemberId(session)
        val resultAdmin = SessionUtil.getLoginAdminId(session)

        // then
        assertAll(
            { assertThat(resultMember).isNotNull() },
            { assertThat(resultMember).isEqualTo(userId) },
            { assertThat(resultAdmin).isNull() },
        )
    }

    @Test
    fun testAdminIdWithAnonymous() {
        // given
        val session = MockHttpSession()

        // when
        val resultAdmin = SessionUtil.getLoginAdminId(session)
        val resultMember = SessionUtil.getLoginMemberId(session)

        // then
        assertAll(
            { assertThat(resultAdmin).isNull() },
            { assertThat(resultMember).isNull() },
        )
    }

    @Test
    fun testAdminId() {
        // given
        val session = MockHttpSession()
        val userId = 1L

        // when
        SessionUtil.setLoginAdminId(session, userId)
        val resultAdmin = SessionUtil.getLoginAdminId(session)
        val resultMember = SessionUtil.getLoginMemberId(session)

        // then
        assertAll(
            { assertThat(resultAdmin).isNotNull() },
            { assertThat(resultAdmin).isEqualTo(userId) },
            { assertThat(resultMember).isNull() },
        )
    }

    @Test
    fun testClear() {
        // given
        val session = MockHttpSession()
        val userId = 1L

        // when
        SessionUtil.setLoginMemberId(session, userId)
        val beforeMember = SessionUtil.getLoginMemberId(session)
        SessionUtil.clear(session)

        // then
        assertAll(
            { assertThat(beforeMember).isNotNull() },
            { assertThrows(IllegalStateException::class.java) { SessionUtil.getLoginMemberId(session) } }
        )
    }
}