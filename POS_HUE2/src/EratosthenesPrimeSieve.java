public class EratosthenesPrimeSieve implements PrimeSieve{
    private final Boolean[] primeNumberArray;

    public EratosthenesPrimeSieve(int limit) {
        primeNumberArray = new Boolean[limit + 1];
        for (int i = 2; i < limit + 1; i++) {
            primeNumberArray[i] = true;
        }

        for (int i = 2; i <= Math.sqrt(limit); i++) {
            if (primeNumberArray[i]) {
                for (int j = i * i; j < limit + 1; j += i) {
                    primeNumberArray[j] = false;
                }
            }
        }
    }

    @Override
    public boolean isPrime(int p) {
        return primeNumberArray[p];
    }

    @Override
    public void printPrimes() {
        for (int i = 2; i < primeNumberArray.length; i++) {
            if (primeNumberArray[i]) {
                System.out.println(i);
            }
        }
    }
}
