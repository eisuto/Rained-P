import java.util.List;

/**
 * 解析器
 */
public class Parser {

    /**
     * 选择器
     */
    String pot;

    /**
     * 响应体
     */
    String responseBody;

    public Parser(String pot, String responseBody) {
        this.pot = pot;
        this.responseBody = responseBody;
    }
    public List<String> find(){
        return null;
    }
}
