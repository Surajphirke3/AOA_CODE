
 import java.util.Scanner;

 class MergeSort{  

    void merge(int arr[], int low, int mid, int high) {
        int n1 = mid - low + 1; 
        int n2 = high - mid;    

        
        int left[] = new int[n1];
        int right[] = new int[n2];

      
        for (int i = 0; i < n1; i++)
            left[i] = arr[low + i];
        for (int j = 0; j < n2; j++)
            right[j] = arr[mid + 1 + j];

      
        int i = 0, j = 0, k = low;
        while (i < n1 && j < n2) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }

      
        while (i < n1) {
            arr[k] = left[i];
            i++;
            k++;
        }

       
        while (j < n2) {
            arr[k] = right[j];
            j++;
            k++;
        }
    }

   
    void mergeSort(int arr[], int low, int high) {
        if (low < high) {
            int mid = low + (high - low) / 2; 

            mergeSort(arr, low, mid);
            mergeSort(arr, mid + 1, high);

          
            merge(arr, low, mid, high);
        }
    }

   
    void printArray(int arr[]) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

        public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

       
        System.out.print("Enter the number of elements: ");
        int n = sc.nextInt();
        int arr[] = new int[n];

        System.out.println("Enter " + n + " elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println("\nGiven Array:");
        MergeSort ob = new MergeSort();
        ob.printArray(arr);

       
        ob.mergeSort(arr, 0, n - 1);

        System.out.println("\nSorted Array:");
        ob.printArray(arr);

        sc.close();
    }
}
       

