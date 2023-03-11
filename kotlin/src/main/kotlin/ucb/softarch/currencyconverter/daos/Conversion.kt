package ucb.softarch.currencyconverter.daos

import java.math.BigDecimal
import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "currency")
data class Conversion
(
        var currencyFrom : String = "",
        var currencyTo : String = "",
        var amount : BigDecimal = BigDecimal.ZERO,
        var result: BigDecimal = BigDecimal.ZERO,
        var date: Date = Date(),
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id : Long = 0
)