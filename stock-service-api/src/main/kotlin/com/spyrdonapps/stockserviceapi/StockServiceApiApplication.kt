package com.spyrdonapps.stockserviceapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StockServiceApiApplication

fun main(args: Array<String>) {
    runApplication<StockServiceApiApplication>(*args)
}
