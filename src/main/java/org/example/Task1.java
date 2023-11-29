package org.example;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task1 {

    public void solution() {
        System.out.println("Функція Мьобуса для n=100: " + moebius(BigInteger.valueOf(100)));
        System.out.println("Функція Ейлера для n=100: " + euler(BigInteger.valueOf(100)));
        System.out.println("Найменше спільне кратне для чисел 72 30 184: " +
                gsm(List.of(BigInteger.valueOf(72), BigInteger.valueOf(30), BigInteger.valueOf(184))));
    }

    private BigInteger euler(BigInteger n) {

        BigInteger result = BigInteger.valueOf(0);

        for (BigInteger d = BigInteger.ONE; d.multiply(d).compareTo(n) <= 0; d = d.add(BigInteger.ONE)) {
            if (n.remainder(d).compareTo(BigInteger.ZERO) == 0) {
                result = result.add(moebius(d).multiply(n).divide(d));
                if (d.multiply(d).compareTo(n) != 0) {
                    result = result.add(moebius(n.divide(d)).multiply(d));
                }
            }
        }

        return result;
    }

    private BigInteger gsm(List<BigInteger> numbers) {

        List<Map<BigInteger, BigInteger>> primeFactorizations = numbers.stream().map(Util::factorization).toList();

        Map<BigInteger, BigInteger> gsmFactorization = primeFactorizations.stream().reduce((f, r) ->
                Stream.of(f, r)
                      .flatMap(map -> map.entrySet().stream())
                      .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, BigInteger::max))).get();

        return gsmFactorization.keySet().stream()
                               .reduce(BigInteger.ONE, (r, k) -> r.multiply(k.pow(gsmFactorization.get(k).intValue())));
    }

    private BigInteger moebius(BigInteger n) {

        if (n.compareTo(BigInteger.ONE) == 0) {
            return BigInteger.ONE;
        } else {
            Map<BigInteger, BigInteger> primeFactorization = Util.factorization(n);
            if (Util.factorization(n).values().stream().allMatch(e -> e.compareTo(BigInteger.ONE) == 0)) {
                return BigInteger.valueOf(-1).pow(primeFactorization.size());
            } else {
                return BigInteger.ZERO;
            }
        }
    }
}
