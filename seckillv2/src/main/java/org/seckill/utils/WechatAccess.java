package org.seckill.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.seckill.entity.AccessToken;
import org.seckill.entity.TicketJSApi;
import org.seckill.entity.WechatConfig;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WechatAccess {

    /**
     * 获取微信accessToken
     * @return
     */
    public static AccessToken getAccessToken(){

        AccessToken accessToken = new AccessToken();

        String url = WechatConfig.GET_TOKEN_URL+"?" + "grant_type=client_credential" +
                "&appid="+ WechatConfig.APP_ID+"&secret="+WechatConfig.SECRET;


        try {
            URL getUrl = new URL(url);
            HttpURLConnection http = (HttpURLConnection) getUrl.openConnection();
            http.setRequestMethod("GET");
            http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);

            http.connect();
            InputStream in = http.getInputStream();
            int size = in.available();
            byte[] b = new byte[size];
            in.read(b);

            String message = new String(b, "UTF-8");

            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject = jsonParser.parse(message).getAsJsonObject();

            accessToken.setAccessToken(jsonObject.get("access_token").getAsString());
            accessToken.setExiresIn(jsonObject.get("expires_in").getAsInt());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return accessToken;
    }

    /**
     * 获取jsapi的ticket
     * @param accessToken
     */
    public static TicketJSApi getTicket(AccessToken accessToken){
        TicketJSApi ticket = new TicketJSApi();

        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+accessToken.getAccessToken()+"&type=jsapi";

        try {
            URL getUrl = new URL(url);
            HttpURLConnection http = (HttpURLConnection) getUrl.openConnection();
            http.setRequestMethod("GET");
            http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

            http.setDoInput(true);
            http.setDoOutput(true);

            http.connect();
            InputStream in = http.getInputStream();

            int size = in.available();
            byte[] b = new byte[size];

            in.read(b);

            String message = new String(b,"UTF-8");

            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject = jsonParser.parse(message).getAsJsonObject();

            ticket.setTicket(jsonObject.get("ticket").getAsString());
            ticket.setExpires_in(jsonObject.get("expires_in").getAsInt());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return ticket;
    }

    public static void main(String arge[]){
        System.out.println("=========获取token=========");
        AccessToken accessToken = new AccessToken();
        accessToken = getAccessToken();
        if (accessToken != null){
            System.out.println(accessToken);
        }
        TicketJSApi ticketJSApi = getTicket(accessToken);

        if (ticketJSApi != null){
            System.out.println(ticketJSApi);
        }
    }
}
