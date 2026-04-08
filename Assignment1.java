import java.util.*;

public class Assignment1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Task 1:");
        printDigits(5481);

        System.out.println("\nTask 2:");
        int[] arr = {3, 2, 4, 1};
        System.out.println("Average: " + average(arr, arr.length));

        System.out.println("\nTask 3:");
        System.out.println(isPrime(7, 2) ? "Prime" : "Composite");
        System.out.println(isPrime(10, 2) ? "Prime" : "Composite");

        System.out.println("\nTask 4:");
        System.out.println("Factorial: " + factorial(5));

        System.out.println("\nTask 5:");
        System.out.println("Fibonacci(5): " + fib(5));

        System.out.println("\nTask 6:");
        System.out.println("Power: " + power(2, 10));

        System.out.println("\nTask 7:");
        System.out.println("Enter 4 numbers:");
        reversePrint(4, sc);

        System.out.println("\n\nTask 8:");
        System.out.println(onlyDigits("123456", 0) ? "Yes" : "No");
        System.out.println(onlyDigits("123a12", 0) ? "Yes" : "No");

        System.out.println("\nTask 9:");
        System.out.println("Length: " + length("hello"));

        System.out.println("\nTask 10:");
        System.out.println("GCD: " + gcd(32, 48));
    }

    public static void printDigits(int n) {
        if (n < 10) {
            System.out.println(n);
            return;
        }
        printDigits(n / 10);
        System.out.println(n % 10);
    }

    public static int sum(int[] arr, int n) {
        if (n == 0) return 0;
        return arr[n - 1] + sum(arr, n - 1);
    }

    public static double average(int[] arr, int n) {
        return (double) sum(arr, n) / n;
    }

    public static boolean isPrime(int n, int i) {
        if (n <= 2) return n == 2;
        if (n % i == 0) return false;
        if (i * i > n) return true;
        return isPrime(n, i + 1);
    }

    public static int factorial(int n) {
        if (n == 1) return 1;
        return n * factorial(n - 1);
    }

    public static int fib(int n) {
        if (n <= 1) return n;
        return fib(n - 1) + fib(n - 2);
    }

    public static int power(int a, int n) {
        if (n == 0) return 1;
        return a * power(a, n - 1);
    }

    public static void reversePrint(int n, Scanner sc) {
        if (n == 0) return;
        int x = sc.nextInt();
        reversePrint(n - 1, sc);
        System.out.print(x + " ");
    }

    public static boolean onlyDigits(String s, int i) {
        if (i == s.length()) return true;
        if (!Character.isDigit(s.charAt(i))) return false;
        return onlyDigits(s, i + 1);
    }

    public static int length(String s) {
        if (s.equals("")) return 0;
        return 1 + length(s.substring(1));
    }

    public static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}