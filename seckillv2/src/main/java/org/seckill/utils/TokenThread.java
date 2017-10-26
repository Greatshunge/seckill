package org.seckill.utils;

import org.seckill.entity.AccessToken;
import org.seckill.entity.TicketJSApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TokenThread implements Runnable{

    private final Logger logger = LoggerFactory.getLogger(TokenThread.class);

    public static AccessToken accessToken = null;
    public static TicketJSApi ticketJSApi = null;

    @Override
    public void run() {
        try {
            while (true){

                accessToken = WechatAccess.getAccessToken();
                if (null != accessToken){
                    logger.info("accessToken获取成功："+accessToken.getAccessToken()+" ,有效时间："+accessToken.getExiresIn());

                    ticketJSApi = WechatAccess.getTicket(accessToken);
                    while (null == ticketJSApi){
                        Thread.sleep(60*1000);
                        ticketJSApi = WechatAccess.getTicket(accessToken);
                        if ( null != ticketJSApi){
                            logger.info("accessToken获取成功："+ticketJSApi.getTicket()+" ,有效时间："+ticketJSApi.getExpires_in());
                        }
                    }
                    //7000秒之后重新进行获取
                    Thread.sleep((accessToken.getExiresIn()-200)*1000);
                }else {
                    Thread.sleep(60*1000);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
