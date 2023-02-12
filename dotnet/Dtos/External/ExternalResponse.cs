namespace currencyconverter.Dtos.External
{
    public record class ExternalResponse
    (
        int Timestamp,
        string Date,
        string From,
        string To,
        decimal Amount,
        decimal Value
    );
}
