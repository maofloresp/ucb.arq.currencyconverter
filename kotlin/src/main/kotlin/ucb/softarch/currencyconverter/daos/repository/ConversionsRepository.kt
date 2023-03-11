package ucb.softarch.currencyconverter.daos.repository

import org.springframework.data.repository.PagingAndSortingRepository
import ucb.softarch.currencyconverter.daos.Conversion

interface ConversionsRepository : PagingAndSortingRepository<Conversion, Long>
{
}