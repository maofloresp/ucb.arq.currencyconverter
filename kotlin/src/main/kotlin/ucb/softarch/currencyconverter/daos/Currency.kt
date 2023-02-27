package ucb.softarch.currencyconverter.daos

import java.math.BigDecimal
import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "currency")
data class Currency
(
        var currencyFrom : String,
        var currencyTo : String,
        var amount : BigDecimal,
        var result: BigDecimal,
        var date: Date,
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id : Long = 0
)