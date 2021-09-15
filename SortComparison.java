//Ben B, Ben G

// example run through output at bottom of file
// google sheets attached was used for making conclusions

import java.util.Arrays;

// findings from count comparisons k = 20 seems to give the least comparisons for median of three quicksort across the different arrangements of elements
// these are total counts across all different initial ordering of elements

// testing k = 10  -> 27299461
// testing k = 15  -> 20706911
// testing k = 18  -> 15555951
// testing k = 19  -> 7171142

// testing k = 20  -> 6678377

// testing k = 21  -> 10104323
// testing k = 22  -> 11335322
// testing k = 25  -> 10198180
// testing k = 50  -> 22012399
// testing k = 100 -> 38797913
// test k = 500    -> 131391955

// finding best sub-array length for swapping to insertion sort (comparing total comparisons across all 20 runs of the different arrangements of elements)
// best length for sub-arrays where we need to swap to insertionsort seems to be length < 50 (although you'd probably get much better results at around 11 for anything that isn't already sorted)

// threshold = 2 -> 163557160 less comparisons than regular quicksort
// threshold = 5 -> 182974759 less comparisons than regular quicksort
// threshold = 10-> 184949855 less comparisons than regular quicksort

// threshold = 11-> 190821526 less comparisons than regular quicksort

// threshold = 12-> 182410551 less comparisons than regular quicksort
// threshold = 15-> 161089682 less comparisons than regular quicksort
// threshold = 40-> 183857133 less comparisons than regular quicksort
// threshold = 50-> 244279986 less comparisons than regular quicksort
// threshold = 60-> 168391059 less comparisons than regular quicksort
// threshold = 75-> 205131784 less comparisons than regular quicksort
// threshold =100-> 189053186 less comparisons than regular quicksort
// threshold =500-> 189462675 less comparisons than regular quicksort

// All your conclusions and observations:
// How do different versions of sorting act differently? 
// How do different kinds of data affect each kind of the sorting? 
/*
    Having different sets of data can affect the performance of the sorting algos in different ways.
    You get way more comparisons for things quicksort and its variations (except when swapped to insertionsort) with already sorted data, while
    Randomized and median quicksort suffer considerably less in that worst case performance. Further, it seems as though InsertionSwapSort's performance being better
    for total counts is entirely carried by its great performance in a sorted array. It would likely do better for any ONE random case if the threshold for swapping
    to insertion sort was at around 11. Mergesort performs the best out of all of them (in terms of comparisons) no matter what. This is presumably because they've
    optimized their algorithm(s) for many different potential array inputs.

    Performance with different sorted elements
    Random rankings: 
    MergeSort, MedianQuickSort, RandomizedQuickSort, QuickSort, InsertionSwapSort
    IncreasingSorted rankings:
    MergeSort, InsertionSwapSort, RandomizedQuicksort, MedianQuicksort, QuickSort
    IncreasingSequences rankings:
    MergeSort, RandomizedQuicksort, MedianQuicksort, QuickSort, InsertionSwapSort
    DecreasingSequences rankings:
    MergeSort, RandomizedMergeSort, MedianQuicksort, QuickSort, InsertionSwapSort
*/


// Which improvements of quicksort performed better? If you can provide an explanation, please do, but it's not required.  
/*
    The rankings already show this, but every variation of quicksort performed better than the original across all 20 runs with the 4 different sets of data.
    That being said, InsertionSwapSort is pretty terrible (actually worse than quicksort) for anything that isn't already sorted. Likely because it was optimized for the total rather than any
    one random case, and the already sorted array really boosts the performance when you swap to insertion sort early. (as that's its best case)
*/


public class SortComparison {
    // logging variables to help determine performance
    public static long mergeTotalCount = 0;
    public static long randomTotalCount = 0;
    public static long medianTotalCount = 0;
    public static long insertEndTotalCount = 0;
    public static long quickTotalCount = 0;
    
    // threshold value for swapping from median of three pivot to regular
    // (lastindex) pivot
    public static int k = 20;
    public static int insertionSortThreshold = 50;

    public static void main(String[] args) {
        SortComparison sort = new SortComparison();
        sort.runComparisons();
        System.out.println( '\n' + "logging totals for the different sorting methods" + '\n');

        System.out.println("total count for mergesort:       " + mergeTotalCount);
        // logging info to help determine good k value
        System.out.println("total count for medianquicksort: " + medianTotalCount);
        System.out.println("total count for randomQuickSort: " + randomTotalCount);
        // logging info to help determine subarray length for insertion sort swapping
        System.out.println("total count for swapsort:        " + insertEndTotalCount);
        System.out.println("total count quicksort:           " + quickTotalCount);
        System.out.println(
                "is the insertionEnd swap making it take less comparisons? " + (insertEndTotalCount < quickTotalCount)
                        + ". insertEndTotalCount - quickTotalCount = " + (insertEndTotalCount - quickTotalCount));
        System.out.println("division: " + insertEndTotalCount / quickTotalCount);
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

    private void Quicksort(TestInteger[] A, int p, int r) {
        if (p < r) {
            int q = Partition(A, p, r);
            Quicksort(A, p, q - 1);
            Quicksort(A, q + 1, r);
        }
    }

    private int Partition(TestInteger[] A, int p, int r) {
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

    private void RandomizedQuicksort(TestInteger[] A, int p, int r) {
        if (p < r) {
            int q = RandomizedPartition(A, p, r);
            RandomizedQuicksort(A, p, q - 1);
            RandomizedQuicksort(A, q + 1, r);
        }
    }

    private int RandomizedPartition(TestInteger[] A, int p, int r) {
        int i = (int) (Math.random() * (r - p) + p);
        exchange(A, r, i);
        return Partition(A, p, r);
    }

    private void MedianQuicksort(TestInteger[] A, int p, int r) {
        if (p < r) {
            int q = MedianPartition(A, p, r);
            if ((r - q) < k) {
                Quicksort(A, p, q - 1);
                Quicksort(A, q + 1, r);
            } else {
                MedianQuicksort(A, p, q - 1);
                MedianQuicksort(A, q + 1, r);
            }
        }
    }

    private int MedianPartition(TestInteger[] A, int p, int r) {
        TestInteger[] potentialPivotIndices = new TestInteger[3];
        for (int i = 0; i < potentialPivotIndices.length; i++) {
            potentialPivotIndices[i] = new TestInteger((int) (Math.random() * (r - p) + p));
        }
        exchange(A, r,
                sortPivotArray(potentialPivotIndices[0], potentialPivotIndices[1], potentialPivotIndices[2]).value);
        return Partition(A, p, r);
    }

    private void QuickToInsertionSort(TestInteger[] A, int p, int r) {
        if (p < r) {
            int q = Partition(A, p, r);
            if ((r - q) < insertionSortThreshold) {
                InsertionSort(A);
            } else {
                QuickToInsertionSort(A, p, q - 1);
                QuickToInsertionSort(A, q + 1, r);
            }
        }
    }

    private void InsertionSort(TestInteger[] toSort) {
        for (int j = 1; j < toSort.length; j++) {
            TestInteger key = toSort[j];
            int i = j - 1;
            while (i >= 0 && toSort[i].compareTo(key) > 0) {
                toSort[i + 1] = toSort[i];
                i = i - 1;
            }
            toSort[i + 1] = key;
        }
    }

    // returns median of three given TestIntegers
    private TestInteger sortPivotArray(TestInteger a, TestInteger b, TestInteger c) {
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

    private void runComparisons() {
        System.out.println("\nRandom ordering of elements:");
        for (int i = 0; i < 5; i++) {
            System.out.println("Run " + (i + 1));
            randomSortComparison();
        }
        System.out.println("\nIncreasing order of elements:");
        for (int i = 0; i < 5; i++) {
            System.out.println("Run " + (i + 1));
            increasingSortComparison();
        }
        System.out.println("\nIncreasing sequences of elements:");
        for (int i = 0; i < 5; i++) {
            System.out.println("Run " + (i + 1));
            subsequenceIncreasingSortComparison();
        }
        System.out.println("\nDecreasing sequences of elements:");
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
        TestInteger[] toInsertionEndSort = Arrays.copyOf(toMergeSort, toMergeSort.length);
        // TestInteger[] toExtraCreditSort = Arrays.copyOf(toMergeSort,
        // toMergeSort.length);
        compareSorting(toMergeSort, toQuickSort, toRandomizedQuickSort, toMedianQuicksort, toInsertionEndSort);
    }

    private void increasingSortComparison() {
        TestInteger[] toMergeSort = new TestInteger[10000];
        fillArrayIncreasing(toMergeSort);
        TestInteger[] toQuickSort = Arrays.copyOf(toMergeSort, toMergeSort.length);
        TestInteger[] toRandomizedQuickSort = Arrays.copyOf(toMergeSort, toMergeSort.length);
        TestInteger[] toMedianQuicksort = Arrays.copyOf(toMergeSort, toMergeSort.length);
        TestInteger[] toInsertionEndSort = Arrays.copyOf(toMergeSort, toMergeSort.length);
        compareSorting(toMergeSort, toQuickSort, toRandomizedQuickSort, toMedianQuicksort, toInsertionEndSort);
    }

    private void subsequenceIncreasingSortComparison() {
        TestInteger[] toMergeSort = new TestInteger[10000];
        for (int i = 0; i < 10000; i += 1000) {
            fillArrayRangeIncreasing(toMergeSort, i, i + 999);
        }
        TestInteger[] toQuickSort = Arrays.copyOf(toMergeSort, toMergeSort.length);
        TestInteger[] toRandomizedQuickSort = Arrays.copyOf(toMergeSort, toMergeSort.length);
        TestInteger[] toMedianQuicksort = Arrays.copyOf(toMergeSort, toMergeSort.length);
        TestInteger[] toInsertionEndSort = Arrays.copyOf(toMergeSort, toMergeSort.length);
        compareSorting(toMergeSort, toQuickSort, toRandomizedQuickSort, toMedianQuicksort, toInsertionEndSort);
    }

    private void subsequenceDecreasingSortComparison() {
        TestInteger[] toMergeSort = new TestInteger[10000];
        for (int i = 0; i < 10000; i += 1000) {
            fillArrayRangeDecreasing(toMergeSort, i, i + 999);
        }
        TestInteger[] toQuickSort = Arrays.copyOf(toMergeSort, toMergeSort.length);
        TestInteger[] toRandomizedQuickSort = Arrays.copyOf(toMergeSort, toMergeSort.length);
        TestInteger[] toMedianQuicksort = Arrays.copyOf(toMergeSort, toMergeSort.length);
        TestInteger[] toInsertionEndSort = Arrays.copyOf(toMergeSort, toMergeSort.length);
        compareSorting(toMergeSort, toQuickSort, toRandomizedQuickSort, toMedianQuicksort, toInsertionEndSort);
    }

    private void compareSorting(TestInteger[] toMergeSort, TestInteger[] toQuickSort,
            TestInteger[] toRandomizedQuicksort, TestInteger[] toMedianQuicksort, TestInteger[] toInsertionEndSort) {
        Arrays.sort(toMergeSort);
        long mergeCount = TestInteger.getCounter();
        System.out.println("Counter for mergesort: " + TestInteger.getCounter());
        TestInteger.resetCounter();

        Quicksort(toQuickSort, 0, toQuickSort.length - 1);
        long quickCount = TestInteger.getCounter();
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

        QuickToInsertionSort(toInsertionEndSort, 0, toInsertionEndSort.length - 1);
        if (isSorted(toInsertionEndSort)) {
            System.out.println("Counter for insertion ending sort: " + TestInteger.getCounter());
        }
        long insertionEndCount = TestInteger.getCounter();
        TestInteger.resetCounter();

        mergeTotalCount += mergeCount;
        medianTotalCount += medianCount;
        insertEndTotalCount += insertionEndCount;
        quickTotalCount += quickCount;
        randomTotalCount += randomCount;

    }
}

// Random ordering of elements:
// Run 1
// Counter for mergesort: 120392
// Counter for quicksort: 158520
// Counter for randomized quicksort: 155121
// Counter for median quicksort: 147433
// Counter for insertion ending sort: 12768812
// Run 2
// Counter for mergesort: 120446
// Counter for quicksort: 155838
// Counter for randomized quicksort: 159798
// Counter for median quicksort: 152397
// Counter for insertion ending sort: 20284247
// Run 3
// Counter for mergesort: 120380
// Counter for quicksort: 164913
// Counter for randomized quicksort: 158889
// Counter for median quicksort: 162092
// Counter for insertion ending sort: 12027308
// Run 4
// Counter for mergesort: 120358
// Counter for quicksort: 157660
// Counter for randomized quicksort: 156563
// Counter for median quicksort: 147460
// Counter for insertion ending sort: 15691396
// Run 5
// Counter for mergesort: 120389
// Counter for quicksort: 147598
// Counter for randomized quicksort: 154460
// Counter for median quicksort: 152234
// Counter for insertion ending sort: 10302019

// Increasing order of elements:
// Run 1
// Counter for mergesort: 9999
// Counter for quicksort: 49995000
// Counter for randomized quicksort: 160370
// Counter for median quicksort: 306333
// Counter for insertion ending sort: 19998
// Run 2
// Counter for mergesort: 9999
// Counter for quicksort: 49995000
// Counter for randomized quicksort: 148959
// Counter for median quicksort: 576977
// Counter for insertion ending sort: 19998
// Run 3
// Counter for mergesort: 9999
// Counter for quicksort: 49995000
// Counter for randomized quicksort: 152688
// Counter for median quicksort: 610985
// Counter for insertion ending sort: 19998
// Run 4
// Counter for mergesort: 9999
// Counter for quicksort: 49995000
// Counter for randomized quicksort: 158079
// Counter for median quicksort: 561941
// Counter for insertion ending sort: 19998
// Run 5
// Counter for mergesort: 9999
// Counter for quicksort: 49995000
// Counter for randomized quicksort: 163485
// Counter for median quicksort: 1816371
// Counter for insertion ending sort: 19998

// Increasing sequences of elements:
// Run 1
// Counter for mergesort: 12016
// Counter for quicksort: 19595435
// Counter for randomized quicksort: 150009
// Counter for median quicksort: 280283
// Counter for insertion ending sort: 12667177
// Run 2
// Counter for mergesort: 10349
// Counter for quicksort: 19010000
// Counter for randomized quicksort: 159991
// Counter for median quicksort: 352956
// Counter for insertion ending sort: 18019995
// Run 3
// Counter for mergesort: 10190
// Counter for quicksort: 11010000
// Counter for randomized quicksort: 163001
// Counter for median quicksort: 525182
// Counter for insertion ending sort: 9039993
// Run 4
// Counter for mergesort: 10209
// Counter for quicksort: 20002000
// Counter for randomized quicksort: 158649
// Counter for median quicksort: 343137
// Counter for insertion ending sort: 12039994
// Run 5
// Counter for mergesort: 10216
// Counter for quicksort: 14007000
// Counter for randomized quicksort: 150512
// Counter for median quicksort: 608303
// Counter for insertion ending sort: 5039994

// Decreasing sequences of elements:
// Run 1
// Counter for mergesort: 10150
// Counter for quicksort: 13018000
// Counter for randomized quicksort: 174044
// Counter for median quicksort: 636304
// Counter for insertion ending sort: 18057996
// Run 2
// Counter for mergesort: 10216
// Counter for quicksort: 16006000
// Counter for randomized quicksort: 152233
// Counter for median quicksort: 255186
// Counter for insertion ending sort: 26033997
// Run 3
// Counter for mergesort: 10251
// Counter for quicksort: 9013000
// Counter for randomized quicksort: 154496
// Counter for median quicksort: 351375
// Counter for insertion ending sort: 11060995
// Run 4
// Counter for mergesort: 10228
// Counter for quicksort: 14015000
// Counter for randomized quicksort: 154114
// Counter for median quicksort: 678448
// Counter for insertion ending sort: 17032997
// Run 5
// Counter for mergesort: 11785
// Counter for quicksort: 9136125
// Counter for randomized quicksort: 160724
// Counter for median quicksort: 1012099
// Counter for insertion ending sort: 11352372

// logging totals for the different sorting methods

// total count for mergesort:       757570
// total count for medianquicksort: 9677496
// total count for randomQuickSort: 3146185
// total count for swapsort:        211519282
// total count quicksort:           395572089
