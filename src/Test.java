
import Start.RainRequest;

public class Test {
    public static void main(String[] args) {
        RainRequest rain = new RainRequest();
        System.out.println(rain.get("https://bangumi.tv/"));
        rain.save("1");
    }
}
