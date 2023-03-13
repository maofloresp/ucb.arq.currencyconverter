package ucb.softarch.currencyconverter.dtos

import ucb.softarch.currencyconverter.daos.Conversion

data class GetConversionResponseDTO(val length: Long, val list: List<Conversion>)
