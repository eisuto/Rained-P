package Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * 页面
 */
public class Page {

    /**
     * 当前页面的html体
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
    Page(String html) {
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
        return "Page{" +
                "html='" + html + '\'' +
                '}';
    }
}
