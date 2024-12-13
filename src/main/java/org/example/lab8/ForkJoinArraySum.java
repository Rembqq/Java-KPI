package org.example.lab8;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinArraySum {
    private static final int THRESHOLD = 20; // Граничний розмір підмасиву

    public static void main(String[] args) {
        int[] array = new int[1_000_000];
        Random random = new Random();

        for(int i = 0; i < array.length; ++i) {
            array[i] = random.nextInt(101); // Випадкові числа в діапазоні [0, 100]
        }

        // Використання ForkJoinPool для обчислення суми
        ForkJoinPool pool = new ForkJoinPool();
        ArraySumTask task = new ArraySumTask(array, 0, array.length);

    }

    static class ArraySumTask extends RecursiveTask<Long> {
        private final int[] array;
        private final int start;
        private final int end;

        public ArraySumTask(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            int length = end - start;

            if(length <= THRESHOLD) {
                // Якщо підмасив малий, обчислюємо суму напряму
                long sum = 0;
                for(int i = start; i < end; i++) {
                    sum += array[i];
                }
                return sum;
            } else {
                // Розділяємо масив на дві частини
                int mid = (start + length) / 2;
                ArraySumTask leftTask = new ArraySumTask(array, start, mid);
                ArraySumTask rightTask = new ArraySumTask(array, mid, end);

                // Виконуємо завдання паралельно
                leftTask.fork();
                long rightResult = rightTask.compute();
                long leftResult = leftTask.join();

                // Об'єднуємо результати
                return leftResult + rightResult;
            }

        }
    }

}
