package kr.pe.junho85.demo

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.router
import reactor.kotlin.core.publisher.toMono

@Component
class CustomerRouter {
    @Bean
    fun customerRoutes(): RouterFunction<*> = router {
        "/functional".nest {
            "/customer".nest {
                GET("/") {
//                    ok().body(Customer(1, "functional web").toMono(), Customer::class.java)
                    it: ServerRequest ->
                        ok().body(Customer(1, "functional web").toMono(), Customer::class.java)
                }
            }
        }
    }
}