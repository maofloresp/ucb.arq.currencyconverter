namespace currencyconverter.Exceptions
{
    using System.Net;

    public class NotFoundException : HttpException
    {
        public NotFoundException(string message) : base(message, HttpStatusCode.NotFound)
        {

        }
    }
}
