package org.example;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Util {

    public static Map<BigInteger, BigInteger> factorization(BigInteger n) {
        Map<BigInteger, BigInteger> result = new HashMap<>();

        for (BigInteger p = BigInteger.TWO; p.multiply(p).compareTo(n) <= 0; p = p.add(BigInteger.ONE)) {
            while (n.remainder(p).compareTo(BigInteger.ZERO) == 0) {
                result.put(p, result.getOrDefault(p, BigInteger.ZERO).add(BigInteger.ONE));
                n = n.divide(p);
            }
        }

        if (n.compareTo(BigInteger.ONE) != 0) {
            result.put(n, BigInteger.ONE);
        }

        return result;
    }

    public static BigInteger gsd(BigInteger a, BigInteger b) {

        Map<BigInteger, BigInteger> aFactor = factorization(a);
        Map<BigInteger, BigInteger> bFactor = factorization(b);
        Map<BigInteger, BigInteger> gsdFactor = new HashMap<>();

        for (Map.Entry<BigInteger, BigInteger> af : aFactor.entrySet()) {
            if (bFactor.containsKey(af.getKey())) {
                gsdFactor.put(af.getKey(), af.getValue().min(bFactor.get(af.getKey())));
            }
        }

        return gsdFactor.keySet().stream()
                        .reduce(BigInteger.ONE, (r, k) -> r.multiply(k.pow(gsdFactor.get(k).intValue())));
    }

    public static int legendraSymbol(BigInteger a, BigInteger p) {
        if (a.pow((p.intValue() - 1) / 2).remainder(p).compareTo(BigInteger.ONE) == 0) {
            return 1;
        } else {
            return -1;
        }
    }

    public static boolean isPrime(int n) {
        boolean result = true;

        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                result = false;
                break;
            }
        }

        return result;
    }
}
