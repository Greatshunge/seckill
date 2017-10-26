package org.seckill.exception;

/**
 * Created by shunge on 2017/5/10.
 */
public class CommonServiceException  extends Exception {

    public CommonServiceException() {
    }

    public CommonServiceException(String message) {
        super(message);
    }

    public CommonServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommonServiceException(Throwable cause) {
        super(cause);
    }
}
