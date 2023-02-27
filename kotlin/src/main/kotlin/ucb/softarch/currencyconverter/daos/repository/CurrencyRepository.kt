package ucb.softarch.currencyconverter.daos.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import ucb.softarch.currencyconverter.daos.Currency

interface CurrencyRepository : CrudRepository<Currency, Long>
{
}