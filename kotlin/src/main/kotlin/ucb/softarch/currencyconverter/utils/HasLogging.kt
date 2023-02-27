package ucb.softarch.currencyconverter.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory

abstract class HasLogging
{
    companion object {
        @JvmStatic
        protected val logger : Logger
                = LoggerFactory.getLogger(this::class.java)
    }
}