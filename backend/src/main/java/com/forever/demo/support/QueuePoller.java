package com.forever.demo.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;

import java.util.concurrent.BlockingQueue;

public abstract class QueuePoller<T> implements Runnable, DisposableBean {
    protected final Logger logger;
    private final Thread poller;
    private final BlockingQueue<T> queue;

    protected abstract void handle(T element);

    protected QueuePoller(String threadName, BlockingQueue<T> queue){
        if(null == queue){
            throw new NullPointerException("Queue should not be null!");
        }
        this.queue = queue;

        this.logger = LoggerFactory.getLogger(this.getClass());
        poller = new Thread(this);
        poller.setName(threadName);
        poller.start();
    }

    @Override
    public void run() {
        long sec = 1000;
        while(!poller.isInterrupted()){
            try {
                T element = queue.take();
                handle(element);
                sec = 1000;
            } catch (Throwable throwable) {
                logger.error("Failed to take element from queue", throwable);
                try {
                    Thread.sleep(sec);

                    if(sec < 600000){
                        sec *= 2;
                    }

                    if(sec > 600000){
                        sec = 600000;
                    }
                } catch (InterruptedException e) {
                    logger.error("Failed to wait, poller thread is going to stop", throwable);
                    break;
                }
            }
            Thread.yield();
        }
    }

    @Override
    public void destroy() throws Exception {
        poller.interrupt();
    }
}
