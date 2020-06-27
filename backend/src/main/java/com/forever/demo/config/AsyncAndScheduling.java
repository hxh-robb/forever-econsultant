package com.forever.demo.config;

import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
@EnableAsync @EnableScheduling
public class AsyncAndScheduling implements SchedulingConfigurer, AsyncConfigurer {

    /* ***** Following are Async Executor Settings ***** */

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("AsyncExecutor-");
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new LoggingAsyncUncaughtException();
    }

    private static final class LoggingAsyncUncaughtException implements AsyncUncaughtExceptionHandler {
        @Override
        public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
            if(null != method){
                LoggerFactory.getLogger(method.getDeclaringClass()).error(throwable.getMessage(), throwable);
            } else {
                LoggerFactory.getLogger(LoggingAsyncUncaughtException.class).error(throwable.getMessage(), throwable);
            }
        }
    }

    /* ***** Following are Scheduler Settings ***** */

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(scheduler());
    }

    @Bean(destroyMethod = "shutdown")
    public Executor scheduler(){
        return Executors.newScheduledThreadPool(2);
    }
}
