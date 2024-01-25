package chap07;

public class RecCalculator implements Calculator {

    @Override
    public long factorial(long num) {
        long start = System.currentTimeMillis();
        try {
            if (num == 0) return 1; else return num * factorial(num - 1);
            // 특이하네 로직으로는 factorial(20), factorial(19) ... 순서로 실행될거 같은데
            // 결과는 1, 2, 3, ... 순으로 계산을 하네
        } finally {
            long end = System.currentTimeMillis();
            System.out.printf("RecCalculator.factorial(%d) 실행 시간 1 = %d\n", num, (end - start));
        }
    }
}
