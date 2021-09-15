//Ben B, Ben G

import java.util.Arrays;

// findings from count comparisons
// Random ordering of elements:
// Run 1
// Counter for mergesort: 120346
// Counter for quicksort: 157680
// Run 2
// Counter for mergesort: 120487
// Counter for quicksort: 148904
// Run 3
// Counter for mergesort: 120303
// Counter for quicksort: 150853
// Run 4
// Counter for mergesort: 120355
// Counter for quicksort: 149532
// Run 5
// Counter for mergesort: 120386
// Counter for quicksort: 159868

// Increasing order of elements:
// Run 1
// Counter for mergesort: 9999
// Counter for quicksort: 49995000
// Run 2
// Counter for mergesort: 9999
// Counter for quicksort: 49995000
// Run 3
// Counter for mergesort: 9999
// Counter for quicksort: 49995000
// Run 4
// Counter for mergesort: 9999
// Counter for quicksort: 49995000
// Run 5
// Counter for mergesort: 9999
// Counter for quicksort: 49995000

// Increasing sequences of elements:
// Run 1
// Counter for mergesort: 10323
// Counter for quicksort: 15005000
// Run 2
// Counter for mergesort: 10320
// Counter for quicksort: 11011000
// Run 3
// Counter for mergesort: 10231
// Counter for quicksort: 13006000
// Run 4
// Counter for mergesort: 10279
// Counter for quicksort: 10015000
// Run 5
// Counter for mergesort: 10261
// Counter for quicksort: 13014000

// Decreasing sequences of elements:
// Run 1
// Counter for mergesort: 10211
// Counter for quicksort: 26003000
// Run 2
// Counter for mergesort: 10313
// Counter for quicksort: 12013000
// Run 3
// Counter for mergesort: 10199
// Counter for quicksort: 12011000
// Run 4
// Counter for mergesort: 10249
// Counter for quicksort: 9018000
// Run 5
// Counter for mergesort: 10232
// Counter for quicksort: 11018000

public class SortComparison {

    public static void main(String[] args) {
        SortComparison sort = new SortComparison();
        sort.runComparisons();
    }

    protected static class TestInteger implements Comparable<TestInteger> {
        private static long counter = 0;

        public final int value;

        public TestInteger(int value) {
            this.value = value;
        }

        public static long getCounter() {
            return counter;
        }

        public static void resetCounter() {
            counter = 0;
        }

        public int compareTo(TestInteger that) {
            counter++;
            return Integer.compare(this.value, that.value);
        }

    }

    private void fillArrayIncreasing(TestInteger[] toFill) {
        for (int i = 0; i < toFill.length; i++) {
            toFill[i] = new TestInteger(i);
        }
    }

    private void fillArrayRandom(TestInteger[] toFill) {
        fillArrayRandom(toFill, 1000000);
    }

    private void fillArrayRandom(TestInteger[] toFill, int upperBound) {
        for (int i = 0; i < toFill.length; i++) {
            toFill[i] = new TestInteger((int) ((java.lang.Math.random() * (upperBound - 1)) + 1));
        }
    }

    private void fillArrayRangeIncreasing(TestInteger[] toFill, int startPosition, int endPosition) {
        int baseRandom = ((int) ((java.lang.Math.random() * 999989) + 1));
        for (int i = 0; i <= endPosition - startPosition; i++) {
            toFill[i + startPosition] = new TestInteger(baseRandom + i);
        }
    }

    private void fillArrayRangeDecreasing(TestInteger[] toFill, int startPosition, int endPosition) {
        Integer baseRandom = ((int) (java.lang.Math.random() * 999988));
        for (int i = 0; i <= endPosition - startPosition; i++) {
            toFill[startPosition + i] = new TestInteger(1000000 - (baseRandom + i));
        }
    }

    private boolean isSorted(TestInteger[] toCheck) {
        for (int i = 0; i < toCheck.length - 1; i++) {
            if (Integer.compare(toCheck[i].value, toCheck[i + 1].value) > 0) {
                return false;
            }
        }
        return true;
    }

    public void Quicksort(TestInteger[] A, int p, int r) {
        if (p < r) {
            int q = Partition(A, p, r);
            Quicksort(A, p, q - 1);
            Quicksort(A, q + 1, r);
        }
    }

    public int Partition(TestInteger[] A, int p, int r) {
        TestInteger x = A[r];
        int i = p - 1;
        for (int j = p; j < r; j++) {
            if (A[j].compareTo(x) <= 0) {
                i = i + 1;
                exchange(A, i, j);
            }
        }
        exchange(A, i + 1, r);
        return i + 1;
    }

    public void RandomizedQuicksort(TestInteger[] A, int p, int r) {
        if (p < r) {
            int q = RandomizedPartition(A, p, r);
            RandomizedQuicksort(A, p, q - 1);
            RandomizedQuicksort(A, q + 1, r);
        }
    }

    public int RandomizedPartition(TestInteger[] A, int p, int r) {
        int i = (int) (Math.random() * (r - p) + p);
        exchange(A, r, i);
        return Partition(A, p, r);
    }

    public void MedianQuicksort(TestInteger[] A, int p, int r) {
        if (p < r) {
            int q = MedianPartition(A, p, r);
            MedianQuicksort(A, p, q - 1);
            MedianQuicksort(A, q + 1, r);
        }
    }

    public int MedianPartition(TestInteger[] A, int p, int r) {
        TestInteger[] potentialPivotIndices = new TestInteger[3];
        for (int i = 0; i < potentialPivotIndices.length; i++) {
            potentialPivotIndices[i] = new TestInteger((int) (Math.random() * (r - p) + p));
        }
        exchange(A, r, sortPivotArray(potentialPivotIndices[0], potentialPivotIndices[1], potentialPivotIndices[2]).value);
        return Partition(A, p, r);
    }

    // returns median of three given TestIntegers
    public TestInteger sortPivotArray(TestInteger a, TestInteger b, TestInteger c) {
        if (a.compareTo(b) >= 0 && b.compareTo(c) >= 0) { // a >= b && b >= c
            return b;
        } else if (b.compareTo(a) >= 0 && a.compareTo(c) >= 0) { // b >= a && a >= c
            return a;
        } else
            return c;
    }

    private void exchange(TestInteger[] A, int p, int q) {
        TestInteger storage = A[p];
        A[p] = A[q];
        A[q] = storage;
    }

    // Comparisons

    public void runComparisons() {
        System.out.println("Random ordering of elements:");
        for (int i = 0; i < 5; i++) {
            System.out.println("Run " + (i + 1));
            randomSortComparison();
        }
        System.out.println("Increasing order of elements:");
        for (int i = 0; i < 5; i++) {
            System.out.println("Run " + (i + 1));
            increasingSortComparison();
        }
        System.out.println("Increasing sequences of elements:");
        for (int i = 0; i < 5; i++) {
            System.out.println("Run " + (i + 1));
            subsequenceIncreasingSortComparison();
        }
        System.out.println("Decreasing sequences of elements:");
        for (int i = 0; i < 5; i++) {
            System.out.println("Run " + (i + 1));
            subsequenceDecreasingSortComparison();
        }
    }

    private void randomSortComparison() {
        TestInteger[] toMergeSort = new TestInteger[10000];
        fillArrayRandom(toMergeSort);
        TestInteger[] toQuickSort = Arrays.copyOf(toMergeSort, toMergeSort.length);
        TestInteger[] toRandomizedQuickSort = Arrays.copyOf(toMergeSort, toMergeSort.length);
        TestInteger[] toMedianQuicksort = Arrays.copyOf(toMergeSort, toMergeSort.length);
        // TestInteger[] toInsertionEndSort = Arrays.copyOf(toMergeSort,
        // toMergeSort.length);
        // TestInteger[] toExtraCreditSort = Arrays.copyOf(toMergeSort,
        // toMergeSort.length);
        compareSorting(toMergeSort, toQuickSort, toRandomizedQuickSort, toMedianQuicksort);
    }

    private void increasingSortComparison() {
        TestInteger[] toMergeSort = new TestInteger[10000];
        fillArrayIncreasing(toMergeSort);
        TestInteger[] toQuickSort = Arrays.copyOf(toMergeSort, toMergeSort.length);
        TestInteger[] toRandomizedQuickSort = Arrays.copyOf(toMergeSort, toMergeSort.length);
        TestInteger[] toMedianQuicksort = Arrays.copyOf(toMergeSort, toMergeSort.length);
        compareSorting(toMergeSort, toQuickSort, toRandomizedQuickSort, toMedianQuicksort);
    }

    private void subsequenceIncreasingSortComparison() {
        TestInteger[] toMergeSort = new TestInteger[10000];
        for (int i = 0; i < 10000; i += 1000) {
            fillArrayRangeIncreasing(toMergeSort, i, i + 999);
        }
        TestInteger[] toQuickSort = Arrays.copyOf(toMergeSort, toMergeSort.length);
        TestInteger[] toRandomizedQuickSort = Arrays.copyOf(toMergeSort, toMergeSort.length);
        TestInteger[] toMedianQuicksort = Arrays.copyOf(toMergeSort, toMergeSort.length);
        compareSorting(toMergeSort, toQuickSort, toRandomizedQuickSort, toMedianQuicksort);
    }

    private void subsequenceDecreasingSortComparison() {
        TestInteger[] toMergeSort = new TestInteger[10000];
        for (int i = 0; i < 10000; i += 1000) {
            fillArrayRangeDecreasing(toMergeSort, i, i + 999);
        }
        TestInteger[] toQuickSort = Arrays.copyOf(toMergeSort, toMergeSort.length);
        TestInteger[] toRandomizedQuickSort = Arrays.copyOf(toMergeSort, toMergeSort.length);
        TestInteger[] toMedianQuicksort = Arrays.copyOf(toMergeSort, toMergeSort.length);
        compareSorting(toMergeSort, toQuickSort, toRandomizedQuickSort, toMedianQuicksort);
    }

    private void compareSorting(TestInteger[] toMergeSort, TestInteger[] toQuickSort,
            TestInteger[] toRandomizedQuicksort, TestInteger[] toMedianQuicksort) {
        Arrays.sort(toMergeSort);
        if (isSorted(toMergeSort)) {
            System.out.println("Counter for mergesort: " + TestInteger.getCounter());
        }
        TestInteger.resetCounter();

        Quicksort(toQuickSort, 0, toQuickSort.length - 1);
        if (isSorted(toQuickSort)) {
            System.out.println("Counter for quicksort: " + TestInteger.getCounter());
        }
        TestInteger.resetCounter();

        RandomizedQuicksort(toRandomizedQuicksort, 0, toRandomizedQuicksort.length - 1);
        if (isSorted(toRandomizedQuicksort)) {
            System.out.println("Counter for randomized quicksort: " + TestInteger.getCounter());
        }
        TestInteger.resetCounter();

        MedianQuicksort(toMedianQuicksort, 0, toMedianQuicksort.length - 1);
        if (isSorted(toMedianQuicksort)) {
            System.out.println("Counter for median quicksort: " + TestInteger.getCounter());
        }
    }

}