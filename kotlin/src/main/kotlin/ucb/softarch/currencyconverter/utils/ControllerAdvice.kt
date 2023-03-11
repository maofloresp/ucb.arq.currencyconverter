package ucb.softarch.currencyconverter.utils

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import ucb.softarch.currencyconverter.exceptions.ServiceException
import java.net.SocketTimeoutException
import java.time.LocalDateTime

class ControllerAdvice : ResponseEntityExceptionHandler()
{
    @ExceptionHandler(ServiceException::class)
    fun handleServiceException(ex: ServiceException): ResponseEntity<Any?>?
    {
        val body: MutableMap<String, Any?> = LinkedHashMap()
        body["timestamp"] = LocalDateTime.now()
        body["message"] = ex.message
        return ResponseEntity(body, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(SocketTimeoutException::class)
    fun handleSocketTimeoutException(): ResponseEntity<Any?>? {
        val body: MutableMap<String, Any?> = LinkedHashMap()
        body["timestamp"] = LocalDateTime.now()
        body["message"] = "Service is not available at the moment"
        return ResponseEntity(body, HttpStatus.SERVICE_UNAVAILABLE)
    }
}