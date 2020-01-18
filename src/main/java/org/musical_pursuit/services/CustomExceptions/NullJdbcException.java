package org.musical_pursuit.services.CustomExceptions;

public class NullJdbcException extends Exception {

    public NullJdbcException(String s) {
        super(s);
    }
}
