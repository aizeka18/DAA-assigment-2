package algorithms;

import metrics.PerformanceTracker;
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Baseline Max-Heap implementation
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

    private void heapifyDown(int i) {
        int largest = i;
        int left = leftChild(i);
        int right = rightChild(i);

        tracker.recordComparison();
        if (left < size && heap[left] > heap[largest]) {
            largest = left;
        }

        tracker.recordComparison();
        if (right < size && heap[right] > heap[largest]) {
            largest = right;
        }

        if (largest != i) {
            swap(i, largest);
            heapifyDown(largest);
        }
    }

    private void heapifyUp(int i) {
        while (i > 0) {
            int parent = parent(i);
            tracker.recordComparison();
            if (heap[parent] >= heap[i]) {
                break;
            }
            swap(i, parent);
            i = parent;
        }
    }

    public void insert(int key) {
        if (size == capacity) {
            resize();
        }

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

        int max = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown(0);

        return max;
    }

    public int getMax() {
        if (size == 0) {
            throw new NoSuchElementException("Heap is empty");
        }
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
}