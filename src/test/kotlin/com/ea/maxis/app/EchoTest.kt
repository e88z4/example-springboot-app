package com.ea.maxis.app

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor
import org.springframework.test.context.TestConstructor.AutowireMode
import org.springframework.test.web.reactive.server.WebTestClient
import java.util.UUID

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestConstructor(autowireMode = AutowireMode.ALL)
class EchoTest(
    private val client: WebTestClient
) {

    @Test
    fun testEcho() {
        val value = UUID.randomUUID().toString()
        client.get().uri {
            it.path("/echo")
                .queryParam("echo", value)
                .build()
        }.exchange()
            .expectStatus().is2xxSuccessful
            .expectBody().jsonPath("echo")
            .isEqualTo(value)
    }
}
