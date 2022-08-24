
/**
 * Insertion sort class. A wrapper class that provides the basic insertion sort.
 * 
 * @author amit
 */

public class InsertionSort
{

    /**
     * Sort the section of the array A[p..r] using Insertion sort. See Section 2.1
     * of CLRS's Algorithms book.
     */
    public void insertion_sort(int A[], int p, int r) {
        for (int j = p + 1; j <= r; j++) {
            int key = A[j];
            int i = j - 1;
            while ((i > p - 1) && (A[i] > key)) {
                A[i + 1] = A[i];
                i--;
            }
            A[i + 1] = key;
        }
    }

}
