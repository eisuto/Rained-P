package Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * 响应
 */
public class Response {

    /**
     * 响应体
     */
    private String html;

    /**
     * 存放当前页面的全部链接
     */
    private List<String> links;

    /**
     * 构造时
     *
     * @param html html文本
     */
    Response(String html) {
        this.html = html;
        if (html != null) {
            //使用选择器将此处获取当前页面全部链接
            links = null;
        }
    }

    /**
     * 保存为html文件
     *
     * @param name 文件名
     */
    public void save(String name) {
        try {
            FileOutputStream saveStream = new FileOutputStream(name + ".html");
            byte[] htmlBytes = html.getBytes();
            for (byte b : htmlBytes) {
                saveStream.write(b);
            }
            saveStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        return "Response{" +
                "html='" + html + '\'' +
                '}';
    }
}
