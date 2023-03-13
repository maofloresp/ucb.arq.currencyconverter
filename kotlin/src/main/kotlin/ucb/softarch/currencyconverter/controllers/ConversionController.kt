package ucb.softarch.currencyconverter.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import ucb.softarch.currencyconverter.daos.Conversion
import ucb.softarch.currencyconverter.dtos.GetConversionRequestDTO
import ucb.softarch.currencyconverter.services.ConversionService
import ucb.softarch.currencyconverter.utils.HasLogging
import java.util.*

@CrossOrigin(origins = ["http://localhost:4200"])
@RestController
class ConversionController @Autowired constructor(private val service: ConversionService) : HasLogging() {

    @PostMapping("/conversions")
    fun post(params: GetConversionRequestDTO): Conversion
    {
        logger.info("POST /conversions to=${params.to}&from=${params.from}&amount=${params.amount}")
        return service.convert(params.from, params.to, params.amount)
    }

    @GetMapping("/conversions")
    fun get(@RequestParam(defaultValue = "1") page: Int) : List<Conversion>
    {
        logger.info("GET /conversion page=${page}")
        return service.getConversions(page)
    }
}