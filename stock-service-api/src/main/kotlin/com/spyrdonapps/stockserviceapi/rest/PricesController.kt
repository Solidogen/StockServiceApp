package com.spyrdonapps.stockserviceapi.rest

import com.spyrdonapps.stockserviceapi.model.StockPrice
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.time.Duration
import java.time.LocalDateTime
import java.util.concurrent.ThreadLocalRandom

@RestController
class PricesController {

    @GetMapping(
        value = ["/stocks/{symbol}"],
        produces = [MediaType.TEXT_EVENT_STREAM_VALUE]
    )
    fun getPrices(@PathVariable symbol: String): Flux<StockPrice> =
        Flux.interval(Duration.ofSeconds(1))
            .map { StockPrice(symbol, randomStockPrice(), LocalDateTime.now()) }

    private fun randomStockPrice(): Double =
        ThreadLocalRandom.current().nextDouble(100.0)
}
