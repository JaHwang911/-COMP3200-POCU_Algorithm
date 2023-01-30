package academy.pocu.comp3500.lab5;

import java.math.BigInteger;

public class KeyGenerator {
    public static boolean isPrime(final BigInteger number) {
        if (number.compareTo(BigInteger.valueOf(0)) == 0 || number.compareTo(BigInteger.valueOf(1)) == 0) {
            return false;
        }

        for (BigInteger i = BigInteger.valueOf(2); i.multiply(i).compareTo(number) < 1; i = i.add(BigInteger.valueOf(1))) {
            if (number.mod(i).intValue() == 0) {
                return false;
            }
        }

        return true;
    }
}