//Ben B, Ben G

import java.util.Arrays;

import java.lang.Math;
import java.io.StringWriter;

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
            int q = Partition(A, p, r);
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
        Integer[][] randomComparisonCount = new Integer[5][2];
        Integer[][] increasingComparisonCount = new Integer[5][2];
        Integer[][] subsequenceIncreasingComparisonCount = new Integer[5][2];
        Integer[][] subsequenceDecreasingComparisonCount = new Integer[5][2];

        // Arrays contain arrays of information in the format [quicksort, mergesort]

        for(int i = 0; i < 5; i++){
            randomComparisonCount[i] = randomSortComparison();
            increasingComparisonCount[i] = increasingSortComparison();
            subsequenceIncreasingComparisonCount[i] = subsequenceIncreasingSortComparison();
            subsequenceDecreasingComparisonCount[i] = subsequenceDecreasingSortComparison();
        }

        System.out.println("List of times when running quicksort on a randomized array: " + prettifyResultArray(randomComparisonCount, 0));
        System.out.println("List of times when running mergesort on a randomized array: " +  prettifyResultArray(randomComparisonCount, 1));
        System.out.println("List of times when running quicksort on a sorted array: " + prettifyResultArray(increasingComparisonCount, 0));
        System.out.println("List of times when running mergesort on a sorted array: " + prettifyResultArray(increasingComparisonCount, 1));
        System.out.println("List of times when running quicksort on an array with sorted subsequences: " + prettifyResultArray(subsequenceIncreasingComparisonCount, 0));
        System.out.println("List of times when running mergesort on an array with sorted subsequences: " + prettifyResultArray(subsequenceIncreasingComparisonCount, 1));
        System.out.println("List of times when running quicksort on an array with reverse-sorted subsequences: " + prettifyResultArray(subsequenceDecreasingComparisonCount, 0));
        System.out.println("List of times when running quicksort on an array with reverse-sorted subsequences: " + prettifyResultArray(subsequenceDecreasingComparisonCount, 1));
    }

    private Integer[] randomSortComparison(){
        TestInteger[] toSort = new TestInteger[10000];
        fillArrayRandom(toSort);
        return compareSorting(toSort);
    }

    private Integer[] increasingSortComparison(){
        TestInteger[] toSort = new TestInteger[10000];
        fillArrayIncreasing(toSort);
        return compareSorting(toSort);
    }

    private Integer[] subsequenceIncreasingSortComparison(){
        TestInteger[] toSort = new TestInteger[10000];
        for(int i = 0; i < 9000; i += 1000){
            fillArrayRangeIncreasing(toSort, i, i + 999);
        }
        return compareSorting(toSort);
    }

    private Integer[] subsequenceDecreasingSortComparison(){
        TestInteger[] toSort = new TestInteger[10000];
        for(int i = 0; i < 9000; i += 1000){
            fillArrayRangeDecreasing(toSort, i, i + 999);
        }
        return compareSorting(toSort);
    }

    private Integer[] compareSorting(TestInteger[] toSort){
        Integer[] output = new Integer[2];
        Quicksort(java.util.Arrays.copyOf(toSort, toSort.length), 0, toSort.length);
        output[0] = (int)TestInteger.getCounter();
        TestInteger.resetCounter();
        java.util.Arrays.sort(toSort);
        output[1] = (int)TestInteger.getCounter();
        TestInteger.resetCounter();
        return output;
    }


    private String prettifyResultArray(Integer[][] input, int column){
        StringWriter output = new StringWriter();
        output.write('[');
        for(int i = 0; i < input.length; i++){
            output.write(input[i][column].toString());
            output.write(", ");
        }
        output.write(']');
        return output.toString();
    }

}