package kr.pe.karsei.boardtraffic.aop

import jakarta.servlet.http.HttpSession
import kr.pe.karsei.boardtraffic.util.SessionUtil
import kr.pe.karsei.boardtraffic.util.logger
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

@Aspect
class LoginCheckAspect {
    private val logger = logger()

    @Around("@annotation(kr.pe.karsei.boardtraffic.aop.LoginCheck) && @annotation(loginCheck)")
    fun adminLoginCheck(proceedingJoinPoint: ProceedingJoinPoint, loginCheck: LoginCheck): Any {
        val session = (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes).request.session as HttpSession

        val userId = when (loginCheck.type.toString()) {
            "ADMIN" -> {
                SessionUtil.getLoginAdminId(session)
            }
            else -> {
                SessionUtil.getLoginMemberId(session)
            }
        }
        logger.info(proceedingJoinPoint.toString() + " accountName: " + userId)

        // controller 에서 첫 번째 인자로 전달
        val modifiedArgs = proceedingJoinPoint.args
        if (proceedingJoinPoint.args != null)
            modifiedArgs[0] = userId

        return proceedingJoinPoint.proceed(modifiedArgs)
    }
}