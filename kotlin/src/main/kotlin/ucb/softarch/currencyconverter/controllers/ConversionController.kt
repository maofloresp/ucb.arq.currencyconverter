package ucb.softarch.currencyconverter.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import ucb.softarch.currencyconverter.dtos.GetConversionRequestDTO
import ucb.softarch.currencyconverter.bos.ConversionService
import ucb.softarch.currencyconverter.dtos.GetConversionResponseDTO
import ucb.softarch.currencyconverter.utils.HasLogging
import java.lang.Exception

@RestController
class ConversionController : HasLogging()
{
    @Throws(Exception::class)
    @GetMapping("/conversion")
    fun get(params: GetConversionRequestDTO): GetConversionResponseDTO
    {
        logger.info("GET /conversion to=${params.to}&from=${params.from}&amount=${params.amount}")
        val service = ConversionService(params)

        if (service.validate())
        {
            return service.convert()
        }

        throw Exception()
    }
}