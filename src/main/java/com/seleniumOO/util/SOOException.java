package com.seleniumOO.util;

/**
 * This is the plugin object exception.
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
