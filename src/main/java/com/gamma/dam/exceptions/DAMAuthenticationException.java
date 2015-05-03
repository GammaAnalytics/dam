package com.gamma.dam.exceptions;

/**
 * Created by Abhijit on 5/3/2015.
 */
public class DAMAuthenticationException extends RuntimeException{


    public DAMAuthenticationException(){
        super();
    }

    public DAMAuthenticationException(String message){
        super(message);
    }

    public DAMAuthenticationException(final Throwable cause){
        super(cause);
    }

    public DAMAuthenticationException(String message, final Throwable cause){
        super(message,cause);
    }

}
