package Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求器
 *
 * @author eisuto
 */
public class RainRequest {
    private String url;


    public RainRequest(String url) {
        this.url = url;
    }


    /**
     * 构造 带参Get 的url
     *
     * @param getParameters 要构造的参数
     * @return url串
     */
    private String creatGetUrl(HashMap<String, String> getParameters) {
        StringBuilder buffer = new StringBuilder(url);
        if (getParameters != null) {
            buffer.append("?");
            for (Map.Entry<String, String> entry : getParameters.entrySet()) {
                buffer.append(entry.getKey());
                buffer.append("=");
                buffer.append(entry.getValue());
                buffer.append("&");
            }
            return buffer.substring(0, buffer.length() - 2);
        }

        return buffer.toString();
    }


    /**
     * 快速Get
     *
     * @return Response对象
     */
    public Response quickGet() {
        StringBuilder buffer = new StringBuilder();
        InputStreamReader reader;
        InputStream urlStream;
        String line;
        try {
            URL url = new URL(this.url);
            urlStream = url.openStream();
            reader = new InputStreamReader(urlStream);
            BufferedReader buff = new BufferedReader(reader);
            while ((line = buff.readLine()) != null) {
                buffer.append(line);
            }
            urlStream.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Response(buffer.toString());
    }


    /**
     * Get请求器
     */
    private StringBuilder getRequest(String requestUrl) {
        StringBuilder buffer = new StringBuilder();
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
            System.out.println(urlCon.getResponseCode());
            if (200 == urlCon.getResponseCode()) {
                InputStream is = urlCon.getInputStream();
                InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
                String str;
                while ((str = br.readLine()) != null) {
                    buffer.append(str);
                }
                br.close();
                isr.close();
                is.close();

            } else {
                throw new Exception("连接失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffer;
    }


    /**
     * 带参Get请求
     *
     * @return Response对象
     */
    public Response get(HashMap<String, String> getParameters) {
        String requestUrl = creatGetUrl(getParameters);
        return new Response(getRequest(requestUrl).toString());
    }


    /**
     * 不带参Get请求
     *
     * @return Response对象
     */
    public Response get() {
        return new Response(getRequest(this.url).toString());
    }


    /**
     * 构造post体
     *
     * @param postBody postMap
     * @return post体
     */
    public String creatpostBody(HashMap<String, String> postBody) {
        StringBuilder buffer = new StringBuilder();
        try {
            if (postBody != null) {
                for (Map.Entry<String, String> entry : postBody.entrySet()) {
                    buffer.append("&");
                    buffer.append(entry.getKey());
                    buffer.append("=");
                    buffer.append(entry.getValue());
                }
                return buffer.substring(1);
            } else {
                throw new Exception("请求异常");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }


    /**
     * Post 请求
     *
     * @param postBody 请求参数
     * @return 反序列化后的Json
     */
    public String postRequest(HashMap<String, String> postBody) {
        URL url;
        try {
            url = new URL(this.url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            //连接超时ms
            httpURLConnection.setConnectTimeout(10000);
            //读取超时ms
            httpURLConnection.setReadTimeout(2000);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            // 获取URLConnection对象对应的输出流
            PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
            //添加body
            printWriter.write(creatpostBody(postBody));
            // flush输出流的缓冲
            printWriter.flush();
            //获取数据
            BufferedInputStream bis = new BufferedInputStream(httpURLConnection.getInputStream());
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int len;
            byte[] arr = new byte[1024];
            while ((len = bis.read(arr)) != -1) {
                bos.write(arr, 0, len);
                bos.flush();
            }
            bos.close();
            return bos.toString("utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
