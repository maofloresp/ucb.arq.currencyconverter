namespace currencyconverter.Dtos
{
    public record class GetConversionRequestDTO (string From, string To, decimal Amount);
}
