package chap07;

public class ImpeCalculator implements Calculator {

    @Override
    public long factorial(long num) {
        // long start = System.currentTimeMillis();
        // System.out.println("start = " + start);
        long result = 1;
        for (long i = 1; i <= num; i++) {
            result *= i;
        }
        // long end = System.currentTimeMillis();
        // System.out.println("end   = " + end);
        // System.out.printf("ImpeCalculator.factorial(%d) 실행시간 = %d\n", num, (end - start));
        return result;
    }
}
