import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        try {
            NumberTester numberTester = new NumberTester("test_csv");
            numberTester.setOddEvenTester(number -> !(number % 2 == 0));
            numberTester.setPrimeTester(number -> new EratosthenesPrimeSieve(10000000).isPrime(number));
            numberTester.setPalindromeTester(Main::checkPalindrome);

            numberTester.testFile();


            RationalCalculator rationalCalculator = new RationalCalculator((a, b) -> {
                double numerator = a.getA() * b.getB() + a.getB() * b.getA();
                double denominator = a.getB() * b.getB();

                double divisor = gcd(numerator, denominator);
                numerator /= divisor;
                denominator /= divisor;

                Number number = new Number();
                number.setA(numerator);
                number.setB(denominator);

                return number;
            }, (a, b) -> {
                double numerator = a.getA() * b.getB() - a.getB() * b.getA();
                double denominator = a.getB() * b.getB();

                double divisor = gcd(numerator, denominator);
                numerator /= divisor;
                denominator /= divisor;

                Number number = new Number();
                number.setA(numerator);
                number.setB(denominator);

                return number;
            }, (a, b) -> {
                double numerator = a.getA() * b.getA();
                double denominator = a.getB() * b.getB();

                double divisor = gcd(numerator, denominator);
                numerator /= divisor;
                denominator /= divisor;

                Number number = new Number();
                number.setA(numerator);
                number.setB(denominator);

                return number;
            }, (a, b) -> {
                double numerator = a.getA() * b.getB();
                double denominator = a.getB() * b.getA();

                double divisor = gcd(numerator, denominator);
                numerator /= divisor;
                denominator /= divisor;

                Number number = new Number();
                number.setA(numerator);
                number.setB(denominator);

                return number;
            });

            VectorCalculator vectorCalculator = new VectorCalculator((a, b) -> {
                Number result = new Number();
                result.setA(a.getA() + b.getA());
                result.setB(a.getB() + b.getB());
                return result;
            }, (a, b) -> {
                Number result = new Number();
                result.setA(a.getA() - b.getA());
                result.setB(a.getB() - b.getB());
                return result;
            }, (a, b) -> {
                double[] aValues = {a.getA(), a.getB(), 0};
                double[] bValues = {b.getA(), b.getB(), 0};

                Number result = new Number();
                result.setA(aValues[1] * bValues[2] - aValues[2] * bValues[1]);
                result.setB(aValues[2] * bValues[0] - aValues[0] * bValues[2]);
                return result;
            }, (a, b) -> {
                double dotProduct = a.getA() * b.getA() + a.getB() * b.getB();

                Number result = new Number();
                result.setA(dotProduct);
                result.setB(1);
                return result;
            });

            ComplexCalculator complexCalculator = new ComplexCalculator((a, b) -> {
                Number result = new Number();
                result.setA(a.getA() + b.getA());
                result.setB(a.getB() + b.getB());
                return result;
            }, (a, b) -> {
                Number result = new Number();
                result.setA(a.getA() - b.getA());
                result.setB(a.getB() - b.getB());
                return result;
            }, (a, b) -> {
                Number result = new Number();
                result.setA((a.getA() * b.getA()) - (a.getB() * b.getB()));
                result.setB((a.getA() * b.getB()) + (a.getB() * b.getA()));
                return result;
            }, (a, b) -> {
                Number result = new Number();
                double denominator = (b.getA() * b.getA()) + (b.getB() * b.getB());

                if (denominator == 0) {
                    throw new ArithmeticException("Division by zero");
                }

                double realPart = (a.getA() * b.getA() + a.getB() * b.getB()) / denominator;
                double imaginaryPart = (a.getB() * b.getA() - a.getA() * b.getB()) / denominator;
                result.setA(realPart);
                result.setB(imaginaryPart);
                return result;
            });
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            AbstractCalculator currentCalculator = null;
            while (true) {
                boolean continueAfter = false;
                printChooseCalculator();
                System.out.print("> ");
                switch (Integer.parseInt(bufferedReader.readLine())) {
                    case 1 -> currentCalculator = rationalCalculator;
                    case 2 -> currentCalculator = vectorCalculator;
                    case 3 -> currentCalculator = complexCalculator;
                    case 4 -> System.exit(1);
                    default -> continueAfter = true;
                }
                ;
                if (continueAfter) continue;

                String[] stringArray = new String[4];
                System.out.print("Enter number x a> ");
                stringArray[0] = bufferedReader.readLine();
                System.out.print("Enter number x b> ");
                stringArray[1] = bufferedReader.readLine();
                System.out.print("Enter number y a> ");
                stringArray[2] = bufferedReader.readLine();
                System.out.print("Enter number y b> ");
                stringArray[3] = bufferedReader.readLine();

                printChooseOperation();
                System.out.print("> ");
                switch (Integer.parseInt(bufferedReader.readLine())) {
                    case 1 -> printSolution(case1(currentCalculator, stringArray));
                    case 2 -> printSolution(case2(currentCalculator, stringArray));
                    case 3 -> printSolution(case3(currentCalculator, stringArray));
                    case 4 -> printSolution(case4(currentCalculator, stringArray));
                    default -> continueAfter = true;
                }
                if (continueAfter) continue;
            }
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
    }

    private static double gcd(double a, double b) {
        return b == 0 ? a : gcd(b, a % b);
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

    private static void printChooseCalculator() {
        System.out.println("Choose calculator");
        System.out.println("1 - Relational calculator");
        System.out.println("2 - Vector calculator");
        System.out.println("3 - Complex calculator");
        System.out.println("4 - Exit program");
    }

    private static void printChooseOperation() {
        System.out.println("Choose operation:");
        System.out.println("1 - add");
        System.out.println("2 - subtract");
        System.out.println("3 - multiply");
        System.out.println("4 - divide");
        System.out.println("5 - enters numbers again");
    }

    private static Number case1(AbstractCalculator currentCalculator, String[] stringArray) {
        Number number1 = new Number();
        number1.setA(Integer.parseInt(stringArray[0]));
        number1.setB(Integer.parseInt(stringArray[1]));

        Number number2 = new Number();
        number2.setA(Integer.parseInt(stringArray[2]));
        number2.setB(Integer.parseInt(stringArray[3]));

        return currentCalculator.add(number1, number2);
    }
    private static Number case2(AbstractCalculator currentCalculator, String[] stringArray) {
        Number number1 = new Number();
        number1.setA(Integer.parseInt(stringArray[0]));
        number1.setB(Integer.parseInt(stringArray[1]));

        Number number2 = new Number();
        number2.setA(Integer.parseInt(stringArray[2]));
        number2.setB(Integer.parseInt(stringArray[3]));

        return currentCalculator.substract(number1, number2);
    }
    private static Number case3(AbstractCalculator currentCalculator, String[] stringArray) {
        Number number1 = new Number();
        number1.setA(Integer.parseInt(stringArray[0]));
        number1.setB(Integer.parseInt(stringArray[1]));

        Number number2 = new Number();
        number2.setA(Integer.parseInt(stringArray[2]));
        number2.setB(Integer.parseInt(stringArray[3]));

        return currentCalculator.multiply(number1, number2);
    }
    private static Number case4(AbstractCalculator currentCalculator, String[] stringArray) {
        Number number1 = new Number();
        number1.setA(Integer.parseInt(stringArray[0]));
        number1.setB(Integer.parseInt(stringArray[1]));

        Number number2 = new Number();
        number2.setA(Integer.parseInt(stringArray[2]));
        number2.setB(Integer.parseInt(stringArray[3]));

        return currentCalculator.divide(number1, number2);
    }

    private static void printSolution(Number number) {
        System.out.println("--------------------");
        System.out.println("a = " + number.getA());
        System.out.println("b = " + number.getB());
        System.out.println("--------------------");
    }
}
