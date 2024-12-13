package org.example.lab8;

import java.util.Random;
import java.util.concurrent.*;

public class ParallelMonteCarloPi {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        if(args.length != 1) {
            System.out.println("Usage: java ParallelMonteCarloPi <number_of_threads>");
            return;
        }

        int numberOfThreads = Integer.parseInt(args[0]);
        final long totalIterations = 1_000_000_000L;
        long iterationsPerThread = totalIterations / numberOfThreads;

        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        long startTime = System.nanoTime();
        Future<Long>[] results = new Future[numberOfThreads];

        for(int i = 0; i < numberOfThreads; ++i) {
            results[i] = executor.submit(() -> {
                Random random = new Random();
                long insideCircle = 0;

                for(long j = 0; j < iterationsPerThread; ++j) {
                    double x = random.nextDouble();
                    double y = random.nextDouble();
                    if(x * x + y * y <= 1.0) {
                        insideCircle++;
                    }
                }
                latch.countDown();
                return insideCircle;
            });
        }
        latch.await(); // Очікуємо завершення всіх потоків
        long totalInsideCircle = 0;

        for(Future<Long> result : results) {
            totalInsideCircle += result.get();
        }

        executor.shutdown();
        double piEstimate = 4.0 * totalInsideCircle / totalIterations;
        long endTime = System.nanoTime();

        System.out.printf("\n\nPI is %.5f\n", piEstimate);
        System.out.printf("THREADS %d\n", numberOfThreads);
        System.out.printf("ITERATIONS %,d\n", totalIterations);
        System.out.printf("TIME %.2fms\n", (endTime - startTime) / 1_000_000.0);

    }
}
