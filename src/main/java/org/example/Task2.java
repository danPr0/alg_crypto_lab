package org.example;

import java.math.BigInteger;
import java.util.List;

public class Task2 {

    public void solution() {

        List<EqualityCoefficients> input = getInput();

        BigInteger m = BigInteger.ONE;
        for (EqualityCoefficients ec : input) {
            m = m.multiply(ec.m);
        }

        BigInteger x = BigInteger.ZERO;
        for (EqualityCoefficients ec : input) {
            BigInteger ms = m.divide(ec.m);
            BigInteger ns = BigInteger.ONE;
            while (ms.multiply(ns).remainder(ec.m).compareTo(BigInteger.ONE) != 0) {
                ns = ns.add(BigInteger.ONE);
            }
            x = x.add(ms.multiply(ns).multiply(ec.c));
        }
        x = x.remainder(m);

        System.out.println(x);
    }

    private List<EqualityCoefficients> getInput() {
        System.out.print("Розв’язання системи лінійних порівнянь за модулем для пар (c, m) [(1, 5), (10, 11)]: ");
        return List.of(
                new EqualityCoefficients(BigInteger.valueOf(1), BigInteger.valueOf(5)),
                new EqualityCoefficients(BigInteger.valueOf(10), BigInteger.valueOf(11)));
    }

    private record EqualityCoefficients(BigInteger c, BigInteger m) {

    }
}
