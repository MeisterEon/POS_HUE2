public class Main {
    public static void main(String[] args) {
        NumberTester numberTester = new NumberTester("test_csv");
        numberTester.setOddEvenTester(number -> !(number % 2 == 0));
        numberTester.setPrimeTester(number -> new EratosthenesPrimeSieve(10000000).isPrime(number));
        numberTester.setPalindromeTester(Main::checkPalindrome);

        numberTester.testFile();
    }

    public static boolean checkPalindrome(int n)
    {
        int reverse = 0;

        int temp = n;
        while (temp != 0) {
            reverse = (reverse * 10) + (temp % 10);
            temp = temp / 10;
        }

        return (reverse == n);
    }
}
