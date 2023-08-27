package com.machnet.shared.utils;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncReportGenerator {

    public <T> void generateAsync(List<T> reports, Callback<T> callback) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> callback.call(reports));
        executor.shutdown();
    }
}
