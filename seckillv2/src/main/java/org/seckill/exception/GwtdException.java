package org.seckill.exception;

/**
 * Created by shunge on 2017/5/24.
 */
public class GwtdException extends Exception {

    public GwtdException() {
        super();
    }

    public GwtdException(String message) {
        super(message);
    }

    public GwtdException(String message, Throwable cause) {
        super(message, cause);
    }

    public GwtdException(Throwable cause) {
        super(cause);
    }
}
