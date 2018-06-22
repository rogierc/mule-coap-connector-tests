package nl.teslanet.mule.transport.coap.test;

public class CatchException extends Exception
{

    public CatchException()
    {
        super();
    }

    public CatchException( String message )
    {
        super( message );
    }

    public CatchException( Throwable cause )
    {
        super( cause );
    }

    public CatchException( String message, Throwable cause )
    {
        super( message, cause );
    }

    public CatchException( String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace )
    {
        super( message, cause, enableSuppression, writableStackTrace );
    }

}
