
import Service.RainRequest;

import java.util.HashMap;

public class Test {
    public static void main(String[] args) {
        HashMap<String,String> test = new HashMap<>();
        test.put("sort","rank");
        RainRequest rain = new RainRequest("https://bangumi.tv/book/browser");
        System.out.println(rain.get(test).toString());

    }
}
