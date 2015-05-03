package com.gamma.dam.exceptions;

/**
 * Created by Abhijit on 5/3/2015.
 */
public class DAMUnknownAccountException  extends RuntimeException{


    public DAMUnknownAccountException(){
        super();
    }

    public DAMUnknownAccountException(String message){
        super(message);
    }

    public DAMUnknownAccountException(final Throwable cause){
        super(cause);
    }

    public DAMUnknownAccountException(String message, final Throwable cause){
        super(message,cause);
    }

}
