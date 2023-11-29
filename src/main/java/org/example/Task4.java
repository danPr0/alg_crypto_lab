package org.example;

import java.math.BigInteger;
import java.util.*;

public class Task4 {

    public void solution() {
        System.out.println("Алгоритм квадратичного решета для n=15347: " + quadraticSieve(BigInteger.valueOf(15347)));
    }

    private BigInteger quadraticSieve(BigInteger n) {

        List<BigInteger> s = new ArrayList<>();
//        List<BigInteger> s = new ArrayList<>(List.of(BigInteger.ONE.negate()));
        for (BigInteger pj = BigInteger.TWO; pj.compareTo(n) != 0 && s.size() < calcT(n.doubleValue()) - 1; pj =
                pj.add(BigInteger.ONE)) {
            if (Util.isPrime(pj.intValue()) && Util.legendraSymbol(n, pj) == 1) {
                s.add(pj);
            }
        }
//        System.out.println(s);

        BigInteger m = n.sqrt();
//        System.out.println(m);
        Map<Pair, List<Integer>> matrix = new LinkedHashMap<>();

        BigInteger unsignedZ = BigInteger.ZERO;
        while (matrix.size() != s.size() + 1) {
            for (int sign = 0; sign <= 1; sign++) {
                BigInteger z = unsignedZ.multiply(BigInteger.valueOf(-1).pow(sign));
                BigInteger q = (m.add(z)).pow(2).subtract(n);
                Pair curPair = new Pair(z.add(m), q);
                matrix.put(curPair, new ArrayList<>(Collections.nCopies(s.size(), 0)));

                for (int i = 0; i < s.size(); i++) {
                    while (q.remainder(s.get(i)).compareTo(BigInteger.ZERO) == 0 && q.multiply(s.get(i)).compareTo(BigInteger.ZERO) > 0) {

                        List<Integer> v = matrix.get(curPair);
                        v.set(i, (v.get(i) + 1) % 2);
                        matrix.put(curPair, v);
                        q = q.divide(s.get(i));
                    }
                }

                if (q.compareTo(BigInteger.ONE) != 0 || matrix.get(curPair).stream().reduce(0, Integer::sum) == 0) {
                    matrix.remove(curPair);
                }
            }
            unsignedZ = unsignedZ.add(BigInteger.ONE);
        }

//        System.out.println(matrix);

        List<Integer> solution =
                getX(new ArrayList<>(matrix.values()), new ArrayList<>(Collections.nCopies(matrix.size(), 0)));
//        System.out.println(solution);

        if (solution != null) {
            BigInteger x = BigInteger.ONE;
            BigInteger y = BigInteger.ONE;
            for (int i = 0; i < solution.size(); i++) {
                if (solution.get(i) == 1) {
                    Pair pair = matrix.keySet().stream().toList().get(i);
                    x = x.multiply(pair.b);
                    y = y.multiply(pair.a.multiply(pair.a));
                }
            }
            x = x.sqrt();
            y = y.sqrt();

            return Util.gsd(y.subtract(x), n);
        }

        return null;
    }

    private List<Integer> getX(List<List<Integer>> v, List<Integer> x) {

        if (!x.stream().reduce(0, Integer::sum).equals(0)) {
            boolean solutionAccepted = true;
            for (int j = 0; j < v.get(0).size(); j++) {
                int result = 0;
                for (int i = 0; i < v.size(); i++) {
                    result += x.get(i) * (v.get(i).get(j));
                }

                if (result % 2 != 0) {
                    solutionAccepted = false;
                }
            }
            if (solutionAccepted) {
                return x;
            }
        }

        for (int i = 0; i < x.size(); i++) {
            if (x.get(i) != 1) {
                x.set(i, 1);
                List<Integer> solution = getX(v, x);
                if (solution != null) {
                    return solution;
                }
                x.set(i, 0);
            }
        }

        return null;
    }

    private double calcT(double n) {
        return Math.exp(0.5 * Math.sqrt(0.5 * Math.log(n) * Math.log(Math.log(n))));
    }

    private record Pair(BigInteger a, BigInteger b) {

    }
}
