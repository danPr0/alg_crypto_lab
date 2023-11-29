package org.example;

import java.math.BigInteger;
import java.util.Map;

public class Task3 {

    public void solution() {

        System.out.println("Символ Лежандра для a=4 p=6: " + Util.legendraSymbol(BigInteger.valueOf(4), BigInteger.valueOf(6)));
        System.out.println("Символ Якобі для a=3 p=31: " + jacobySymbol(BigInteger.valueOf(3), BigInteger.valueOf(31)));
    }

    private int jacobySymbol(BigInteger a, BigInteger p) {

        int result = 1;
        Map<BigInteger, BigInteger> factorization = Util.factorization(p);
        for (Map.Entry<BigInteger, BigInteger> d : factorization.entrySet()) {
            result *= Math.pow(Util.legendraSymbol(a, d.getKey()), d.getValue().intValue());
        }

        return result;
    }
}
