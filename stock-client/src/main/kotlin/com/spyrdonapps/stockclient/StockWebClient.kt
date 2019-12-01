package com.spyrdonapps.stockclient

import com.spyrdonapps.stockclient.model.StockPrice
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import java.io.IOException
import java.time.Duration

class StockWebClient(private val webClient: WebClient) {

    fun getPricesForSymbol(symbol: String): Flux<StockPrice> =
        webClient.get()
            .uri("http://localhost:8080/stocks/${symbol}")
            .retrieve()
            .bodyToFlux(StockPrice::class.java)
            .retryBackoff(5, Duration.ofSeconds(1), Duration.ofSeconds(20))
            .doOnError(IOException::class.java) { e -> println(e) }
}
