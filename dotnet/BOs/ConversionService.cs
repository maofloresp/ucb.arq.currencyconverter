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

        private ILogger _logger;

        public ConversionService(GetConversionRequestDTO dto, ILogger logger)
        {
            _cashAmount = dto.Amount;
            _currencyFrom = dto.From;
            _currencyTo = dto.To;
            _externalServiceURL = "https://api.currencybeacon.com/v1/convert";
            _apiKey = "";

            _logger = logger;
        }

        public bool Validate()
        {
            if (_cashAmount <= 0)
            {
                _logger.LogError("Validation has failed");
                return false;
            }

            _logger.LogInformation("Successfull validation");
            return true;
        }

        public GetConversionResponseDTO Convert()
        {
            _logger.LogInformation("Attempt to connect to external service");
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


            _logger.LogInformation("External request has completed");
            return response;
        }
    }
}
