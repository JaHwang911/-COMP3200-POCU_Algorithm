package academy.pocu.comp3500.lab5;

import java.math.BigInteger;
import java.util.Random;

public class KeyGenerator {
    public static boolean isPrime(final BigInteger number) {
        BigInteger bigZero = BigInteger.ZERO;

        if (number.compareTo(BigInteger.TWO) < 0) {
            return false;
        } else if (number.compareTo(BigInteger.valueOf(2)) == 0) {
            return true;
        } else if (number.mod(BigInteger.TWO).equals(bigZero)) {
            return false;
        }

        BigInteger odd = number.subtract(BigInteger.ONE);
        int s = 0;

        while (odd.mod(BigInteger.TWO).equals(bigZero)) {
            ++s;
            odd = odd.divide(BigInteger.TWO);
        }

        for (int i = 0; i < 25; ++i) {
            BigInteger random = getRandomInRange(BigInteger.TWO, number.subtract(BigInteger.ONE));
            BigInteger x = random.modPow(odd, number);

            if (x.equals(BigInteger.ONE) || x.equals(number.subtract(BigInteger.ONE))) {
                continue;
            }

            for (int j = 0; j < s - 1; j++) {
                x = x.modPow(BigInteger.TWO, number);

                if (x.equals(BigInteger.ONE)) {
                    return false;
                }

                if (x.equals(number.subtract(BigInteger.ONE))) {
                    break;
                }
            }

            if (!x.equals(number.subtract(BigInteger.ONE))) {
                return false;
            }
        }

        return true;
    }

    private static BigInteger getRandomInRange(BigInteger min, BigInteger max) {
        Random random = new Random();
        BigInteger range = max.subtract(min).add(BigInteger.ONE);
        BigInteger result;

        do {
            result = new BigInteger(range.bitLength(), random);
        } while (result.compareTo(range) >= 0);

        return result.add(min);
    }
}