// ЗАМЕНИТЕ содержимое MaxHeap.java на эту оптимизированную версию:

package algorithms;

import metrics.PerformanceTracker;
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Optimized Max-Heap implementation with iterative heapify
 */
public class MaxHeap {
    private int[] heap;
    private int size;
    private int capacity;
    private final PerformanceTracker tracker;

    public MaxHeap(int capacity) {
        this.capacity = capacity;
        this.heap = new int[capacity];
        this.size = 0;
        this.tracker = new PerformanceTracker("MaxHeap");
    }

    public MaxHeap(int[] array) {
        this.capacity = array.length;
        this.size = array.length;
        this.heap = Arrays.copyOf(array, array.length);
        this.tracker = new PerformanceTracker("MaxHeap");
        buildHeap();
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int leftChild(int i) {
        return 2 * i + 1;
    }

    private int rightChild(int i) {
        return 2 * i + 2;
    }

    private void swap(int i, int j) {
        tracker.recordSwap();
        tracker.recordArrayAccess(2);
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    /**
     * OPTIMIZATION: Iterative heapifyDown
     */
    private void heapifyDown(int i) {
        int current = i;
        while (true) {
            int largest = current;
            int left = leftChild(current);
            int right = rightChild(current);

            if (left < size) {
                tracker.recordComparison();
                tracker.recordArrayAccess(2);
                if (heap[left] > heap[largest]) {
                    largest = left;
                }
            }

            if (right < size) {
                tracker.recordComparison();
                tracker.recordArrayAccess(2);
                if (heap[right] > heap[largest]) {
                    largest = right;
                }
            }

            if (largest == current) break;

            swap(current, largest);
            current = largest;
        }
    }

    /**
     * OPTIMIZATION: Iterative heapifyUp
     */
    private void heapifyUp(int i) {
        int current = i;
        while (current > 0) {
            int parent = parent(current);
            tracker.recordComparison();
            tracker.recordArrayAccess(2);
            if (heap[parent] >= heap[current]) {
                break;
            }
            swap(current, parent);
            current = parent;
        }
    }

    /**
     * OPTIMIZATION: Efficient buildHeap
     */
    private void buildHeap() {
        for (int i = (size / 2) - 1; i >= 0; i--) {
            heapifyDown(i);
        }
    }

    // Остальные методы остаются без изменений
    public void insert(int key) {
        if (size == capacity) {
            resize();
        }

        tracker.recordArrayAccess(1);
        heap[size] = key;
        size++;
        heapifyUp(size - 1);
    }

    private void resize() {
        capacity *= 2;
        heap = Arrays.copyOf(heap, capacity);
    }

    public int extractMax() {
        if (size == 0) {
            throw new NoSuchElementException("Heap is empty");
        }

        tracker.recordArrayAccess(1);
        int max = heap[0];

        if (size > 1) {
            tracker.recordArrayAccess(2);
            heap[0] = heap[size - 1];
            size--;
            heapifyDown(0);
        } else {
            size--;
        }

        return max;
    }

    public int getMax() {
        if (size == 0) {
            throw new NoSuchElementException("Heap is empty");
        }
        tracker.recordArrayAccess(1);
        return heap[0];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public PerformanceTracker getPerformanceTracker() {
        return tracker;
    }

    public void resetMetrics() {
        tracker.reset();
    }
}