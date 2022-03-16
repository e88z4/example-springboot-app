package com.ea.maxis.app

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class Echo {

    @GetMapping(value = ["/echo"])
    fun echo(@RequestParam("echo", required = true) echo: String) = EchoResponse(echo = echo)
}

data class EchoResponse(
    val echo: String
)
