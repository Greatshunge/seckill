package org.seckill.utils.vedio;

import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Created by Jason on 2015/12/28.
 */
public class HttpClientFacade {


    private final static Logger logger = LoggerFactory.getLogger(HttpClientFacade.class);
    /**
     * HttpClient请求连接管理器
     */
    private static PoolingHttpClientConnectionManager clientConnectionManager = null;

    static {
        try {
            /**
             * 简单套接字连接
             */
            ConnectionSocketFactory plainsf = new PlainConnectionSocketFactory();
            /**
             * 安全套接字连接上下文
             */
            SSLContext sslContext = SSLContexts.custom()
                    .useTLS()
                    .loadTrustMaterial(KeyStore.getInstance(KeyStore.getDefaultType()), new TrustStrategy() {

                        @Override
                        public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                            return true;
                        }
                    })
                    .setSecureRandom(new SecureRandom())
                    .build();
            /**
             * 安全套接字连接
             */
            LayeredConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext,
                    SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            /**
             * 套接字连接注册
             */
            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", plainsf)
                    .register("https", sslsf)
                    .build();
            /**
             * 构造HttpClient请求连接池管理器
             */
            clientConnectionManager = new PoolingHttpClientConnectionManager(registry);
            clientConnectionManager.setMaxTotal(50);
            clientConnectionManager.setDefaultConnectionConfig(ConnectionConfig.custom().setCharset(Charset.forName("UTF-8")).build());
            clientConnectionManager.setDefaultSocketConfig(SocketConfig.custom().setSoReuseAddress(true).build());
        } catch (Exception e) {
            logger.error("Create http client manager failed!", e);
        }
    }

    /**
     * 从httpclient连接池里面获取一个httpclient实例
     *
     * @return see {@link CloseableHttpClient}
     */
    public synchronized static CloseableHttpClient fetchHttpClient() {
        logger.debug("Fetching CloseableHttpClient from connection manager.");
        return HttpClients.custom().setConnectionManager(clientConnectionManager).setRetryHandler(new DefaultHttpRequestRetryHandler(3, true)).build();
    }
}
