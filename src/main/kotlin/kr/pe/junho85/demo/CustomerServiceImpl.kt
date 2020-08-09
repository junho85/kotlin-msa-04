package kr.pe.junho85.demo

import kr.pe.junho85.demo.Customer.*
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.core.publisher.toMono
import java.util.concurrent.ConcurrentHashMap

@Component
class CustomerServiceImpl : CustomerService {
    companion object {
        val initialCustomers = arrayOf(
                Customer(1, "Kotlin"),
                Customer(2, "Spring"),
                Customer(3, "Microservice", Telephone("+44", "7123456789")))
    }

    val customers = ConcurrentHashMap<Int, Customer>(initialCustomers.associateBy(Customer::id))

    override fun getCustomer(id: Int): Mono<Customer>? = customers[id]?.toMono()

    override fun searchCustomers(nameFilter: String): Flux<Customer> =
            customers.filter {
                it.value.name.contains(nameFilter, true)
            }.map(Map.Entry<Int, Customer>::value).toFlux()

//    override fun createCustomer(customerMono: Mono<Customer>): Mono<*> {
//        return customerMono.subscribe {
//            customers[it.id] = it
//        }.toMono()
//    }

    override fun createCustomer(customerMono: Mono<Customer>): Mono<*> =
            customerMono.map {
                customers[it.id] = it
                it
//                Mono.empty<Any>()
            }

}