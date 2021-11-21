package com.bookbuffs.bookbuffs.excepetions;

public class SpringBookbuffsException extends RuntimeException {
    public  SpringBookbuffsException (String exMessage, Exception exception) {
        super(exMessage,exception);
    }

    public SpringBookbuffsException(String exMessage) {
        super(exMessage);
    }
}
