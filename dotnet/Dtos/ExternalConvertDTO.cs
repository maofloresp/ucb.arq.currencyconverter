namespace currencyconverter.Dtos
{
    using currencyconverter.Dtos.External;

    public record class ExternalConvertDTO (ExternalMeta Meta, ExternalResponse Response);
}
