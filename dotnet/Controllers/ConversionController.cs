namespace currencyconverter.Controllers
{
    using currencyconverter.BOs;
    using currencyconverter.Dtos;
    using currencyconverter.Exceptions;
    using Microsoft.AspNetCore.Mvc;
    using System;
    using System.Net;

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
        public IActionResult Get([FromQuery] GetConversionRequestDTO parameters)
        {
            _logger.LogInformation("GET /conversion To={To}&from={From}&amount={Amount}", parameters.To, parameters.From, parameters.Amount);

            try
            {
                var service = new ConversionService(parameters, _logger);

                if (service.Validate())
                {
                    return Ok(service.Convert());
                }

                throw new BadRequestException();
                   
            }
            catch (HttpException ex)
            {
                return StatusCode((int)ex.StatusCode, ex.Message);
            }
        }
    }
}
