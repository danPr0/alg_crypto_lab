package org.example;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Task5 {

    public void solution() {

        BigInteger alpha = BigInteger.valueOf(2);
        BigInteger beta = BigInteger.valueOf(32);
        BigInteger m = BigInteger.valueOf(3);

        List<BigInteger> alphasPowerJ = new ArrayList<>();
        for (BigInteger j = BigInteger.ZERO; j.compareTo(m) < 0; j = j.add(BigInteger.ONE)) {
            alphasPowerJ.add(alpha.pow(j.intValue()));
        }

        BigInteger x = null;
        for (BigInteger i = BigInteger.ZERO; i.compareTo(m) < 0; i = i.add(BigInteger.ONE)) {
            BigInteger leftVal = beta.divide(alpha.pow(m.multiply(i).intValue()));
            for (BigInteger rightVal : alphasPowerJ) {
                if (leftVal.compareTo(rightVal) == 0) {
                    x = i.multiply(m).add(BigInteger.valueOf(alphasPowerJ.indexOf(rightVal)));
                }
            }
        }

        System.out.println("Алгоритм «великий крок – малий крок» для alpha=2 beta=32 m=3: " + x);
    }
}
