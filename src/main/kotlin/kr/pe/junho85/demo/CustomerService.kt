package kr.pe.junho85.demo

interface CustomerService {
    fun getCustomer(id: Int): Customer?
    fun searchCustomers(nameFilter: String): List<Customer>
}