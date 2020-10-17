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

    private HashMap<String, String> postBody;

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
     * @return Page对象
     */
    public Page quickGet() {
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
        return new Page(buffer.toString());
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
     * @return Page对象
     */
    public Page get(HashMap<String, String> getParameters) {
        String requestUrl = creatGetUrl(getParameters);
        return new Page(getRequest(requestUrl).toString());
    }


    /**
     * 不带参Get请求
     *
     * @return Page对象
     */
    public Page get() {
        return new Page(getRequest(this.url).toString());
    }


    /**
     * Post 请求
     *
     * @param parameters 请求参数
     * @return 反序列化后的Json
     */
    public HashMap<String, String> post(HashMap<String, String> parameters) {
        return null;


    }
}
