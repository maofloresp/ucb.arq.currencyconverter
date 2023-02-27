package ucb.softarch.currencyconverter.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import ucb.softarch.currencyconverter.dtos.GetConversionRequestDTO
import ucb.softarch.currencyconverter.services.ConversionService
import ucb.softarch.currencyconverter.dtos.GetConversionResponseDTO
import ucb.softarch.currencyconverter.utils.HasLogging

@RestController
class ConversionController @Autowired constructor(private val service: ConversionService) : HasLogging() {
    @GetMapping("/conversion")
    fun get(params: GetConversionRequestDTO): GetConversionResponseDTO
    {
        logger.info("GET /conversion to=${params.to}&from=${params.from}&amount=${params.amount}")
        return service.convert(params.from, params.to, params.amount)
    }
}