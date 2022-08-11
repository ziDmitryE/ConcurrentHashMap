import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private static final int ARRAY_CAPACITY = 100000;
    private static final int ARRAY_BOUND = 1000;
    private static final int POOLS_COUNT = 3;
    private static final int TIME_BREAK = 3000;

    public static void main(String[] args) throws InterruptedException {

        int[] array = getInitArray(ARRAY_CAPACITY);
        Example example = new Example();

        System.out.println("вариант с synchronizedMap");
        Map<Integer, Integer> map = Collections.synchronizedMap(new HashMap<>());
        final ExecutorService threadPool = Executors.newFixedThreadPool(POOLS_COUNT);
        threadPool.submit(() -> example.recording(map, array));
        threadPool.shutdown();

        //Даем время на решение первой задачи
        Thread.sleep(TIME_BREAK);

        System.out.println("вариант с ConcurrentHashMap");
        Map<Integer, Integer> map2 = new ConcurrentHashMap<>();
        final ExecutorService threadPool2 = Executors.newFixedThreadPool(POOLS_COUNT);
        threadPool2.submit(() -> example.recording(map2, array));
        threadPool2.shutdown();
    }

    public static int[] getInitArray(int capacity) {
        int[] array = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            array[i] = new Random().nextInt(ARRAY_BOUND);
        }
        return array;
    }
}
