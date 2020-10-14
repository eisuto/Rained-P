package Start;

import java.io.*;
import java.net.URL;

/**
 * @author eisuto
 */
public class RainRequest {
    private String htmlText;

    public void save(String name){
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
     * @param url 地址
     * @return html文本
     */
    public String get(String url){
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
