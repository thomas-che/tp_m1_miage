package ex2;

public class App {
    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        long startTime = System.nanoTime();
        int res = fibonacci.suite(41);
        long endTime = System.nanoTime();
        System.out.println("Res = "+res);
        System.out.println(endTime-startTime + " nanoSec");
    }
}
