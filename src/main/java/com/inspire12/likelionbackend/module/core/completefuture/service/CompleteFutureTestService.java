package com.inspire12.likelionbackend.module.core.completefuture.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Service
public class CompleteFutureTestService {
    private final Executor customFutureThreadPoolExecutor;
    private static Logger log = LoggerFactory.getLogger(CompleteFutureTestService.class);

    public CompleteFutureTestService(Executor customFutureThreadPoolExecutor) {
        this.customFutureThreadPoolExecutor = customFutureThreadPoolExecutor;
    }

    public void sayHello(int i) {
        Supplier<String> heavyTask = new Supplier<String>() {
            @Override
            public String get() {
                log.info("executing Thread Name .. [{}]", Thread.currentThread().getName());
                try {
                    Thread.sleep(1000); // 1초간 정지
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return "Hello " + i;
            }
        };
        Function<String, String> plusTask = new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s.concat("영학");
            }
        };

        Consumer<String> consume = new Consumer<String>() {
            @Override
            public void accept(String s) {
                log.info(s);
            }
        };
        CompletableFuture.supplyAsync(heavyTask)
                .thenApply(plusTask)
                .thenAccept(consume);

    }

    public String getHelloSync() {
        log.info("executing Thread Name .. [{}]", Thread.currentThread().getName());
        try {
            Thread.sleep(1000); // 1초간 정지
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Hello";
    }

    public CompletableFuture<String> getHello(int i) {
        return CompletableFuture.supplyAsync(() -> {
                    log.info("executing Thread Name .. [{}]", Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000); // 1초간 정지
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return "Hello " + i;
                })
                .thenApply(s -> s.concat("영학"));
    }

    public CompletableFuture<String> getHelloWithThreadPool() {

        return CompletableFuture.supplyAsync(() -> {
            log.info("executing Thread Name .. [{}]", Thread.currentThread().getName());
            try {
                Thread.sleep(1000); // 1초간 정지
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Hello from thread" + Thread.currentThread().getName();
        }, customFutureThreadPoolExecutor);
    }

}
