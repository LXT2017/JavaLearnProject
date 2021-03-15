package com.learn.threadpool.ThreadPool;

public class ThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
        FixedSizeThreadPool fixedSizeThreadPool = new FixedSizeThreadPool(3,6);
        for (int i =0; i<6; i++){
            fixedSizeThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("任务放入了仓库");
                    try {
                        Thread.sleep(2000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        System.out.println("线程中断");
                    }
                }
            });
        }
        fixedSizeThreadPool.shutdown();
    }
}
