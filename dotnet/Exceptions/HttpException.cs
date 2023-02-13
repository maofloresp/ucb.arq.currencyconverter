namespace currencyconverter.Exceptions
{
    using Microsoft.AspNetCore.Mvc;
    using System.Net;

    public abstract class HttpException : Exception
    {
        public HttpStatusCode StatusCode { get; }
        
        public HttpException(string message, HttpStatusCode statusCode = HttpStatusCode.InternalServerError) : base (message)
        {
            StatusCode = statusCode;
        }
    }
}
