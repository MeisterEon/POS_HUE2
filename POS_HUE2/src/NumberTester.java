import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NumberTester {
    private String fileName;
    private NumberTest oddTester;
    private NumberTest primeTester;
    private NumberTest palindromeTester;
    public NumberTester(String fileName) {
        this.fileName = fileName;
    }

    public void setOddEvenTester(NumberTest oddTester) {
        this.oddTester = oddTester;
    }

    public void setPrimeTester(NumberTest primeTester) {
        this.primeTester = primeTester;
    }

    public void setPalindromeTester(NumberTest palindromeTester) {
        this.palindromeTester = palindromeTester;
    }

    public void testFile() {
        try (Scanner scanner = new Scanner(new File(fileName))){
            int limit = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < limit; i++) {
                String[] split = scanner.nextLine().split(" ");
                switch (Integer.parseInt(split[0])) {
                    case 1 -> System.out.println(oddTester.testNumber(Integer.parseInt(split[1])) ? "ODD" : "EVEN");
                    case 2 -> System.out.println(primeTester.testNumber(Integer.parseInt(split[1])) ? "PRIME" : "NO PRIME");
                    case 3 -> System.out.println(palindromeTester.testNumber(Integer.parseInt(split[1])) ? "PALINDROM" : "NO PALINDROM");
                    default -> System.out.println("Invalid Number");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
