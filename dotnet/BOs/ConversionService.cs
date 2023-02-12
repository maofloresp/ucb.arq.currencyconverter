namespace currencyconverter.BOs
{
    using currencyconverter.Dtos;

    public class ConversionService
    {
        private readonly string _currencyFrom;
        private readonly string _currencyTo;
        private readonly decimal _cashAmount;
        private readonly string _externalServiceURL;
        private readonly string _apiKey;

        public ConversionService(GetConversionRequestDTO dto)
        {
            _cashAmount = dto.Amount;
            _currencyFrom = dto.From;
            _currencyTo = dto.To;
            _externalServiceURL = "https://api.currencybeacon.com/v1/convert";
            _apiKey = "";
        }

        public bool Validate()
        {
            if (_cashAmount <= 0)
            {
                return false;
            }

            return true;
        }

        public GetConversionResponseDTO Convert()
        {
            return new GetConversionResponseDTO(BuildURL(), _cashAmount);
        }

        private string BuildURL()
        {
            return $"{_externalServiceURL}?api_key={_apiKey}&to={_currencyTo}&from={_currencyFrom}&amount={_cashAmount}";
        }
    }
}
