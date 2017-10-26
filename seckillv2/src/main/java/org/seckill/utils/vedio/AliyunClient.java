package org.seckill.utils.vedio;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.vod.model.v20170321.CreateUploadVideoRequest;
import com.aliyuncs.vod.model.v20170321.CreateUploadVideoResponse;
import com.aliyuncs.vod.model.v20170321.RefreshUploadVideoRequest;
import com.aliyuncs.vod.model.v20170321.RefreshUploadVideoResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.codehaus.jackson.JsonNode;
import org.seckill.entity.Video;
import org.seckill.utils.JsonUtils;
import org.seckill.utils.vedio.aliyun.Signature;
import org.seckill.utils.vedio.aliyun.The;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class AliyunClient {

    public static final Logger logger = LoggerFactory.getLogger(AliyunClient.class);

    public static final String regionId = "cn-shanghai";
    public static String accessKeyId = "LTAIQchz05Cr8GH3";
    public static String accessKeySecret = "VOUebt9vBZNNFqzrQMpeFYlSTPtQqK";

    DefaultAcsClient aliyunClient;

    public AliyunClient(){
        aliyunClient = new DefaultAcsClient(
                DefaultProfile.getProfile(regionId,accessKeyId,accessKeySecret));
    }

    public DefaultAcsClient getAliyunClient() {
        return aliyunClient;
    }

    private static String createUploadVideo(DefaultAcsClient client) {
        CreateUploadVideoRequest request = new CreateUploadVideoRequest();
        CreateUploadVideoResponse response = null;
        try {
              /*必选，视频源文件名称（必须带后缀, 支持 ".3gp", ".asf", ".avi", ".dat", ".dv", ".flv", ".f4v", ".gif", ".m2t", ".m3u8", ".m4v", ".mj2", ".mjpeg", ".mkv", ".mov", ".mp4", ".mpe", ".mpg", ".mpeg", ".mts", ".ogg", ".qt", ".rm", ".rmvb", ".swf", ".ts", ".vob", ".wmv", ".webm"".aac", ".ac3", ".acm", ".amr", ".ape", ".caf", ".flac", ".m4a", ".mp3", ".ra", ".wav", ".wma"）*/
            request.setFileName("源文件名称.mp4");
            //必选，视频标题
            request.setTitle("视频标题");
            //可选，分类ID
            request.setCateId(0);
            //可选，视频标签，多个用逗号分隔
            request.setTags("标签1,标签2");
            //可选，视频描述
            request.setDescription("视频描述");
            //可选，视频源文件字节数
            request.setFileSize(0l);
            response = client.getAcsResponse(request);
        } catch (ServerException e) {
            System.out.println("CreateUploadVideoRequest Server Exception:");
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("CreateUploadVideoRequest Client Exception:");
            e.printStackTrace();
        }
        System.out.println("RequestId:"+response.getRequestId());
        System.out.println("UploadAuth:"+response.getUploadAuth());
        System.out.println("UploadAddress:"+response.getUploadAddress());
        return response.getVideoId();
    }

    private static void refreshUploadVideo(DefaultAcsClient client, String videoId) {
        RefreshUploadVideoRequest request = new RefreshUploadVideoRequest();
        RefreshUploadVideoResponse response = null;
        try {
            request.setVideoId(videoId);
            response = client.getAcsResponse(request);
        } catch (ServerException e) {
            System.out.println("RefreshUploadVideoRequest Server Exception:");
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("RefreshUploadVideoRequest Client Exception:");
            e.printStackTrace();
        }
        System.out.println("RequestId:" + response.getRequestId());
        System.out.println("UploadAuth:" + response.getUploadAuth());
    }

    public Video fetchVideoInfo(String videoId) throws Exception {
        CloseableHttpClient httpClient = HttpClientFacade.fetchHttpClient();
        HttpClientContext clientContext = HttpClientContext.create();

        final String endPoint = "http://vod.cn-shanghai.aliyuncs.com/";
        final String action = "GetPlayInfo";
        final String formats = "mp4";
        final String API_URL = The.API.build(endPoint, action, accessKeyId, accessKeySecret) + "&VideoId=" + videoId + "&Formats=" + formats;

        String url = Signature.newBuilder()
                .method(The.HTTP.GET.method())
                .url(API_URL)
                .secret(The.API.secret())
                .build()
                .compose();

        HttpGet request = new HttpGet(url);
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(10000).setConnectionRequestTimeout(10000).build();
        request.setConfig(requestConfig);

        try {
            JsonNode jsonNode = httpClient.execute(request, new HttpResponseHandler(), clientContext);
            String result = jsonNode.toString();
            String VideoBase = JsonUtils.getJsonValue(result, "VideoBase");
            String CoverURL = JsonUtils.getJsonValue(VideoBase, "CoverURL");
            String PlayInfoList = JsonUtils.getJsonValue(result, "PlayInfoList");
            String PlayInfo = JsonUtils.getJsonValue(PlayInfoList, "PlayInfo");

            List<Map<String, Object>> list = JsonUtils.getJsonArray(PlayInfo);
            if (list != null && !list.isEmpty()) {
                Map<String, Object> map = list.get(0);
                Video video = mapToVideo(map);
                video.setVideoId(videoId);
                video.setThumbnail(CoverURL);
                return video;
            } else {
                logger.warn("获取视频信息为空！视频信息: " + PlayInfo);
                return null;
            }
        } catch (Exception e) {
            logger.error("获取视频信息失败！视频videoId: " + videoId, e);
            throw new Exception();
        }
    }

    private Video mapToVideo(Map<String, Object> map) {
        Video video = new Video();
        String Duration = String.valueOf(map.get("Duration"));
        int intDuration = (int) Math.round(Double.valueOf(Duration));
        String Height = String.valueOf(map.get("Height"));
        int intHeight = Integer.valueOf(Height);
        String Width = String.valueOf(map.get("Width"));
        int intWidth = Integer.valueOf(Width);
        String Size = String.valueOf(map.get("Size"));
        int intSize = Integer.valueOf(Size);
        String PlayURL = String.valueOf(map.get("PlayURL"));

        video.setDuration(intDuration);
        video.setHeight(generateRandomHeight(intHeight));
        video.setWidth(intWidth);
        video.setSize(intSize);
        video.setPlayURL(PlayURL);
        return video;
    }

    private int generateRandomHeight(int height) {
        int Max = 110, Min = 90;
        float random = (float) (Math.round(Math.random() * (Max - Min) + Min)) / 100;
        return (int) Math.floor(height * random);
    }

    public static void main(String[] args) throws Exception {
        AliyunClient aliyunClient = new AliyunClient();
//        String videoId = createUploadVideo(aliyunClient.getAliyunClient());
//        System.out.println("VideoId:" + videoId);
//        //当网络异常导致文件上传失败时,可刷新上传凭证后再次执行上传操作
//        refreshUploadVideo(aliyunClient.getAliyunClient(), videoId);


        System.out.println("-----------------------------------");
        Video video = aliyunClient.fetchVideoInfo("5f26414692764240bfd7a7dc0c88c7a0");
        System.out.println(video);
    }

}
