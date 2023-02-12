package ucb.softarch.currencyconverter.dtos

import java.math.BigDecimal

data class GetConversionResponseDTO(val currency: String, val amount: BigDecimal)
