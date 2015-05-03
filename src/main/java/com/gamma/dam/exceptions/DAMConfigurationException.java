package com.gamma.dam.exceptions;

/**
 * Created by Abhijit on 5/3/2015.
 */
public class DAMConfigurationException extends RuntimeException{


    public DAMConfigurationException(){
        super();
    }

    public DAMConfigurationException(String message){
        super(message);
    }

    public DAMConfigurationException(final Throwable cause){
        super(cause);
    }

    public DAMConfigurationException(String message, final Throwable cause){
        super(message,cause);
    }
}
