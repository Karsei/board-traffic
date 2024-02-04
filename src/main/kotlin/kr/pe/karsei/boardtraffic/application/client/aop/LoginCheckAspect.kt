package kr.pe.karsei.boardtraffic.application.client.aop

import jakarta.servlet.http.HttpSession
import kr.pe.karsei.boardtraffic.core.util.SessionUtil
import kr.pe.karsei.boardtraffic.core.util.logger
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import org.springframework.web.server.ResponseStatusException

@Aspect
@Component
@Order(Ordered.LOWEST_PRECEDENCE)
class LoginCheckAspect {
    private val logger = logger()

    @Around("@annotation(kr.pe.karsei.boardtraffic.application.client.aop.LoginCheck) && @annotation(loginCheck)")
    fun adminLoginCheck(proceedingJoinPoint: ProceedingJoinPoint, loginCheck: LoginCheck): Any {
        val session = (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes).request.session as HttpSession

        val userId = when (loginCheck.type.toString()) {
            "ADMIN" -> {
                SessionUtil.getLoginAdminId(session)
            }
            else -> {
                SessionUtil.getLoginMemberId(session)
            }
        } ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "로그인한 ID를 확인해주세요.")

        logger.info(proceedingJoinPoint.toString() + " userId: " + userId)

        // controller 에서 첫 번째 인자로 전달
        val modifiedArgs = proceedingJoinPoint.args
        if (proceedingJoinPoint.args != null)
            modifiedArgs[0] = userId

        return proceedingJoinPoint.proceed(modifiedArgs)
    }
}