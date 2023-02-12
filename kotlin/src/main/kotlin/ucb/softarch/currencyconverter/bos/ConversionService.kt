package ucb.softarch.currencyconverter.bos

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import okhttp3.OkHttpClient
import okhttp3.Request
import ucb.softarch.currencyconverter.dtos.ExternalConvertDTO
import ucb.softarch.currencyconverter.dtos.GetConversionRequestDTO
import ucb.softarch.currencyconverter.dtos.GetConversionResponseDTO
import java.io.IOException
import java.math.BigDecimal

class ConversionService constructor(dto: GetConversionRequestDTO)
{
    private val currencyFrom = dto.from
    private val currencyTo = dto.to
    private val cashAmount = dto.amount
    private val externalServiceURL = "https://api.currencybeacon.com/v1/convert"
    private val apiKey = ""

    fun validate(): Boolean
    {
        if(cashAmount <= BigDecimal.ZERO)
        {
            return false;
        }

        return true;
    }

    fun convert() : GetConversionResponseDTO
    {
        val externalResponse = getExternalConversion()

        return GetConversionResponseDTO(externalResponse.response.to, externalResponse.response.value)
    }

    private fun buildURL(): String
    {
        return "${externalServiceURL}?api_key=${apiKey}&to=${currencyTo}&from=${currencyFrom}&amount=${cashAmount}"
    }

    private fun getExternalConversion() : ExternalConvertDTO
    {
        val client = OkHttpClient()

        val request = Request.Builder().url(buildURL()).build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful)
            {
                throw IOException("Unexpected code $response")
            }

            return jacksonObjectMapper().readValue<ExternalConvertDTO>(response.body().string())
        }

    }
}