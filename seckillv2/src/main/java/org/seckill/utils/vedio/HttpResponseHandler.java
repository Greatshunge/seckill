package org.seckill.utils.vedio;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by Jason on 2015/12/28.
 */
public class HttpResponseHandler implements ResponseHandler<JsonNode> {

    private final static Logger logger = LoggerFactory.getLogger(HttpResponseHandler.class);

    @Override
    public JsonNode handleResponse(HttpResponse response) throws IOException {
        //请求响应码
        StatusLine statusLine = response.getStatusLine();
        /**
         * 响应实体
         */
        HttpEntity entity = response.getEntity();

        if (statusLine.getStatusCode() >= 300) {
            throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
        }

        if (entity == null) {
            logger.error("Http response content is empty!");
            throw new ClientProtocolException("Response contains no content");
        }

        //将响应实体转换成字符串
        String result = EntityUtils.toString(entity, "UTF-8");

        //如果是个回调，则格式化
        String callbackPrefix = "callback(";
        String callbackSuffix = ");";

        if (result.contains(callbackPrefix)) {
            result = result.substring(result.indexOf(callbackPrefix) + callbackPrefix.length());
        }

        if (result.endsWith(callbackSuffix)) {
            result = result.substring(0, result.lastIndexOf(callbackSuffix));
        }

        result = result.trim();

        logger.debug(result);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(result);
    }
}
