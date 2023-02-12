namespace currencyconverter.BOs
{
    using currencyconverter.Dtos;
    using RestSharp;

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
            var externalResponse = GetExternalConversion();

            return new GetConversionResponseDTO(externalResponse.Response.To, externalResponse.Response.Value);
        }

        private string BuildURL()
        {
            return $"{_externalServiceURL}?api_key={_apiKey}&to={_currencyTo}&from={_currencyFrom}&amount={_cashAmount}";
        }

        private ExternalConvertDTO GetExternalConversion()
        {
            var client = new RestClient(BuildURL());

            var response = client.Get<ExternalConvertDTO>(new RestRequest());

            return response;
        }
    }
}
