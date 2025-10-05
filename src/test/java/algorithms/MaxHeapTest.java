package algorithms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

class MaxHeapTest {
    private MaxHeap heap;

    @BeforeEach
    void setUp() {
        heap = new MaxHeap(10);
    }

    @Test
    void testEmptyHeap() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        assertThrows(NoSuchElementException.class, () -> heap.getMax());
    }

    @Test
    void testSingleElement() {
        heap.insert(42);
        assertFalse(heap.isEmpty());
        assertEquals(1, heap.size());
        assertEquals(42, heap.getMax());
    }

    @Test
    void testInsertAndExtract() {
        heap.insert(10);
        heap.insert(30);
        heap.insert(20);

        assertEquals(30, heap.extractMax());
        assertEquals(20, heap.extractMax());
        assertEquals(10, heap.extractMax());
        assertTrue(heap.isEmpty());
    }

    @Test
    void testResize() {
        MaxHeap smallHeap = new MaxHeap(2);
        smallHeap.insert(1);
        smallHeap.insert(2);
        smallHeap.insert(3);

        assertEquals(3, smallHeap.size());
        assertEquals(3, smallHeap.extractMax());
    }
}