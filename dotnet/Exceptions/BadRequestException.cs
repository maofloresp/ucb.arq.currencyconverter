namespace currencyconverter.Exceptions
{
    using System.Net;

    public class BadRequestException : HttpException
    {
        public BadRequestException() : base("Your request has invalid properties", HttpStatusCode.BadRequest) 
        {

        }
    }
}
