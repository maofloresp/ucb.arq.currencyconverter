package ucb.softarch.currencyconverter.services

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import ucb.softarch.currencyconverter.dtos.ExternalConvertDTO
import ucb.softarch.currencyconverter.dtos.GetConversionResponseDTO
import ucb.softarch.currencyconverter.exceptions.ServiceException
import ucb.softarch.currencyconverter.utils.HasLogging
import java.lang.RuntimeException
import java.math.BigDecimal

@Service
class ConversionService : HasLogging()
{
    @Value("\${api.url}")
    lateinit var apiUrl: String

    @Value("\${api.key}")
    lateinit var apiKey: String

    fun convert(from : String, to : String, amount : BigDecimal) : GetConversionResponseDTO
    {
        if(amount <= BigDecimal.ZERO)
        {
            logger.error("Validation has failed")
            throw IllegalArgumentException("Amount can't be negative")
        }

        val externalResponse = getExternalConversion(buildURL(from, to, amount))

        return GetConversionResponseDTO(externalResponse.response.to, externalResponse.response.value)
    }

    private fun buildURL(from : String, to : String, amount : BigDecimal): String
    {
        return "${apiUrl}?api_key=${apiKey}&to=${to}&from=${from}&amount=${amount}"
    }

    private fun getExternalConversion(serviceURL : String) : ExternalConvertDTO
    {
        logger.info("Attempt to connect to external service")
        val client = OkHttpClient()

        val request = Request.Builder().url(serviceURL).build()

        try
        {
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful)
                {
                    logger.error("External request has failed")
                    throw ServiceException("Error in third party services")
                }

                logger.info("Attempt to parse the response")

                val externalResponse : ExternalConvertDTO

                try
                {
                    externalResponse = jacksonObjectMapper().readValue<ExternalConvertDTO>(response.body().string())
                }
                catch (ex : Exception)
                {
                    logger.error("External request has returned an unexpected response")
                    throw ServiceException("Did not processed the response correctly")
                }


                if (externalResponse.response.value == BigDecimal.ZERO)
                {
                    logger.warn("Given currencies do not exist")
                    throw ServiceException("Target or source currency doesn't exist")
                }

                logger.info("External request has completed")
                return externalResponse
            }
        }
        catch (ex : Exception)
        {
            ex.printStackTrace()
            throw RuntimeException("Service error")
        }
    }
}