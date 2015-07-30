package net.javacogito.luckytickets;


import java.io.*;
import java.math.BigInteger;

/*
Many thanks to http://www.ega-math.narod.ru/Quant/Tickets.htm
*/


public class Main {

    private static BigInteger[][]Ncache;

    public static void main( String[] args ) throws IOException {


        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String inputLine;
        while ((inputLine = buffer.readLine()) != null) {
            inputLine = inputLine.trim();
           System.out.println(newLuckyCount(Integer.parseInt(inputLine)));
        }
    }


    public static BigInteger newLuckyCount(int digits){
        int halfDigits = digits / 2;
        int maxSum = 9 * halfDigits;
        BigInteger totalCount = BigInteger.ZERO;

        Ncache = new BigInteger[halfDigits + 1][maxSum + 1];

        for (int k = 0; k <= maxSum; k++){
            BigInteger N = N(halfDigits, k);
            BigInteger N2 = N.multiply(N);
            totalCount = totalCount.add(N2);
        }
        return totalCount;
    }


    public static BigInteger N(int n, int k){
        if(Ncache[n][k] != null){
            return Ncache[n][k];
        }
        if (n == 1){
            if (k >= 0 && k <= 9){
                Ncache[n][k] = BigInteger.ONE;
                return BigInteger.ONE;
            } else {
                Ncache[n][k] = BigInteger.ZERO;
                return BigInteger.ZERO;
            }
        }

        BigInteger sum = BigInteger.ZERO;
        for (int l = 0; l <= 9; l++){
            if (k < 9 && l > k){
                continue;
            }
            sum = sum.add(N(n - 1, k - l));
        }
        Ncache[n][k] = sum;
        return sum;
    }
}
