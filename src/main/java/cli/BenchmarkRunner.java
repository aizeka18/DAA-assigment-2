package cli;

import algorithms.MaxHeap;
import java.util.Random;

public class BenchmarkRunner {

    public static void main(String[] args) {
        if (args.length < 1) {
            printUsage();
            return;
        }

        switch (args[0]) {
            case "benchmark":
                runBenchmark();
                break;
            case "test":
                testCorrectness();
                break;
            default:
                printUsage();
        }
    }

    private static void runBenchmark() {
        System.out.println("Running MaxHeap benchmark...");
        System.out.printf("%-12s %-12s%n", "Size", "Time(ns)");

        for (int size = 100; size <= 10000; size *= 2) {
            benchmarkSize(size);
        }
    }

    private static void benchmarkSize(int size) {
        Random random = new Random(42);
        MaxHeap heap = new MaxHeap(size);

        long startTime = System.nanoTime();

        for (int i = 0; i < size; i++) {
            heap.insert(random.nextInt(10000));
        }

        for (int i = 0; i < size; i++) {
            heap.extractMax();
        }

        long endTime = System.nanoTime();
        System.out.printf("%-12d %-12d%n", size, endTime - startTime);
    }

    private static void testCorrectness() {
        System.out.println("Testing MaxHeap correctness...");

        MaxHeap heap = new MaxHeap(10);
        heap.insert(5);
        heap.insert(3);
        heap.insert(8);

        assertEquals(8, heap.extractMax());
        assertEquals(5, heap.extractMax());
        assertEquals(3, heap.extractMax());

        System.out.println("All tests passed!");
    }

    private static void assertEquals(int expected, int actual) {
        if (expected != actual) {
            throw new AssertionError("Expected: " + expected + ", Got: " + actual);
        }
    }

    private static void printUsage() {
        System.out.println("Usage:");
        System.out.println("  benchmark  - Run performance benchmark");
        System.out.println("  test       - Test algorithm correctness");
    }
}