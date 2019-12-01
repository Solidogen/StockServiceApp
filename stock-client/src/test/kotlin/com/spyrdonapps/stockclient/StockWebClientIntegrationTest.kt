package com.spyrdonapps.stockclient

import org.hamcrest.Matchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test
import org.springframework.web.reactive.function.client.WebClient

internal class StockWebClientIntegrationTest {

    private val webClient = WebClient.builder().build()

    @Test
    internal fun shouldRetrieveStockPricesFromTheService() {
        val stockWebClient = StockWebClient(webClient)

        val pricesFlux = stockWebClient.getPricesForSymbol("SYMBOL")
        val fivePricesFlux = pricesFlux.take(5)

        assertThat(fivePricesFlux.count().block(), `is`(5L))
        assertThat(fivePricesFlux.blockFirst()!!.symbol, `is`("SYMBOL"))
    }
}
