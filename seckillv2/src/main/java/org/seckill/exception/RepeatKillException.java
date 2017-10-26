package org.seckill.exception;

/**
 * 重复秒杀异常
 * Created by shunge on 2017/4/7.
 */
public class RepeatKillException extends SeckillException{

    public RepeatKillException() {
    }

    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepeatKillException(Throwable cause) {
        super(cause);
    }
}
