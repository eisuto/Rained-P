
import Service.RainRequest;

public class Test {
    public static void main(String[] args) {
        RainRequest rain = new RainRequest("https://bangumi.tv/");
        System.out.println(rain.get().toString());

    }
}
