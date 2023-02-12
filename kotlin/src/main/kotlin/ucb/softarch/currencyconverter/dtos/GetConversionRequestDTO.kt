package ucb.softarch.currencyconverter.dtos

import java.math.BigDecimal

data class GetConversionRequestDTO(val from: String, val to: String, val amount: BigDecimal)
