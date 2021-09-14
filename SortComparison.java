//Ben B, Ben G

import java.util.Arrays;

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

    private void fillArrayRepeated(TestInteger[] toFill){
        fillArrayRepeated(toFill, 1);
    }

    private void fillArrayRepeated(TestInteger[] toFill, int fillValue) {
        Arrays.fill(toFill, new TestInteger(fillValue));
    }

    private void fillArrayIncreasing(TestInteger[] toFill) {
        for(int i = 0; i < toFill.length; i++){
            toFill[i] = new TestInteger(i);
        }
    }

    private void fillArrayDecreasing(TestInteger[] toFill) {
        for(int i = 0; i < toFill.length; i++){
            toFill[i] = new TestInteger(-1 * i);
        }
    }

    private void fillArrayRandom(TestInteger[] toFill){
        fillArrayRandom(toFill, 1000000);
    }

    private void fillArrayRandom(TestInteger[] toFill, int upperBound) {
        for(int i = 0; i < toFill.length; i++){
            toFill[i] = new TestInteger((int)((java.lang.Math.random() * (upperBound - 1)) + 1));
        }
    }

    private void fillArrayRangeIncreasing(TestInteger[] toFill, int startPosition, int endPosition){
        int baseRandom = ((int) ((java.lang.Math.random() * 999989) + 1));
        for(int i = 0; i <= endPosition - startPosition; i++){
            toFill[i + startPosition] = new TestInteger(baseRandom + i);
        }
    }

    private void fillArrayRangeDecreasing(TestInteger[] toFill, int startPosition, int endPosition){
        Integer baseRandom = ((int) (java.lang.Math.random() * 999988));
        for(int i = 0; i <= endPosition - startPosition; i++){
            toFill[startPosition + i] = new TestInteger(1000000 - (baseRandom + i));
        }
    }

    private boolean isSorted(TestInteger[] toCheck){
        for(int i = 0; i < toCheck.length - 1; i++){
            if (Integer.compare(toCheck[i].value, toCheck[i + 1].value) > 0) {
                return false;
            }
        }
        return true;
    }

    // for the future
    // TestInteger pivot = toSort[rangeStart] //For lack of any knowledge about the array, take the first element as the pivot
        // int pivotPosStart = 0;
        // int pivotPosEnd = 0;

    public void Quicksort(TestInteger[] A, int p, int r){
        if(p < r){
            int q = Partition(A, p, r - 1);
            Quicksort(A, p, q - 1);
            Quicksort(A, q + 1, r);
        }
    }

    public int Partition(TestInteger[] A, int p, int r){
        TestInteger x = A[r];
        int i = p - 1;
        for(int j = p; j < r; j++){
            if(A[j].compareTo(x) <= 0){
                i = i + 1;
                exchange(A, i, j);
            }
        }
        exchange(A, i + 1, r);
        return i + 1;
    }

    private void exchange(TestInteger[] A, int p, int q){
        TestInteger storage = A[p];
        A[p] = A[q];
        A[q] = storage;
    }

    // Comparisons

    public void runComparisons(){
        System.out.println("Random ordering of elements:");
        for(int i = 0; i < 5; i++){
            System.out.println("Run " + (i + 1));
            randomSortComparison();
        }
        System.out.println("Increasing order of elements:");
        for(int i = 0; i < 5; i++){
            System.out.println("Run " + (i + 1));
            increasingSortComparison();
        }
        System.out.println("Increasing sequences of elements:");
        for(int i = 0; i < 5; i++){
            System.out.println("Run " + (i + 1));
            subsequenceIncreasingSortComparison();
        }
        System.out.println("Decreasing sequences of elements:");
        for(int i = 0; i < 5; i++){
            System.out.println("Run " + (i + 1));
            subsequenceDecreasingSortComparison();
        }
    }

    private void randomSortComparison(){
        TestInteger[] toMergeSort = new TestInteger[10000];
        fillArrayRandom(toMergeSort);
        TestInteger[] toQuickSort = Arrays.copyOf(toMergeSort, toMergeSort.length);
        compareSorting(toMergeSort, toQuickSort);
    }

    private void increasingSortComparison(){
        TestInteger[] toMergeSort = new TestInteger[10000];
        fillArrayIncreasing(toMergeSort);
        TestInteger[] toQuickSort = Arrays.copyOf(toMergeSort, toMergeSort.length);
        compareSorting(toMergeSort, toQuickSort);
    }

    private void subsequenceIncreasingSortComparison(){
        TestInteger[] toMergeSort = new TestInteger[10000];
        for(int i = 0; i < 9000; i += 1000){
            fillArrayRangeIncreasing(toMergeSort, i, i + 999);
        }
        TestInteger[] toQuickSort = Arrays.copyOf(toMergeSort, toMergeSort.length);
        compareSorting(toMergeSort, toQuickSort);
    }

    private void subsequenceDecreasingSortComparison(){
        TestInteger[] toMergeSort = new TestInteger[10000];
        for(int i = 0; i < 9000; i += 1000){
            fillArrayRangeDecreasing(toMergeSort, i, i + 999);
        }
        TestInteger[] toQuickSort = Arrays.copyOf(toMergeSort, toMergeSort.length);
        compareSorting(toMergeSort, toQuickSort);
    }

    private void compareSorting(TestInteger[] toMergeSort, TestInteger[] toQuickSort)
    {
        Arrays.sort(toMergeSort);
        System.out.println("Counter for mergesort: " + TestInteger.getCounter());
        TestInteger.resetCounter();
        Quicksort(toQuickSort, 0, toQuickSort.length);
        System.out.println("Counter for quicksort: " + TestInteger.getCounter());
        TestInteger.resetCounter();
    }

}