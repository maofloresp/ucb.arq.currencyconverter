package ucb.softarch.currencyconverter.dtos

import ucb.softarch.currencyconverter.dtos.external.*

data class ExternalConvertDTO(val meta: ExternalMeta, val response: ExternalResponse)
