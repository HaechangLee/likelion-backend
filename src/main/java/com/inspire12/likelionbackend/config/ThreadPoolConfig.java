package com.inspire12.likelionbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@EnableAsync
@Configuration
public class ThreadPoolConfig {
//    private static final int CORE_POOL_SIZE = 2;
    private static final int CORE_POOL_SIZE =10;
//    private static final int MAX_POOL_SIZE = 2;
    private static final int MAX_POOL_SIZE = 10;


    @Bean(name = "customThreadPoolExecutor")
    public Executor customThreadPoolExecutor() {
        ThreadPoolTaskExecutor threadPoolExecutor = new ThreadPoolTaskExecutor();
        /* 동시에 실행 할 기본 스레드의 수를 설정할 수 있다. 기본 값은 1이다. */
        threadPoolExecutor.setCorePoolSize(CORE_POOL_SIZE);
        /* thread-pool의 사용할 수 있는 최대 스레드 수를 설정할 수 있다. 기본값 Integer.MAX_VALUE*/
        threadPoolExecutor.setMaxPoolSize(MAX_POOL_SIZE);
        threadPoolExecutor.setThreadNamePrefix("likelion-");
        return threadPoolExecutor;
    }
}
