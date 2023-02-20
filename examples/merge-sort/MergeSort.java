
/**
 * Provides two versions of merge sort, one without and one with cutoff to switch 
 * to insertion sort when recursion reaches a small enough sub-array.
 * 
 * @author amit
 */
public class MergeSort
{
    /**
     * Sort the section of the array A[p..r] using Insertion sort. See Section 2.1
     * of CLRS book.
     * 
     * @param A the input array
     * @param p the starting index in the array
     * @param r the ending index in the array
     */
    private void insertion_sort(int A[], int p, int r) {
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


    /**
     * Sort the section of the array A[p..r] using merge sort with insertion sort on small
     * lists below the cutoff size. See Section 2.3 of the CLRS book.
     */
    public void mergesort(int A[], int p, int r, int cutoff) {
        if (r - p + 1 <= cutoff) {
            insertion_sort(A, p, r);
        } else {
            int q = (p + r) / 2;
            mergesort(A, p, q, cutoff);
            mergesort(A, q + 1, r, cutoff);
            merge(A, p, q, r);
        }
    }


    /**
     * Merge two sorted sequences A[p..q] and A[q+1..r] and place merged output back
     * in array A. Uses extra space proportional to A[p..r].
     */
    public void merge(int A[], int p, int q, int r) {
        int[] B = new int[r - p + 1];

        int i = p;
        int j = q + 1;
        int k = 0;

        // as long as both lists have unexamined elements
        // this loop keeps executing.
        while ((i <= q) && (j <= r)) {
            if (A[i] <= A[j]) {
                B[k] = A[i];
                i++;
            } else {
                B[k] = A[j];
                j++;
            }
            k++;
        }

        // now only one list has unprocessed elements.
        if (i <= q) {
            // copy remaining elements from the first list
            for (int l = i; l <= q; l++) {
                B[k] = A[l];
                k++;
            }
        } else {
            // copy remaining elements from the second list
            for (int l = j; l <= r; l++) {
                B[k] = A[l];
                k++;
            }
        }

        // copy merged output from array B back to array A
        k = 0;
        for (int l = p; l <= r; l++) {
            A[l] = B[k];
            k++;
        }
    }

}
