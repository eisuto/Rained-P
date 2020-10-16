package Service;

import java.io.*;
import java.net.URL;
import java.util.HashMap;

/**
 * 请求器
 * @author eisuto
 */
public class RainRequest {
    private String url;

    public RainRequest(String url) {
        this.url = url;
    }

    /**
     * Get请求
     *
     * @return Page对象
     */
    public Page get() {
        StringBuilder buffer = new StringBuilder();
        InputStreamReader reader;
        InputStream urlStream;
        String line;
        URL u;
        try {
            u = new URL(url);
            urlStream = u.openStream();
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
     * Post 请求
     * @param parameters 请求参数
     * @return 反序列化后的Json
     */
    public HashMap<String,String> post(HashMap<String,String> parameters){
        return null;
    }
}
