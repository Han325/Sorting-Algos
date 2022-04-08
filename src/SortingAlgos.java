import java.util.Arrays;

public class SortingAlgos {
    public static void main(String[] args) {
//        int[] arr = {20, 5, 3, 14, 17};
        int[] arr = {8, 7, 2, 1, 0, 9, 6};

//        BubbleSort(arr);
//        SelectionSort(arr);
//        InsertionSort(arr);
//        ShellSort(arr);
        QuickSort(arr, 0, arr.length - 1);
    }

    /**
     * Bubble sort is basically comparing an element with the element on its left
     * and if the left element is bigger than the element, the bigger element will swap place with the selected element
     * Example array {20, 5, 3, 14, 17}
     * 5 is compared with 20, and if 20 is bigger than 5, which it is, it will swap place with 5
     * the new array will be like this 5, 20, 3, 14, 17
     */
    public static void BubbleSort(int[] arr) {
        int n = arr.length;
        int temp = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (arr[j - 1] > arr[j]) {
                    //swap elements
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
                System.out.println(Arrays.toString(arr));

            }
        }
    }

    /**
     * Selection sort is basically, scanning the array for the lowest number and sort
     * Example array: {20, 5, 3, 14, 17}
     * It works by first setting a first value of the array which is 20 as the minimum.
     * A for loop is then ran to compare 20 with the rest of the elements to find the newest minimum.
     * In this case 20 will then be compared with 5, 3, 14, 17, and obviously 3 is the newest minimum.
     * 3 will then swap position with 20, and the new array will be 3, 5, 20, 14, 17
     */
    public static void SelectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[index]) {
                    index = j;//searching for lowest index
                }
            }
            int smallerNumber = arr[index];
            arr[index] = arr[i];
            arr[i] = smallerNumber;
            System.out.println(Arrays.toString(arr));
        }
    }

    /**
     * Insertion sort is similar to Bubble sort, but the differences are:
     * Insertion sort doesn't just compare adjacent elements but it compares all the elements on the left
     * And if an element bigger is found it swaps the position, thus the name insertion
     */
    public static void InsertionSort(int[] arr) {
        int n = arr.length;
        for (int j = 1; j < n; j++) {
            int key = arr[j];
            System.out.println(key);

            int i = j - 1;
            while ((i > -1) && (arr[i] > key)) {
                arr[i+1] = arr[i];
                i--;

            }
            System.out.println(i+1);

            arr[i + 1] = key;

            System.out.println(Arrays.toString(arr));

        }
    }

    /**
     * Shell sort is similar to insertion sort, the difference being the comparison happens between "intervals"
     * The interval is actually the length of the array divided by 2 for each iteration
     * If an array has 8 elements, the first iteration will be 4, and the element at the interval/position (4) will be
     * compared with the element that has a distance of 4 to it from the left.
     * The iteration continues until the interval becomes 1, and then it will be just like a standard insertion sort
     */
    public static void ShellSort(int[] array) {
        int n = array.length;
        for (int interval = n / 2; interval > 0; interval /= 2) {
            for (int i = interval; i < n; i += 1) {
                int temp = array[i];
                int j;
                for (j = i; j >= interval && array[j - interval] > temp; j -= interval) {
                    array[j] = array[j - interval];
                }
                array[j] = temp;
            }
        }
    }

    public static void QuickSort(int[] arr, int low, int high) {
        if (low < high) {

            // find pivot element such that
            // elements smaller than pivot are on the left
            // elements greater than pivot are on the right
            int pi = partition(arr, low, high);
            System.out.println(pi);

            // recursive call on the left of pivot
            QuickSort(arr, low, pi - 1);

            // recursive call on the right of pivot
            QuickSort(arr, pi + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high) {
        // choose the rightmost element as pivot
        int pivot = arr[high];

        // pointer for greater element
        int i = (low - 1);

        // traverse through all elements
        // compare each element with pivot
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {

                // if element smaller than pivot is found
                // swap it with the greater element pointed by i
                i++;

                // swapping element at i with element at j
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }

        }

        // swap the pivot element with the greater element specified by i
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        // return the position from where partition is done
        System.out.println(Arrays.toString(arr));
        return (i + 1);

    }

    public static void MergeSort(int arr[], int l, int r) {
        if (l < r) {

            // m is the point where the array is divided into two subarrays
            int m = (l + r) / 2;

            MergeSort(arr, l, m);
            MergeSort(arr, m + 1, r);

            // Merge the sorted subarrays
            merge(arr, l, m, r);
        }
    }

    public static void merge(int arr[], int p, int q, int r) {
        // Create L ← A[p..q] and M ← A[q+1..r]
        int n1 = q - p + 1;
        int n2 = r - q;

        int L[] = new int[n1];
        int M[] = new int[n2];

        for (int i = 0; i < n1; i++)
            L[i] = arr[p + i];
        for (int j = 0; j < n2; j++)
            M[j] = arr[q + 1 + j];

        // Maintain current index of sub-arrays and main array
        int i, j, k;
        i = 0;
        j = 0;
        k = p;

        // Until we reach either end of either L or M, pick larger among
        // elements L and M and place them in the correct position at A[p..r]
        while (i < n1 && j < n2) {
            if (L[i] <= M[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = M[j];
                j++;
            }
            k++;
        }

        // When we run out of elements in either L or M,
        // pick up the remaining elements and put in A[p..r]
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = M[j];
            j++;
            k++;
        }
    }
}
