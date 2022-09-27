
/**
 * A wrapper class that provides the basic quicksort algorithm on an array of integers.
 * 
 * @author amit
 */

public class QuickSort
{

    /**
     * Use quicksort algorithm to sort A[p:r].
     * 
     * @param A The array to be sorted.
     * @param p The leftmost index in the array.
     * @param r the rightmost index in the array.
     */
    public void quicksort(int A[], int p, int r) {

        if (p < r) {
            int q = partition(A, p, r);
            quicksort(A, p, q - 1);
            quicksort(A, q + 1, r);
        }
    }


    /**
     * Partition the array A[p:r] around a pivot element, chosen to be A[r].
     * 
     * @param A
     * @param p
     * @param r
     * @return
     */
    private int partition(int[] A, int p, int r) {
        int x = A[r];
        int i = p - 1;
        for (int j = p; j < r; j++) {
            if (A[j] <= x) {
                i++;
                int tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
            }
        }
        int tmp = A[i + 1];
        A[i + 1] = A[r];
        A[r] = tmp;
        return i + 1;
    }

}
