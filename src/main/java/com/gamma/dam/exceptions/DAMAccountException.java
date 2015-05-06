package com.gamma.dam.exceptions;

/**
 * Created by Abhijit on 5/6/2015.
 */
public class DAMAccountException extends RuntimeException {

    public DAMAccountException(){
        super();
    }

    public DAMAccountException(String message){
        super(message);
    }

    public DAMAccountException(final Throwable cause){
        super(cause);
    }

    public DAMAccountException(String message, final Throwable cause){
        super(message,cause);
    }
}
