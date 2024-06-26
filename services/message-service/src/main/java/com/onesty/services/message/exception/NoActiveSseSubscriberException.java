package com.onesty.services.message.exception;

public class NoActiveSseSubscriberException extends Exception{

    public NoActiveSseSubscriberException(String exceptionMessage){
        super(exceptionMessage);
    }
}
