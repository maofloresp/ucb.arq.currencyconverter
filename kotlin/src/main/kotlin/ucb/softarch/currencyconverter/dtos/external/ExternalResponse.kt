package ucb.softarch.currencyconverter.dtos.external

import java.math.BigDecimal

data class ExternalResponse
(
        val timestamp: Int,
        val date: String,
        val from: String,
        val to: String,
        val amount: BigDecimal,
        val value: BigDecimal
)
