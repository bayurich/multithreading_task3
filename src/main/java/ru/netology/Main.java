package ru.netology;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {

        System.out.println("Создание заполненного массива целых чисел...");
        //на малых размерах, примерно до 10*6 разница в скорости незначительная, но однопоточный быстрее
        //на более больших размерах, чем больше размер, тем разница увеличивается (однопоточный быстрее)
        int[] array = createRandomArray(200000000);

        System.out.println("Однопоточный подсчет суммы элементов массива...");
        System.out.println("Сумма элементов: " + sumElements(array));

        System.out.println("Многопоточный подсчет суммы элементов массива...");
        System.out.println("Сумма элементов: " + sumElementsThreads(array));
    }

    private static int sumElementsThreads(int[] array) {
        Long timeStart = System.currentTimeMillis();

        ArraySumTask arraySumTask = new ArraySumTask(0,array.length, array);
        int result = new ForkJoinPool().invoke(arraySumTask);

        System.out.println("Время выполнения: " + (System.currentTimeMillis() - timeStart));
        return result;
    }

    private static int sumElements(int[] array) {
        Long timeStart = System.currentTimeMillis();
        int result = 0;
        for (int i=0; i < array.length; i++){
            result += array[i];
        }

        System.out.println("Время выполнения: " + (System.currentTimeMillis() - timeStart));
        return result;
    }

    private static int[] createRandomArray(int count) {
        int[] array = new int[count];
        Random random = new Random();
        for (int i=0; i < array.length; i++){
            array[i] = random.nextInt(50);
        }

        System.out.println("Создан массив из " + count + " элементов:");
        //System.out.println(Arrays.toString(array));

        return array;
    }
}
