namespace currencyconverter.Controllers
{
    using currencyconverter.BOs;
    using currencyconverter.Dtos;
    using Microsoft.AspNetCore.Mvc;

    [Route("/conversion")]
    [ApiController]
    public class ConversionController : ControllerBase
    {
        [HttpGet]
        public GetConversionResponseDTO Get([FromQuery] GetConversionRequestDTO parameters)
        {
            var service = new ConversionService(parameters);

            if (service.Validate())
            {
                return service.Convert();
            }

            throw new Exception();
        }
    }
}
