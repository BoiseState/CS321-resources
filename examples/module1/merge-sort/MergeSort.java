
/**
 * Mergesort class. Provides two versions of mergesort.
 * @author Amit Jain
 */

public class MergeSort
{


/**
 * Sort the section of the array A[p..r] using Insertion sort.
 * See Section 2.1 of CLRS book.
 */
private void insertion_sort(int A[], int p, int r) {
	for (int j=p+1; j<=r; j++) {
		int key = A[j];
		int i = j-1;
		while ((i > p-1) && (A[i] > key)) {	
			A[i+1] = A[i];
			i--;
		}
		A[i+1] = key;
	}
}

		

/**
 * Sort the section of the array A[p..r] using mergesort.
 * See Section 2.3 of the CLRS book.
 */
public void mergesort(int A[], int p, int r, int cutoff) {
	if (r-p+1 <= cutoff)  {
			insertion_sort(A,p,r);
	} else {
		int q = (p+r)/2;
		mergesort(A, p, q, cutoff);
		mergesort(A, q+1, r, cutoff);
		merge(A, p, q, r);
	}
}



/**
 * Merge two sorted sequences A[p..q] and A[q+1..r] and place merged output back in array A. 
 * Uses extra space proportional to A[p..r].
 */     
public void merge(int A[], int p, int q, int r) {
		int []B = new int[r-p+1];
		
		int i = p;
		int j = q+1;
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
		
		// now only at most one list has unprocessed elements.
		
		if (i <= q) { 
			// copy remaining elements from the first list
			for (int l=i; l<=q; l++) {
				B[k] = A[l];
				k++;
			}
		} else {
			// copy remaining elements from the second list
			for (int l=j; l<=r; l++) {
				B[k] = A[l];
				k++;
			}
		}
		
		// copy merged output from array B back to array A
		k=0;
		for (int l=p; l<=r; l++) {
			A[l] = B[k];
			k++;
		}
}

/**
 * Merge, using sentinels, two sorted sequences A[p..q] and A[q+1..r] 
 * and place merged output back in array A. Uses extra space proportional to A[p..r]+2.
 */     
public void merge_with_sentinels(int A[], int p, int q, int r) 
{
		int n1 = q-p+1;
		int n2 = r-q;
		int [] L = new int[n1+1];
		int [] R = new int[n2+1];

		for (int i=0; i<n1; i++)
			L[i] = A[p+i];

		for (int j=0; j<n2; j++)
			R[j] = A[q+j+1];

		L[n1] = Integer.MAX_VALUE;
		R[n2] = Integer.MAX_VALUE;

		int i = 0;
		int j = 0;
		
		for (int k=p; k<=r; k++) {
			if (L[i] <= R[j]) {
				A[k] = L[i]; i++;
			} else {
				A[k] = R[j]; j++;
			}
		}
		L = R = null;
}


/**
 * Sort the section of the array A[p..r] using mergesort with sentinels.
 */
public void mergesort_with_sentinels(int A[], int p, int r, int cutoff) {
	if (r-p+1 <= cutoff)  {
			insertion_sort(A,p,r);
	} else {
		int q = (p+r)/2;
		mergesort_with_sentinels(A, p, q, cutoff);
		mergesort_with_sentinels(A, q+1, r, cutoff);
		merge_with_sentinels(A, p, q, r);
	}
}

}
