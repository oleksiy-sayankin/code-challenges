package net.javacogito.primepalindrome;


public class Main {
  public static int UPPER_BOUND = 1000;

  public static void main(String[] args) {
    int start = UPPER_BOUND;
    if (isEven(UPPER_BOUND)) {
      start = UPPER_BOUND - 1;
    }

    for (int i = start; i >= 1; i--) {
      if (isPalindrome(i) && isPrime(i)) {
        System.out.println(i);
        break;
      }
    }

  }

  public static boolean isPrime(int n) {
    //check if n is a multiple of 2
    if (n % 2 == 0) return false;
    //if not, then just check the odds
    for (int i = 3; i * i <= n; i += 2) {
      if (n % i == 0)
        return false;
    }
    return true;
  }

  public static boolean isPalindrome(int n) {
    String s[] = Integer.toString(n).split("");
    for (int i = 1; i <= (s.length - 1) / 2; i++) {
      if (!s[i].equals(s[s.length - i])) {
        return false;
      }
    }
    return true;
  }

  public static boolean isEven(int n) {
    return n % 2 == 0;
  }
}
