package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

public class EdgeCaseTest {

    @Test
    void testExtractFromSingleElementHeap() {
        MaxHeap heap = new MaxHeap(1);
        heap.insert(100);

        assertEquals(100, heap.extractMax());
        assertTrue(heap.isEmpty());

        assertThrows(NoSuchElementException.class, () -> heap.extractMax());
    }

    @Test
    void testMultipleInsertExtractCycles() {
        MaxHeap heap = new MaxHeap(5);

        // First cycle
        heap.insert(10);
        heap.insert(20);
        assertEquals(20, heap.extractMax());
        assertEquals(10, heap.extractMax());

        // Second cycle
        heap.insert(15);
        heap.insert(5);
        assertEquals(15, heap.extractMax());
        assertEquals(5, heap.extractMax());
    }

    @Test
    void testLargeNumbers() {
        MaxHeap heap = new MaxHeap(3);
        heap.insert(Integer.MAX_VALUE);
        heap.insert(Integer.MIN_VALUE);
        heap.insert(0);

        assertEquals(Integer.MAX_VALUE, heap.extractMax());
        assertEquals(0, heap.extractMax());
        assertEquals(Integer.MIN_VALUE, heap.extractMax());
    }
}