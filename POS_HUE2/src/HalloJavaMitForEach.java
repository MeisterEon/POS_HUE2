import java.util.Arrays;
import java.util.List;

public class HalloJavaMitForEach {
    public void iterateOverList() {
        List<String> stringList = Arrays.asList("Nasus", "Zeri", "Warwick");

        for (String string : stringList) {
            System.out.println(string);
        }

        stringList.forEach(System.out::println);
    }
}
