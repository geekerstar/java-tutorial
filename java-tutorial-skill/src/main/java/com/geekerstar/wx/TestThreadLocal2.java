package com.geekerstar.wx;

public class TestThreadLocal2 {

    private static ThreadLocal<Integer> seqCount = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };


    public int nextSeq() {
        seqCount.set(seqCount.get() +1);
        return seqCount.get();
    }

    public static void main(String [] args) {
        TestThreadLocal2 testThreadLocal2 = new TestThreadLocal2();

        SeqThread seqThread1 = new SeqThread(testThreadLocal2);
        SeqThread seqThread2 = new SeqThread(testThreadLocal2);
        SeqThread seqThread3 = new SeqThread(testThreadLocal2);
        SeqThread seqThread4 = new SeqThread(testThreadLocal2);

        seqThread1.start();
        seqThread2.start();
        seqThread3.start();
        seqThread4.start();
    }

    public static class SeqThread extends Thread {

        private TestThreadLocal2 testThreadLocal2;

        public SeqThread(TestThreadLocal2 testThreadLocal2) {
            this.testThreadLocal2 = testThreadLocal2;
        }

        @Override
        public void run() {
            for (int i=0; i<3; i++) {
                System.out.println(Thread.currentThread().getName()+" seqCount:"+ testThreadLocal2.nextSeq());
            }
        }
    }
 }
