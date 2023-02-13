namespace currencyconverter.Exceptions
{
    using System.Net;
    public class ServiceUnavailableException : HttpException
    {
        public ServiceUnavailableException(string message) : base(message, HttpStatusCode.ServiceUnavailable)
        {

        }
    }
}
