
/**
 * A wrapper class that provides the basic insertion sort.
 * 
 * @author amit
 */

public class InsertionSort
{
    /**
     * Sort the section of the array A[p:r] using Insertion sort. See Section 2.1
     * of CLRS's Algorithms book. The algorithm in the book sorts the range [1:n]. 
     * Here we have generalized it to sort a range [p:r]
     * 
     * @param A The array to sort
     * @param p The first index in the range to sort
     * @param r The last index in the range to sort
     */
    public void insertion_sort(int A[], int p, int r) {
        for (int i = p + 1; i <= r; i++) {
            int key = A[i];
            int j = i - 1;
            while ((j > p - 1) && (A[j] > key)) {
                A[j + 1] = A[j];
                j--;
            }
            A[j + 1] = key;
        }
    }

}
