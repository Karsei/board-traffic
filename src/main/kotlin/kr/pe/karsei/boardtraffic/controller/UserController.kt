package kr.pe.karsei.boardtraffic.controller

import kr.pe.karsei.boardtraffic.service.UserService
import lombok.extern.log4j.Log4j2
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Log4j2
@RestController
@RequestMapping("/users")
class UserController(
        private val userService: UserService,
) {
}