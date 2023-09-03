package org.example.wasPractice.counter;

public class RaceConditionDemo {

    public static void main(String[] args) {
        Counter counter = new Counter();
        Thread t1 = new Thread(counter, "Thread-1");
        Thread t2 = new Thread(counter, "Thread-2");
        Thread t3 = new Thread(counter, "Thread-3");

        t1.start();
        t2.start();
        t3.start();
        // race condition: 여러 프로세스 혹은 스레드가 동시에 하나의 자원에 접근하기 위해 경쟁하는 상태
        /*
         * 결과 - counter synchronized 전
         * Value for Thread After increment Thread-2 2
         * Value for Thread at last Thread-2 2
         * Value for Thread After increment Thread-3 3
         * Value for Thread at last Thread-3 1
         * Value for Thread After increment Thread-1 1
         * Value for Thread at last Thread-1 0
         * => 싱글톤 객체(여기서는 counter)가 멀티 스레드 환경에서 상태를 갖고 스레드들이 공유하게 하면 문제가 생긴다.
         *  - 싱글톤은 상태를 유지(stateful)하게 설계하면 안 됨
         *  - thread safety 하지 않음
         */

        /*
         * 결과 - counter synchronized 후
         * Value for Thread After increment Thread-1 1
         * Value for Thread at last Thread-1 0
         * Value for Thread After increment Thread-3 1
         * Value for Thread at last Thread-3 0
         * Value for Thread After increment Thread-2 1
         * Value for Thread at last Thread-2 0
         */
    }
}
