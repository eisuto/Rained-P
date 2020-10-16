package Start;

import java.io.*;
import java.net.URL;

/**
 * 请求器
 * @author eisuto
 */
public class RainRequest {
    private String htmlText;
    private String url;

    public RainRequest(String url) {
        this.url = url;
    }

    /**
     *
     * @param name 文件名
     */
    public void save(String name) {
        try {
            FileOutputStream saveStream = new FileOutputStream(name + ".html");
            byte[] htmlBytes = htmlText.getBytes();
            for (byte b : htmlBytes) {
                saveStream.write(b);
            }
            saveStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Get请求
     *
     * @return html文本
     */
    public String get() {
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
        htmlText = buffer.toString();
        return htmlText;
    }
}
