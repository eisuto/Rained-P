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
    private HashMap<String, String> getParameters;
    private HashMap<String, String> postBody;

    public RainRequest(String url) {
        this.url = url;
    }

    /**
     * 构造get请求url
     *
     * @return url串
     */
    private String creatGetUrl() {
        StringBuilder buffer = new StringBuilder(url);
        if(getParameters!=null){
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
     * Get请求
     *
     * @return Page对象
     */
    public Page get() {
        String requestUrl = creatGetUrl();
        StringBuilder buffer = new StringBuilder();
        InputStreamReader reader;
        InputStream urlStream;
        String line;
        try {
            URL url = new URL(requestUrl);
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

    public Page getX() {

        String requestUrl = creatGetUrl();
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
        return new Page(buffer.toString());
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
