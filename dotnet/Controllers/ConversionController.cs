namespace currencyconverter.Controllers
{
    using currencyconverter.BOs;
    using currencyconverter.Dtos;
    using Microsoft.AspNetCore.Mvc;

    [Route("/conversion")]
    [ApiController]
    public class ConversionController : ControllerBase
    {
        private readonly ILogger _logger;

        public ConversionController(ILogger<ConversionController> logger)
        {
            _logger = logger;
        }

        [HttpGet]
        public GetConversionResponseDTO Get([FromQuery] GetConversionRequestDTO parameters)
        {
            _logger.LogInformation("GET /conversion To={To}&from={From}&amount={Amount}", parameters.To, parameters.From, parameters.Amount);
            var service = new ConversionService(parameters, _logger);

            if (service.Validate())
            {
                return service.Convert();
            }

            throw new Exception();
        }
    }
}
