package ucb.softarch.currencyconverter.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ucb.softarch.currencyconverter.daos.Conversion
import ucb.softarch.currencyconverter.dtos.GetConversionRequestDTO
import ucb.softarch.currencyconverter.services.ConversionService
import ucb.softarch.currencyconverter.dtos.GetConversionResponseDTO
import ucb.softarch.currencyconverter.utils.HasLogging
import java.math.BigDecimal
import java.util.*
import java.util.logging.Logger

@RestController
class ConversionController @Autowired constructor(private val service: ConversionService) : HasLogging() {
    @PostMapping("/conversion")
    fun post(params: GetConversionRequestDTO): GetConversionResponseDTO
    {
        logger.info("POST /conversion to=${params.to}&from=${params.from}&amount=${params.amount}")
        return service.convert(params.from, params.to, params.amount)
    }

    @GetMapping("/conversions")
    fun get(@RequestParam(defaultValue = "1") page: Int) : List<Conversion>
    {
        return service.getConversions(page)
    }
}