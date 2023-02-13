namespace currencyconverter.Exceptions
{
    public class InternalServerException : HttpException
    {
        public InternalServerException(string message) : base(message)
        {

        }
    }
}
