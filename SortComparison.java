//Ben B, Ben G

import java.util.Arrays;

// findings from count comparisons

// Random ordering of elements:
// Run 1
// Counter for mergesort: 120429
// Counter for quicksort: 163255
// Counter for randomized quicksort: 149340
// Counter for median quicksort: 169196
// Run 2
// Counter for mergesort: 120407
// Counter for quicksort: 147780
// Counter for randomized quicksort: 164674
// Counter for median quicksort: 173658
// Run 3
// Counter for mergesort: 120391
// Counter for quicksort: 162016
// Counter for randomized quicksort: 143712
// Counter for median quicksort: 168393
// Run 4
// Counter for mergesort: 120332
// Counter for quicksort: 160432
// Counter for randomized quicksort: 163897
// Counter for median quicksort: 165613
// Run 5
// Counter for mergesort: 120363
// Counter for quicksort: 159253
// Counter for randomized quicksort: 154131
// Counter for median quicksort: 171310
// Increasing order of elements:
// Run 1
// Counter for mergesort: 9999
// Counter for quicksort: 49995000
// Counter for randomized quicksort: 156789
// Counter for median quicksort: 157197
// Run 2
// Counter for mergesort: 9999
// Counter for quicksort: 49995000
// Counter for randomized quicksort: 158173
// Counter for median quicksort: 159872
// Run 3
// Counter for mergesort: 9999
// Counter for quicksort: 49995000
// Counter for randomized quicksort: 151532
// Counter for median quicksort: 163218
// Run 4
// Counter for mergesort: 9999
// Counter for quicksort: 49995000
// Counter for randomized quicksort: 149052
// Counter for median quicksort: 162314
// Run 5
// Counter for mergesort: 9999
// Counter for quicksort: 49995000
// Counter for randomized quicksort: 152741
// Counter for median quicksort: 160945
// Increasing sequences of elements:
// Run 1
// Counter for mergesort: 10246
// Counter for quicksort: 17004000
// Counter for randomized quicksort: 157617
// Counter for median quicksort: 157925
// Run 2
// Counter for mergesort: 10357
// Counter for quicksort: 14013000
// Counter for randomized quicksort: 154196
// Counter for median quicksort: 170168
// Run 3
// Counter for mergesort: 10328
// Counter for quicksort: 16011000
// Counter for randomized quicksort: 158878
// Counter for median quicksort: 167280
// Run 4
// Counter for mergesort: 10260
// Counter for quicksort: 18004000
// Counter for randomized quicksort: 146558
// Counter for median quicksort: 175694
// Run 5
// Counter for mergesort: 10200
// Counter for quicksort: 13012000
// Counter for randomized quicksort: 164578
// Counter for median quicksort: 169676
// Decreasing sequences of elements:
// Run 1
// Counter for mergesort: 10297
// Counter for quicksort: 10010000
// Counter for randomized quicksort: 156028
// Counter for median quicksort: 165028
// Run 2
// Counter for mergesort: 10239
// Counter for quicksort: 16007000
// Counter for randomized quicksort: 171002
// Counter for median quicksort: 176159
// Run 3
// Counter for mergesort: 10350
// Counter for quicksort: 7014000
// Counter for randomized quicksort: 162907
// Counter for median quicksort: 164813
// Run 4
// Counter for mergesort: 10306
// Counter for quicksort: 21014000
// Counter for randomized quicksort: 152698
// Counter for median quicksort: 165209
// Run 5
// Counter for mergesort: 10362
// Counter for quicksort: 15007000
// Counter for randomized quicksort: 155898
// Counter for median quicksort: 183872


// approximating best k value compared to randomquicksort (20 trials) ->
// testing k = 50 -> median bettter 1 time, 4 times, 2 times, 3 times, 2 times -> avg = 2.4
// testing k = 75 -> median better 3 times, 2 times, 2 times, 1 time, 2 times -> avg = 2
// testing k = 100 -> median better 5 times, 3 times, 2 times, 2 times, 1 time -> avg = 2.6
// testing k = 115 -> median better 1 time, 2 times, 0 times, 2 times, 2 times -> avg = 1.4
// testing k = 120 -> median better 1 time, 3 times, 1 time, 0 times, 5 times -> avg = 2
// testing k = 123 -> median better 1 time, 0 times, 3 times, 2 times, 6 times -> 2.4

// testing k = 125 -> median better 4 times, 2 times, 6 times, 3 times, 3 times -> avg = 3.6

// testing k = 127 -> median better 2 times, 3 times, 4 times, 5 times, 2 times -> avg = 3.2
// testing k = 130 -> median better 5 times, 6 times, 1 time, 2 times, 1 time -> avg = 3
// testing k = 135 -> median better 2 times, 2 times, 2 times, 3 times, 3 times -> avg = 2.4
// testing k = 150 -> median better 1 time, 3 times, 4 times, 4 times, 1 time -> avg = 2.6
// testing k = 175 -> median better 0 times, 4 times, 2 times, 1 time, 2 times -> avg = 1.8
// testing k = 250 -> median better 2 times, 2 times, 4 times, 1 time, 2 times -> avg = 2.2
// testing k = 500 -> median better 2 times, 1 time, 3 times, 4 times, 1 time -> avg = 2.2
// testing k = 1000 -> median better 1 time, 3 times, 4 times, 0 times , 1 time -> avg = 1.8 
// testing k = 1500 -> median better 2 times, 2 times, 3 times, 2 times, 3 times -> avg = 2.4
// testing k = 2000 -> median better 3 times, 1 time, 2 times, 2 times, 3 times -> avg = 2.2
// testing k = 2500 -> median better 0 times, 2 times, 2 times, 1 time, 2 times -> avg = 1.4

// testing k = 50 -> total counts:  3347540
// testing k = 100 -> total counts: 3370787

// k = 125 has lowest total counts of tested k values both relative to randomquicksort performance and total comparisons in general
// testing k = 125 -> total counts: 3324298

// testing k = 150 -> total counts: 3344073
// testing k = 500 -> total counts: 3343461
// testing k = 1000-> total counts: 3387802

public class SortComparison {
    public static int medianTotalCount = 0;
    public static int medianBetterCount = 0;
    public static int randomBetterCount = 0;
    public static int k = 50; // threshold value for swapping from median of three pivot to regular (last index) pivot

    public static void main(String[] args) {
        SortComparison sort = new SortComparison();
        sort.runComparisons();

        // logging info to help determine good k value
        // System.out.println("total count for medianquicksort: " + medianTotalCount);
        // System.out.println("random was better " + randomBetterCount + " time(s), while median was better " + medianBetterCount + " time(s).");
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
            if (A.length < k) // length less than k -> back to regular partition -> optimal k seems to be ~125
            {
                int q = Partition(A, p, r);
                Quicksort(A, p, q - 1);
                Quicksort(A, q + 1, r);
            } else {
                int q = MedianPartition(A, p, r);
                MedianQuicksort(A, p, q - 1);
                MedianQuicksort(A, q + 1, r);
            }
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
        System.out.println("Counter for mergesort: " + TestInteger.getCounter());
        TestInteger.resetCounter();

        Quicksort(toQuickSort, 0, toQuickSort.length - 1);
        System.out.println("Counter for quicksort: " + TestInteger.getCounter());
        TestInteger.resetCounter();

        RandomizedQuicksort(toRandomizedQuicksort, 0, toRandomizedQuicksort.length - 1);
        System.out.println("Counter for randomized quicksort: " + TestInteger.getCounter());
        long randomCount = TestInteger.getCounter();
        TestInteger.resetCounter();

        MedianQuicksort(toMedianQuicksort, 0, toMedianQuicksort.length - 1);
        System.out.println("Counter for median quicksort: " + TestInteger.getCounter());
        long medianCount = TestInteger.getCounter(); 
        TestInteger.resetCounter();

        medianTotalCount += medianCount;

        checkQuickSortPerformance(medianCount, randomCount);

    }

    //testing which quicksort modification is working better for the most cases
    private void checkQuickSortPerformance(long medianCount, long randomCount)
    {
        if (medianCount <= randomCount)
        {
            medianBetterCount++;
        }
        else randomBetterCount++;
    }

}