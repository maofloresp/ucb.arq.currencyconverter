namespace currencyconverter.BOs
{
    using currencyconverter.Dtos;
    using currencyconverter.Exceptions;
    using RestSharp;
    using System.Net;

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

            ExternalConvertDTO? response;

            try
            {
                response = client.Get<ExternalConvertDTO>(new RestRequest());
            }
            catch
            {
                _logger.LogError("External service is unavailable");
                throw new ServiceUnavailableException("Service is temporarily unavailable, please try later");
            }

            if (response == null)
            {
                _logger.LogError("Can't process the external service response");
                throw new InternalServerException("We can't process the request at this time");
            }

            if (response.Response.Value == 0)
            {
                _logger.LogWarning("Given currencies do not exist");
                throw new NotFoundException("Target or source currency doesn't exist");
            }

            _logger.LogInformation("External request has completed");
            return response;
        }
    }
}
