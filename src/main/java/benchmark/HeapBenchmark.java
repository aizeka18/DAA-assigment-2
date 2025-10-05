package benchmark;

import org.openjdk.jmh.annotations.*;
import algorithms.MaxHeap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
@Warmup(iterations = 2, time = 1)
@Measurement(iterations = 3, time = 1)
@Fork(1)
public class HeapBenchmark {

    private int[] testData;

    @Setup
    public void setup() {
        testData = new int[1000];
        Random random = new Random(42);
        for (int i = 0; i < testData.length; i++) {
            testData[i] = random.nextInt(10000);
        }
    }

    @Benchmark
    public void testInsertAndExtract() {
        MaxHeap heap = new MaxHeap(testData.length);
        for (int value : testData) {
            heap.insert(value);
        }
        while (heap.size() > 0) {
            heap.extractMax();
        }
    }
}