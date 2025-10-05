package metrics;

import java.util.HashMap;
import java.util.Map;

/**
 * Basic performance metrics tracker
 */
public class PerformanceTracker {
    private final String algorithmName;
    private final Map<String, Long> metrics;
    private long startTime;

    public PerformanceTracker(String algorithmName) {
        this.algorithmName = algorithmName;
        this.metrics = new HashMap<>();
        reset();
    }

    public void recordComparison() {
        metrics.put("comparisons", metrics.get("comparisons") + 1);
    }

    public void recordSwap() {
        metrics.put("swaps", metrics.get("swaps") + 1);
    }

    public void recordArrayAccess() {
        metrics.put("arrayAccesses", metrics.get("arrayAccesses") + 1);
    }

    public void recordArrayAccess(int count) {
        metrics.put("arrayAccesses", metrics.get("arrayAccesses") + count);
    }

    public void startTimer() {
        startTime = System.nanoTime();
    }

    public void stopTimer() {
        long duration = System.nanoTime() - startTime;
        metrics.put("executionTime", metrics.get("executionTime") + duration);
    }

    public void reset() {
        metrics.put("comparisons", 0L);
        metrics.put("swaps", 0L);
        metrics.put("arrayAccesses", 0L);
        metrics.put("executionTime", 0L);
    }

    public Map<String, Long> getMetrics() {
        return new HashMap<>(metrics);
    }

    public void printMetrics() {
        System.out.println("=== " + algorithmName + " Performance Metrics ===");
        metrics.forEach((key, value) ->
                System.out.printf("%s: %,d%n", key, value));
    }
}