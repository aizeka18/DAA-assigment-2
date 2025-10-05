# Max-Heap Implementation

Complete Max-Heap implementation with performance tracking and benchmarking.

## Features

- Max-Heap operations: insert, extract-max, get-max
- Performance metrics tracking
- Comprehensive test suite
- Benchmarking CLI

## Complexity Analysis

| Operation | Time Complexity | Space Complexity |
|-----------|----------------|------------------|
| Insert | O(log n) | O(1) |
| Extract Max | O(log n) | O(1) |
| Get Max | O(1) | O(1) |
| Build Heap | O(n) | O(n) |

## Usage

```bash
# Run benchmarks
mvn exec:java -Dexec.mainClass="cli.BenchmarkRunner" -Dexec.args="benchmark"

# Run tests
mvn exec:java -Dexec.mainClass="cli.BenchmarkRunner" -Dexec.args="test"
mvn test