/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.Http.http5   
 * @author: Frankjiu
 * @date: 2020年8月19日
 * @version: V1.0
 */

package com.modules.http.okhttp3;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.http.util.TextUtils;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @Description: OkHttp3工具类
 * @author: Frankjiu
 * @date: 2020年8月19日
 */
@Slf4j
public class OkHttp3Util {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static final int CONNECT_TIMEOUT = 10; // 连接超时时间
    private static final int READ_TIMEOUT = 15; // 读取超时时间
    private static final int WRITE_TIMEOUT = 10; // 写入超时时间

    /**
     * 配置OkHttpClient
     */
    static {
        log.info("+++++++++++++++OkHttpClient初始化配置++++++++++++++");
        okHttpClient = okHttpClient.newBuilder().connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS).writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS).build();
    }

    /**
     * 同步Get请求
     */
    public static String get(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response response = okHttpClient.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    /**
     * 同步get请求
     */
    public static String get(String url, Map<String, String> map) throws Exception {
        url = getRequestUrl(url, map);
        Request request = new Request.Builder().url(url).build();
        Response response = okHttpClient.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    /**
     * 异步Get请求(回调)
     */
    public static void get(String url, final CallBackFunction callBackFunction) {
        Request request = new Request.Builder().get().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callBackFunction.success(call, response);
            }

            @Override
            public void onFailure(Call call, IOException e) {
                callBackFunction.failed(call, e);
            }
        });
    }

    /**
     * 异步Get请求(回调)
     */
    public static void get(String url, Map<String, String> map, final CallBackFunction callBackFunction) {
        url = getRequestUrl(url, map);
        Request request = new Request.Builder().get().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callBackFunction.success(call, response);
            }

            @Override
            public void onFailure(Call call, IOException e) {
                callBackFunction.failed(call, e);
            }
        });
    }

    /**
     * 同步Post json
     */
    public static String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(url).post(body).build();
        Response response = okHttpClient.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    /**
     * 同步Post key-value
     */
    public static String post(String url, Map<String, String> map) throws IOException {
        RequestBody body = setRequestBodyParams(map);
        Request request = new Request.Builder().post(body).url(url).build();
        Response response = okHttpClient.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    /**
     * 异步Post json
     */
    public void post(String url, String json, final CallBackFunction callBackFunction) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        buildRequest(url, callBackFunction, body);
    }

    /**
     * 异步Post key-value
     */
    public void post(String url, Map<String, String> map, final CallBackFunction callBackFunction) {
        RequestBody body = setRequestBodyParams(map);
        buildRequest(url, callBackFunction, body);
    }

    /**
     * 请求体参数封装
     */
    private static RequestBody setRequestBodyParams(Map<String, String> map) {
        FormBody.Builder bodyBuilder = new FormBody.Builder();
        if (map != null) {
            Iterator<String> iterator = map.keySet().iterator();
            String key = "";
            while (iterator.hasNext()) {
                key = iterator.next().toString();
                bodyBuilder.add(key, map.get(key));
            }
        }
        return bodyBuilder.build();
    }

    /**
     * get方式URL拼接
     */
    private static String getRequestUrl(String url, Map<String, String> map) {
        if (StringUtils.isEmpty(map) || map.isEmpty()) {
            return url;
        }

        StringBuilder stbUrl = new StringBuilder(url);
        if (url.indexOf("?") == -1) {
            stbUrl.append("?rd=" + Math.random());
        }

        for (Map.Entry<String, String> item : map.entrySet()) {
            if (false == TextUtils.isEmpty(item.getKey().trim())) {
                try {
                    stbUrl.append(
                            "&" + item.getKey().trim() + "=" + URLEncoder.encode(String.valueOf(item.getValue().trim()), "UTF-8"));
                } catch (Exception e) {
                    throw new IllegalArgumentException("参数拼接异常!", e);
                }
            }
        }
        return stbUrl.toString();
    }

    /**
     * 构造 Request 发起异步请求
     */
    private static void buildRequest(String url, CallBackFunction callBackFunction, RequestBody body) {
        Request request = new Request.Builder().post(body).url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callBackFunction.success(call, response);
            }

            @Override
            public void onFailure(Call call, IOException e) {
                callBackFunction.failed(call, e);
            }
        });
    }

    /**
     * 回调函数接口
     */
    public interface CallBackFunction {
        /**
         * 请求成功
         */
        void success(Call call, Response response) throws IOException;

        /**
         * 请求失败
         */
        void failed(Call call, IOException e);
    }

    /**
     * 测试
     */
    public static void main(String[] args) {

        @Data
        class ParamVo {
            private String app;
            private String appkey;
            private String sign;
            private String format;
        }

        // URL1 get请求
        String url = "http://api.k780.com:88/?app=life.time&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json";
        Map<String, String> map = new HashMap<String, String>();
        map.put("a", "3");
        String str = null;
        try {
            str = OkHttp3Util.get(url, map);
            System.out.println("get请求>>> " + str);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // URL2 get请求
        //生成token的接口URL
        String id = "11";
        String secret = "6535624";
        String tokenUrl = "https://oapi.dingtalk.com/gettoken?corpid=" + id + "&corpsecret=" + secret;
        try {
            String token = OkHttp3Util.get(tokenUrl);
            System.out.println("get请求>>> token:" + token);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // URL3 post请求
        ObjectMapper mapper = new ObjectMapper();
        ParamVo paramVo = new ParamVo();
        paramVo.setApp("life.time");
        paramVo.setAppkey("10003");
        paramVo.setSign("b59bc3ef6191eb9f747dd4e83c99f2a4");
        paramVo.setFormat("json");
        try {
            String jsonParam = mapper.writeValueAsString(paramVo);
            String postStr = OkHttp3Util.post("http://api.k780.com:88/", jsonParam);
            System.out.println("post请求>>>" + postStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // URL4 get请求
        String baiduUrl = "http://www.baidu.com/s?";
        Map<String, String> paramMap = Maps.newHashMap();
        paramMap.put("wd", "秦始皇3");
        paramMap.put("rsv_spt", "1");
        paramMap.put("rsv_iqid", "0xfdb20d8c000144d9");
        paramMap.put("issp", "1");
        paramMap.put("f", "8");
        paramMap.put("rsv_bp", "1");
        paramMap.put("rsv_idx", "2");
        paramMap.put("ie", "utf-8");
        paramMap.put("tn", "baiduhome_pg");
        paramMap.put("rsv_enter", "1");

        paramMap.put("rsv_dl", "tb");
        paramMap.put("rsv_sug3", "2");
        paramMap.put("rsv_sug2", "0");

        paramMap.put("rsv_btype", "i");
        paramMap.put("inputT", "1051");
        paramMap.put("rsv_sug4", "1051");
        try {
            String data = OkHttp3Util.get(baiduUrl, paramMap);
            System.out.println("get请求>>>" + data);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}