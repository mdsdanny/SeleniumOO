package com.seleniumOO.util;

/**
 * This is the SOO library exception object.
 * All exceptions that happens on the library, will thrown a SOOException object.
 *
 * Created by daniela on 14/02/17.
 */
public class SOOException extends Exception{

    public SOOException(String message) {
        super(message);
    }

    public SOOException(Throwable e) {
        super(e);
    }
}
