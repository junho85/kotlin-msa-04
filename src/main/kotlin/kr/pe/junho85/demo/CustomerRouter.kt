package kr.pe.junho85.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.router

@Component
class CustomerRouter {
    @Autowired
    lateinit var customerHandler: CustomerHandler

    @Bean
    fun customerRoutes(): RouterFunction<*> = router {
        "/functional".nest {
            "/customer".nest {
                GET("/") {
//                    ok().body(Customer(1, "functional web").toMono(), Customer::class.java)
//                    it: ServerRequest ->
//                        ok().body(Customer(1, "functional web").toMono(), Customer::class.java)
                    it: ServerRequest -> customerHandler.get(it)
                }
            }
        }
    }
}