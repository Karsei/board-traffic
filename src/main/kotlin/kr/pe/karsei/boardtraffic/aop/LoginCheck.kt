package kr.pe.karsei.boardtraffic.aop

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
annotation class LoginCheck(val type: UserType) {
    enum class UserType {
        USER, ADMIN
    }
}
