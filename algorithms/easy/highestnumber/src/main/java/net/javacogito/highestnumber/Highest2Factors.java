package net.javacogito.highestnumber;

public class Highest2Factors {
  public static long[] highestBiPrimeFac(long p1, long p2, long n) {
    long maxK1 = (long) Math.ceil(customLog(p1, n));
    long maxK2 = (long) Math.ceil(customLog(p2, n));
    long[] result = new long[3];
    long maxNumber = p1 * p2;
    for (long k1 = 1; k1 <= maxK1; k1++) {
      for (long k2 = 1; k2 <= maxK2; k2++) {
        long currentMaxNumber = power(p1, k1) * power(p2, k2);
        if (currentMaxNumber <= n && currentMaxNumber > maxNumber) {
          maxNumber = currentMaxNumber;
          result[0] = maxNumber;
          result[1] = k1;
          result[2] = k2;
        }
      }
    }
    return result;
  }


  private static double customLog(double base, double logNumber) {
    return Math.log(logNumber) / Math.log(base);
  }

  private static long power(long base, long power) {
    long result = 1;
    for (long i = 1; i <= power; i++) {
      result *= base;
    }
    return result;
  }
}
