package org.seckill.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String getJsonValue(String content, String key) {
        String value;
        try {
            JSONObject jsonObject = new JSONObject(content);
            value = jsonObject.getString(key);
        } catch (Exception e) {
            return null;
        }
        return value;
    }

    public static List<Map<String, Object>> getJsonArray(String content) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(content, new TypeReference<List<Map<String, Object>>>() {
            });
        } catch (IOException e) {
            return null;
        }
    }

    public static synchronized ObjectMapper getMapper() {
        return objectMapper;
    }

    public static synchronized String toJson(Object entity) throws JsonProcessingException {
        return objectMapper.writeValueAsString(entity);
    }

    public static synchronized <T> T fromJson(String json, Class<T> clazz) throws IOException {
        return objectMapper.readValue(json, clazz);
    }

    public static void main(String[] args) throws IOException {
        String value = "[\n" +
                "    {\n" +
                "        \"StreamType\": \"video\",\n" +
                "        \"Format\": \"mp4\",\n" +
                "        \"PlayURL\": \"http://play.dlsmartercity.com/6a0e465221c44567863606d6cb4966b1/74c85461b5a6410192593217363824f9-5287d2089db37e62345123a1be272f8b.mp4\",\n" +
                "        \"Size\": 26151551,\n" +
                "        \"Fps\": \"25.000\",\n" +
                "        \"Definition\": \"LD\",\n" +
                "        \"Duration\": \"211.960\",\n" +
                "        \"Height\": 640,\n" +
                "        \"Width\": 960,\n" +
                "        \"Bitrate\": \"884.530\",\n" +
                "        \"Encrypt\": 0\n" +
                "    }\n" +
                "]";

//        JsonNode jsonNode = new ObjectMapper().readTree(value);
//        if (jsonNode instanceof ArrayNode){
//            ArrayNode node= (ArrayNode)jsonNode;
//            if (node.size() <= 0) {
//                return;
//            }
//            for (int i=0;i < node.size();i++){
//                Video video = new ObjectMapper().readValue(node.get(i).toString(), Video.class);
//                System.out.println(video);
//            }
//        }
    }
}
