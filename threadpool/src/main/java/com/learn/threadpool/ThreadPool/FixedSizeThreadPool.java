package com.learn.threadpool.ThreadPool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FixedSizeThreadPool {

    // 1、自定义线程池
    // 由多个线程组成
    List<Thread> workers;

    // 2、仓库
    // 仓库放置任务
    private BlockingQueue<Runnable> queue;


    // 3、定义具体线程
    public static class worker extends Thread{

        private FixedSizeThreadPool pool;

        public worker(FixedSizeThreadPool pool) {
            this.pool = pool;
        }

        @Override
        public void run() {
            // 需要告诉具体在做些什么 -- 执行任务
            // 由用户指定的任务 -- runable -- run()方法
            // 用户从仓库中选择

            Runnable task = null;
            while (this.pool.isworking || this.pool.queue.size()>0){
                try {
                    // 使用阻塞的方式，如果没有就一直等待
                    if(this.pool.isworking){
                        task = this.pool.queue.take();
                    }else {
                        // 万一阻塞
                        task = this.pool.queue.poll();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (task != null){
                    task.run();
                    System.out.println("线程："+Thread.currentThread().getName()+"执行完毕");
                }
            }
        }
    }

    public FixedSizeThreadPool(int poolsize, int tasksize) {
        if(poolsize <= 0 || tasksize <=0){
            throw new IllegalArgumentException("参数错误");
        }


        this.queue = new LinkedBlockingQueue<>(tasksize);
        this.workers = Collections.synchronizedList(new ArrayList<>());


        for (int i=0;i<poolsize;i++){
            worker worker = new worker(this);
            worker.start();
            workers.add(worker);
        }
    }

    // 向仓库放任务
    public void submit(Runnable runable) throws InterruptedException {
        this.queue.put(runable);
    }


    public void execute(Runnable runable) throws InterruptedException {
        if (isworking){
            this.queue.offer(runable);
        }else {
            isworking = false;
        }

    }

    // 关闭线程池
    private  volatile  boolean isworking = true;
    public void shutdown(){
        isworking = false;

        for (Thread thread:workers) {
            // 强制结束
            if(Thread.State.BLOCKED.equals(thread.getState())){
                thread.interrupt();
            }

        }
    }

}
