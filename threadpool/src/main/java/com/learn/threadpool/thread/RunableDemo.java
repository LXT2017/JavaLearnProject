package com.learn.threadpool.thread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class RunableDemo {

    public static class RunnableTask implements Runnable{


        @Override
        public void run() {
            System.out.println("Runable的线程名字：" + Thread.currentThread().getName());
        }
    }



    public static class ThreadDemo extends Thread{

        @Override
        public void run(){
            System.out.println("Thread的线程名字：" + Thread.currentThread().getName());
        }

    }


    public static void main(String[] args) throws Exception {
        RunnableTask runnableTask = new RunnableTask();
        ThreadDemo threadDemo = new ThreadDemo();
        /**
         * runable和thread的区别
         */

        runnableTask.run();
        threadDemo.start();


        // 线程之间通信的可能
        Callable callable = new Callable<String>(){

            @Override
            public String call() throws Exception {
                Random random = new Random();
                int rs  = random.nextInt();
                return "返回值为" + rs;
            }
        };

        // 进程通信
        FutureTask<String> futureTask = new FutureTask<>(callable);
        new Thread(futureTask).start();
        System.out.println(futureTask.get());

    }
}
