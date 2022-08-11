import java.util.Map;

public class Example {

    public void recording(Map<Integer, Integer> map, int[] array) {
        int sum=0;
        long start = System.currentTimeMillis();
        for (int i = 0; i <= array.length - 1; i++) {
            map.put(i, array[i]);
        }
        for (Map.Entry<Integer, Integer> IntegerEntry : map.entrySet()) {
            sum += IntegerEntry.getValue();
        }
        System.out.println("Сумма = " + sum);
        System.out.println("решение заняло = " + (System.currentTimeMillis() - start) + " мсек\n");
    }
}
