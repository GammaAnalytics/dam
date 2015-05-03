package com.gamma.dam.exceptions;

/**
 * Created by Abhijit on 5/3/2015.
 */
public class DAMAuthorizationException extends RuntimeException{


    public DAMAuthorizationException(){
        super();
    }

    public DAMAuthorizationException(String message){
        super(message);
    }

    public DAMAuthorizationException(final Throwable cause){
        super(cause);
    }

    public DAMAuthorizationException(String message, final Throwable cause){
        super(message,cause);
    }

}

